package ru.jprod.core.model;

import ru.jprod.cleaner.Cleaner;
import ru.jprod.cleaner.ICleanable;

/**
 * Фабрика по созданию моделей
 *
 * @author artem
 * @since 25.05.19
 */
public class ModelFactory
{
    /**
     * Создать модель. Модель добавляется в очередь на удаление
     *
     * @param clazz класс
     * @param <T>
     * @return модель
     */
    public static <T extends Model & ICleanable> T create(Class<T> clazz)
    {
        T model;

        try
        {
            model = clazz.newInstance();
        }
        catch (InstantiationException | IllegalAccessException ex)
        {
            throw new RuntimeException("can not create model", ex);
        }

        Cleaner.get().push(model);
        return model;
    }
}
