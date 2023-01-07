package ru.job4j.cars.repository;

import org.hibernate.Session;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Ilya Kaltygin
 */
public interface CrudRepository {

    /**
     * Метод принимает параметры и создает из них команду и передает ее в
     * метод tx(Function<Session, T> command), который выполняет полученную команду.
     * @param command команда.
     */
    void run(Consumer<Session> command);

    /**
     /**
     * Метод принимает параметры и создает из них команду и передает ее в
     * метод tx(Function<Session, T> command), который выполняет полученную команду.
     * @param query запрос.
     * @param args карта,где ключ = псевдоним, значение = значение псевдонима.
     */
    void run(String query, Map<String, Object> args);

    /**
     * Метод принимает параметры и создает из них команду.
     * В этом же методе вызывается метод tx(Function<Session, T> command), который выполняет созданную команду.
     * @param query запрос.
     * @param cl Класс, данные какого типа хотим получить.
     * @param args карта,где ключ = псевдоним, значение = значение псевдонима.
     * @return Optional<T>
     * @param <T> generic.
     */
    <T> Optional<T> queryAndGetOptional(String query, Class<T> cl, Map<String, Object> args);

    /**
     /**
     * Метод принимает параметры и создает из них команду.
     * В этом же методе вызывается метод tx(Function<Session, T> command), который выполняет созданную команду.
     * @param query запрос.
     * @param cl Класс, данные какого типа хотим получить.
     * @return List<T>
     * @param <T> generic.
     */
    <T> List<T> queryAndGetList(String query, Class<T> cl);

    /**
     * Метод принимает параметры и создает из них команду.
     * В этом же методе вызывается метод tx(Function<Session, T> command), который выполняет созданную команду.
     * @param query запрос.
     * @param cl Класс, данные какого типа хотим получить.
     * @param args карта,где ключ = псевдоним, значение = значение псевдонима.
     * @return List<T>
     * @param <T> generic.
     */
    <T> List<T> queryAndGetList(String query, Class<T> cl, Map<String, Object> args);

    /**
     * Главный метод в этом классе, выполняющий абстрактную операцию.
     * Метод принимает команду, открывает сессию, начинает транзакцию, выполняет команду.
     * Команда принимается в виде функционального интерфейса, благодаря чему достигается абстрактность операции.
     * @param command команда, которую необходимо выполнить.
     * @param <T> generic.
     * @return значение типа Т.
     */
    <T> T tx(Function<Session, T> command);
}
