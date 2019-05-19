package ru.jprod.core.model;

import static ru.jprod.util.Constants.EntityConstants.NAME_MAX_LENGTH;
import static ru.jprod.util.Constants.EntityConstants.NAME_MIN_LENGTH;
import static ru.jprod.util.Constants.EntityConstants.NAME_REGEX;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_algorithm", indexes = { @Index(name = "idx_algorithm_name", columnList = "name") })
public class Algorithm implements HasName
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    @NotNull(message = "{mathplus.validation.NotNull}")
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH, message = "{mathplus.validation.Size}")
    @Pattern(regexp = NAME_REGEX, message = "{mathplus.validation.Pattern}")
    private String name;

    public long getId()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}