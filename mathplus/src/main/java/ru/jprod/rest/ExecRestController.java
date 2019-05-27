package ru.jprod.rest;

import org.springframework.http.ResponseEntity;

/**
 * REST контроллер для выполнения скриптов
 *
 * @author artem
 * @since 27.05.19
 */
public interface ExecRestController
{
    /**
     * Выполнить скрипт
     *
     * @param script скрипт
     * @return результат выполнения
     */
    ResponseEntity<String> execute(String script);
}
