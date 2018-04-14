package main.java.ru.sbt.jschool.session6.Problem1.Formatter;

import java.util.Map;

/**
 */
public interface JSONFormatter {
    /**
     * Marshall object to JSON string.
     *
     * @param obj Any object.
     * @return String in JSON format.
     */
    String marshall(Object obj);
    String marshall(Object obj, Map ctx);

    /**
     * Add predefined type to formatter.
     *
     * @param clazz Class to add.
     * @param format Formatter for specified class.
     * @param <T> Formatted type.
     * @return True if class been previously known.
     */
    <T> boolean addType(Class<T> clazz, JSONTypeFormatter<T> format);
}
