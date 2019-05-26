package ru.jprod.cleaner;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Клинер тестовых данных. Работает в двух режимах:
 *
 * <ul>
 * <li>в режиме работы с очередью для тестового метода</li>
 * <li>в режиме работы с очередью для тестового класса</li>
 * </ul>
 *
 * @author artem
 * @since 25.05.19
 */
public class Cleaner
{
    /**
     * Типы очередей клинера
     */
    public enum QueueMode
    {
        /**
         * Режим с очередью для тестового метода
         */
        TEST,
        /**
         * Режим с очередью для тестового класса
         */
        CLASS;

        private final Deque<ICleanable> queue = new LinkedList<>();

        public Deque<ICleanable> getQueue()
        {
            return queue;
        }
    }

    private static final Cleaner cleaner = new Cleaner();

    private QueueMode queueMode = QueueMode.TEST;

    private Cleaner()
    {
    }

    /**
     * Получить объект клинера
     *
     * @return клинер
     */
    public static Cleaner get()
    {
        return cleaner;
    }

    /**
     * Очистить текущую очередь
     */
    public void clean()
    {
        clean(queueMode);
    }

    /**
     * Очистить очередь объектов
     *
     * @param queueMode тип очереди
     */
    public void clean(QueueMode queueMode)
    {
        Deque<ICleanable> queue = queueMode.getQueue();
        while (queue.size() > 0)
        {
            ICleanable last = queue.removeLast();
            if (last.isNeedClean())
            {
                last.clean();
            }
        }
    }

    /**
     * Получить текущий режим
     *
     * @return режим
     */
    public QueueMode currentMode()
    {
        return queueMode;
    }

    /**
     * Отправить объект в очередь на удаление
     *
     * @param elem объект
     */
    public void push(ICleanable elem)
    {
        queueMode.getQueue().add(elem);
    }

    /**
     * Установить режим
     *
     * @param queueMode режим
     */
    public void switchTo(QueueMode queueMode)
    {
        this.queueMode = queueMode;
    }
}
