package ru.jprod.cleaner;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Клинер тестовых данных. Работает в двух режимах:
 *
 * <ul>
 * <li>в режиме добавления/удаления в очередь для тестового теста</li>
 * <li>в режиме добавления/удаления в очередь для тестового класса</li>
 * </ul>
 *
 * @author artem
 * @since 25.05.19
 */
public class Cleaner
{
    /**
     * Режик клинера
     */
    public enum MODE
    {
        /**
         * Режим работы с очередью для тестового метода
         */
        TEST,
        /**
         * Режим работы с очередью для тестового класса
         */
        CLASS;

        private final Deque<ICleanable> queue = new LinkedList<>();

        public Deque<ICleanable> getQueue()
        {
            return queue;
        }
    }

    private static final Cleaner cleaner = new Cleaner();

    private MODE mode = MODE.TEST;

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
     * Очистить очередь объектов
     */
    public void clean()
    {
        clean(mode);
    }

    /**
     * Очистить очередь объектов
     *
     * @param mode очередь для удаления
     */
    public void clean(MODE mode)
    {
        Deque<ICleanable> queue = mode.getQueue();
        while (queue.size() > 0)
        {
            ICleanable elem = queue.removeLast();
            if (elem.isNeedClean())
            {
                elem.clean();
            }
        }
    }

    /**
     * Получить режим
     *
     * @return режим
     */
    public MODE getMode()
    {
        return mode;
    }

    /**
     * Отправить объект в очередь на удаление
     *
     * @param elem объект
     */
    public void push(ICleanable elem)
    {
        mode.getQueue().add(elem);
    }

    /**
     * Установить режим
     *
     * @param mode режим
     */
    public void setMode(MODE mode)
    {
        this.mode = mode;
    }
}
