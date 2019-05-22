package ru.jprod.util.sql.column;

import java.sql.Types;
import java.util.Set;

import org.hibernate.dialect.Dialect;
import org.hibernate.mapping.Column;

import com.google.common.collect.Sets;

import ru.jprod.util.sql.constraint.Constraint;

public class ColumnDefinition
{
    private Column column = new Column();

    private Set<Constraint> constraints = Sets.newHashSet();

    public ColumnDefinition(String name, int type)
    {
        column.setName(name);
        column.setTypeIndex(type);
    }

    public void addConstraint(Constraint constraint)
    {
        constraints.add(constraint);
    }

    public Set<Constraint> getConstraints()
    {
        return constraints;
    }

    public String getName()
    {
        return column.getName();
    }

    public int getTypeIndex()
    {
        return column.getTypeIndex();
    }

    /**
     * @param dialect
     * @return имя типа колонки соответствующее {@link Types}
     */
    public String getTypeName(Dialect dialect)
    {
        return dialect.getTypeName(column.getTypeIndex(), column.getLength(), column.getPrecision(),
                column.getScale());
    }
}
