package ru.jprod.core.model.algorithm.dao;

import org.springframework.stereotype.Component;

import ru.jprod.core.model.Algorithm;
import ru.jprod.core.model.dao.DAOBase;

/**
 * DAO для {@link Algorithm}
 *
 * @author artem
 * @since 20.05.2019
 */
@Component
public class DAOAlgorithmImpl extends DAOBase<Algorithm> implements DAOAlgorithm
{
}
