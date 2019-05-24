package ru.jprod.util;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import com.google.common.collect.Maps;

/**
 * Выполнение кода в транзакции
 * Класс позволяет абстрагироваться от такой сущности как {@link TransactionTemplate}
 * TransactionTemplate потокобезопасен, поэтому мы можем считать данный класс синглтоном
 */
@Component(value = "txRunner")
public class TxRunner
{
    /**
     * Тип транзакции
     */
    public enum TxType
    {
        READ_ONLY //транзакция текущая (PROPAGATION_REQUIRED) + readOnly=true
                {
                    @Override
                    public TransactionTemplate getTemplate(PlatformTransactionManager txManager)
                    {
                        TransactionTemplate template = super.getTemplate(txManager);
                        template.setReadOnly(true);
                        return template;
                    }
                },
        NEW //транзакция новая (PROPAGATION_REQUIRES_NEW)
                {
                    @Override
                    public TransactionTemplate getTemplate(PlatformTransactionManager txManager)
                    {
                        TransactionTemplate template = super.getTemplate(txManager);
                        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                        return template;
                    }
                },
        CURRENT_OR_NEW; //транзакция текущая (PROPAGATION_REQUIRED);

        /**
         * Получить {@link TransactionTemplate}
         */
        public TransactionTemplate getTemplate(PlatformTransactionManager txManager)
        {
            return new TransactionTemplate(txManager);
        }
    }

    /**
     * Ошибка выполнения действия в транзакции
     */
    public static class TxInvocationException extends RuntimeException
    {
        public TxInvocationException(Throwable cause)
        {
            super(cause);
        }
    }

    private static volatile TxRunner runner;
    private Map<TxType, TransactionTemplate> templates = Maps.newHashMap();
    @Inject
    private PlatformTransactionManager txManager;

    /**
     * Выполнить код в текущей транзакции (или создать новую если текущей нет)
     */
    public static <T> T call(Callable<T> callable)
    {
        return call(TxType.CURRENT_OR_NEW, callable);
    }

    /**
     * @param callable - callback
     * @param txType   - тип транзакции
     * @param <T>      тип возвращаемого значения
     * @return результат работы {@code callable}
     */
    public static <T> T call(TxType txType, Callable<T> callable)
    {
        try
        {
            return get().execute(txType, callable);
        }
        catch (RuntimeException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new TxInvocationException(e);
        }
    }

    /**
     * Выполнить код в текущей транзакции (или создать новую если текущей нет)
     */
    public static void run(final Runnable runnable)
    {
        run(TxType.CURRENT_OR_NEW, runnable);
    }

    /**
     * @param runnable - код, который выполняем в транзакции
     * @param txType   - тип транзакции
     */
    public static void run(TxType txType, final Runnable runnable)
    {
        call(txType, () -> {
            runnable.run();
            return null;
        });
    }

    private static TxRunner get()
    {
        return runner;
    }

    /**
     * Выполнить callable используя propagation PROPAGATION_REQUIRED
     *
     * @param <T> тип возвращаемого значения
     * @return результат работы {@code callable}
     */
    public <T> T execute(final Callable<T> callable) throws Exception
    {
        return execute(TxType.CURRENT_OR_NEW, callable);
    }

    /**
     * Выполнить callable
     *
     * @param callable - callback
     * @param template - тип транзакции: новая, текущая, текущая + readonly
     * @param <T>      тип возвращаемого значения
     * @return результат выполнения {@code callable}
     * @throws Exception
     */
    public <T> T execute(TxType template, final Callable<T> callable) throws Exception
    {
        try
        {
            return getResult(template, callable);
        }
        catch (TxInvocationException e)
        {
            throw (Exception)e.getCause();
        }
    }

    private <T> T getResult(TxType txType, final Callable<T> callable)
    {
        return templates.get(txType).execute((TransactionStatus status) -> {
            try
            {
                return callable.call();
            }
            catch (Exception e)
            {
                throw new TxInvocationException(e);
            }
        });
    }

    @PostConstruct
    private void init()
    {
        Arrays.asList(TxType.values()).forEach(e -> templates.put(e, e.getTemplate(txManager)));
        runner = this;
    }
}
