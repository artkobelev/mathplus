package ru.jprod.core.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import ru.jprod.core.model.HasName;
import ru.jprod.util.exceptions.ObjectExistsException;
import ru.jprod.util.exceptions.ObjectNotFoundException;

/**
 * Общие методы для DAO классов
 *
 * @param <T>
 */
public abstract class DAOBase<T extends HasName> extends AbstractDAO implements DAO<T>
{
    @Override
    public void checkAbsent(String name) throws ObjectExistsException
    {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteria.from(getType());

        Query<Long> query = session().createQuery(criteria
                .select(criteriaBuilder.construct(Long.class, root.get("id")))
                .where(criteriaBuilder.equal(root.<String>get(HasName.NAME), name))
        ).setMaxResults(1);

        if (query.uniqueResult() != null)
        {
            throw new ObjectExistsException(getType(), name);
        }
    }

    @Override
    public long count()
    {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        return ((Number)session().createQuery(criteria
                .select(criteriaBuilder.count(criteria.from(getType())))
        ).getSingleResult()).longValue();
    }

    @Override
    public void delete(T obj)
    {
        session().delete(obj);
    }

    @Override
    public List<String> getAllNames()
    {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<String> criteria = criteriaBuilder.createQuery(String.class);
        Root<T> root = criteria.from(getType());

        return session().createQuery(criteria
                .select(criteriaBuilder.construct(String.class, root.get(HasName.NAME)))
                .orderBy(criteriaBuilder.asc(root.get(HasName.NAME)))
        ).getResultList();
    }

    @Override
    public T getById(long id)
    {
        return session().get(getType(), id);
    }

    @Override
    public T getByName(String name)
    {
        return getObjectByName(name);
    }

    @Override
    public List<T> getExisting(Collection<String> names)
    {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getType());
        Root<T> root = criteria.from(getType());

        List<T> result = session().createQuery(criteria
                .select(root)
                .where(root.get(HasName.NAME).in(names))
        ).getResultList();

        if (result.size() != names.size())
        {
            Set<String> actual = Sets.newHashSet(Iterables.transform(result, HasName::getName));
            Set<String> expected = Sets.newHashSet(names);
            Set<String> difference = Sets.difference(expected, actual);
            throw new ObjectNotFoundException(String.format("Objects with type=%s and names=%s not found",
                    getType().getName(), Iterables.toString(difference)));
        }
        return result;
    }

    @Override
    public T getExisting(long id) throws ObjectNotFoundException
    {
        T obj = session().get(getType(), id);
        if (obj == null)
        {
            throw new ObjectNotFoundException(getType(), id);
        }
        return obj;
    }

    @Override
    public T getExisting(String name)
    {
        T object = getObjectByName(name);
        if (object == null)
        {
            throw new ObjectNotFoundException(getType(), name);
        }
        return (T)object;
    }

    @Override
    public long save(T obj)
    {
        return (long)session().save(obj);
    }

    @Override
    public void update(T obj)
    {
        session().update(obj);
    }

    protected Class<T> getType()
    {
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private T getObjectByName(String name)
    {
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<T> criteria = criteriaBuilder.createQuery(getType());
        Root<T> root = criteria.from(getType());

        Object object = session().createQuery(criteria
                .select(root)
                .where(criteriaBuilder.equal(root.<String>get(HasName.NAME), name))
        ).setMaxResults(1).uniqueResult();

        return (T)object;
    }
}