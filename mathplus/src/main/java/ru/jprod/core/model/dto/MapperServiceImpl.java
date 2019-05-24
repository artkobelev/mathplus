package ru.jprod.core.model.dto;

import static com.google.common.collect.Maps.newLinkedHashMap;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

import ru.jprod.util.exceptions.MathplusException;

/**
 * Методы для преобразования сущностей в {@link Map} и наоборот
 *
 * @author artem
 * @since 20.05.2019
 */
@Component
public class MapperServiceImpl implements MapperService
{
    @Inject
    private ApplicationContext context;

    private Map<Class<?>, DtoMapper<Exportable>> mappers = Maps.newHashMap();

    @Override
    public <T extends Exportable> Map<String, Object> packToMap(T object)
    {
        return getMapper(object.getClass()).pack(object, newLinkedHashMap());
    }

    @Override
    public <T extends Exportable> T restore(Map<String, Object> map)
    {
        return (T)getMapper(getDtoType(map)).restore(map);
    }

    @Override
    public <T extends Exportable> T update(Map<String, Object> from, T object)
    {
        return (T)getMapper(object.getClass()).update(from, object);
    }

    private Class<?> getDtoType(Map<String, Object> map)
    {
        if (!map.containsKey(DtoMapper.TYPE))
        {
            throw new MathplusException("Type is missing in dtObject");
        }
        String typeName = (String)map.get(DtoMapper.TYPE);
        try
        {
            return Class.forName(typeName);
        }
        catch (ClassNotFoundException e)
        {
            throw new MathplusException("DtOject type {" + typeName + "} is not registered in the system. ",
                    e.getCause());
        }
    }

    private DtoMapper<Exportable> getMapper(Class<?> clazz)
    {
        if (mappers.containsKey(clazz))
        {
            return mappers.get(clazz);
        }
        throw new MathplusException("No mapper registered for class: {" + clazz.getName() + "}");
    }

    @PostConstruct
    private void registerMappers()
    {
        BeanFactoryUtils.beansOfTypeIncludingAncestors(context, DtoMapper.class).values().forEach(a -> {
            mappers.put(a.getType(), a);
        });
    }
}
