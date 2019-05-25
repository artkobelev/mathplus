package ru.jprod.core.model.algorithm;

import ru.jprod.core.model.ModelFactory;
import ru.jprod.util.GenerateUtils;

/**
 * DAO для объекта алгоритма
 *
 * @author artem
 * @since 25.05.19
 */
public class DAOAlgorithm
{
    private DAOAlgorithm()
    {

    }

    /**
     * Создать алгоритм
     *
     * @param name имя алгоритма
     * @return модель
     */
    public static Algorithm create(String name)
    {
        Algorithm algorithm = ModelFactory.create(Algorithm.class);
        algorithm.setName(name);
        algorithm.setScript(GenerateUtils.createText());
        return algorithm;
    }

    /**
     * Создать алгоритм
     *
     * @return модель
     */
    public static Algorithm create()
    {
        return create(GenerateUtils.createName());
    }
}
