package ru.jprod.util;

import static com.google.common.collect.Iterables.transform;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;

/**
 * Класс, содержащий утилитарные методы для валидации объектов
 *
 * @author artem
 * @since 23.05.2019
 */
@Component
public class ValidationUtils
{
    @Inject
    private Validator validator;

    /**
     * Метод, выполняющий валидацию объекта, согласно установленным в нём правилам валидации
     *
     * @param object объект, который нужно валидировать
     */
    public void validate(Object object)
    {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty())
        {
            throw new ValidationException(Joiner.on('\n').join(transform(violations, ConstraintViolation::getMessage)));
        }
    }
}
