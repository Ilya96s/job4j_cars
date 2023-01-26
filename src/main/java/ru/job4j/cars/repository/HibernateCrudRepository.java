package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * CrudRepository - реализация паттерна Command
 *
 * @author Ilya Kaltygin
 */

@Repository
@AllArgsConstructor
public class HibernateCrudRepository implements CrudRepository {

    /**
     * Объект конфигуратор.
     * Используется для получения объектов Session.
     * Отвечает за считывание параметров конфигурации Hibernate и подключение к базе данных.
     */
    private final SessionFactory sf;

    /**
     * Метод принимает параметры и создает из них команду и передает ее в
     * метод tx(Function<Session, T> command), который выполняет полученную команду.
     * @param command команда.
     */
    @Override
    public void run(Consumer<Session> command) {
        tx(session -> {
            command.accept(session);
            return null;
        });
    }

    /**
     /**
     * Метод принимает параметры и создает из них команду и передает ее в
     * метод tx(Function<Session, T> command), который выполняет полученную команду.
     * @param query запрос.
     * @param args карта,где ключ = псевдоним, значение = значение псевдонима.
     */
    @Override
    public void run(String query, Map<String, Object> args) {
        Consumer<Session> command = session -> {
            var sq = session.createQuery(query);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            sq.executeUpdate();
        };
        run(command);
    }

    /**
     * Метод принимает параметры и создает из них команду.
     * В этом же методе вызывается метод tx(Function<Session, T> command), который выполняет созданную команду.
     * @param query запрос.
     * @param cl Класс, данные какого типа хотим получить.
     * @param args карта,где ключ = псевдоним, значение = значение псевдонима.
     * @return Optional<T>
     * @param <T> generic.
     */
    @Override
    public <T> Optional<T> queryAndGetOptional(String query, Class<T> cl, Map<String, Object> args) {
        Function<Session, Optional<T>> command = session -> {
            var sq = session.createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return sq.uniqueResultOptional();
        };
        return tx(command);
    }

    /**
     /**
     * Метод принимает параметры и создает из них команду.
     * В этом же методе вызывается метод tx(Function<Session, T> command), который выполняет созданную команду.
     * @param query запрос.
     * @param cl Класс, данные какого типа хотим получить.
     * @return List<T>
     * @param <T> generic.
     */
    @Override
    public <T> List<T> queryAndGetList(String query, Class<T> cl) {
        Function<Session, List<T>> command = session -> session.createQuery(query, cl).list();
        return tx(command);
    }

    /**
     * Метод принимает параметры и создает из них команду.
     * В этом же методе вызывается метод tx(Function<Session, T> command), который выполняет созданную команду.
     * @param query запрос.
     * @param cl Класс, данные какого типа хотим получить.
     * @param args карта,где ключ = псевдоним, значение = значение псевдонима.
     * @return List<T>
     * @param <T> generic.
     */
    @Override
    public <T> List<T> queryAndGetList(String query, Class<T> cl, Map<String, Object> args) {
        Function<Session, List<T>> command = session -> {
            var sq = session.createQuery(query, cl);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                sq.setParameter(arg.getKey(), arg.getValue());
            }
            return sq.list();
        };
        return tx(command);
    }

    /**
     * Главный метод в этом классе, выполняющий абстрактную операцию.
     * Метод принимает команду, открывает сессию, начинает транзакцию, выполняет команду.
     * Команда принимается в виде функционального интерфейса, благодаря чему достигается абстрактность операции.
     * @param command команда, которую необходимо выполнить.
     * @param <T> generic.
     * @return значение типа Т.
     */
    @Override
    public <T> T tx(Function<Session, T> command) {
        var session = sf.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
