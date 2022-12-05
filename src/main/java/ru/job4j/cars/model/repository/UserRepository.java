package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Optional;

/**
 * UserRepository - класс репозиторий
 *
 * @author Ilya Kaltygin
 */

@AllArgsConstructor
public class UserRepository {

    private static final String UPDATE = "UPDATE User SET login = :login, password = :password WHERE id = :id";

    private static final String DELETE = "DELETE User WHERE id = :id";

    private static final String FIND_ALL_ORDER_BY = "From User as user ORDER BY user.id";

    private static final String FIND_BY_ID = "From User WHERE id = :id";

    private static final String FIND_BY_LIKE_LOGIN = "From User WHERE login LIKE :key";

    private static final String FIND_BY_LOGIN = "From User WHERE login = :login";

    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    /**
     * обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(UPDATE)
                    .setParameter("login", user.getLogin())
                    .setParameter("password", user.getPassword())
                    .setParameter("id", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }

    }

    /**
     * удалить пользователя по id.
     * @param userId id.
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(DELETE)
                    .setParameter("id", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * список пользователей отсортированных по id.
     * @return список пользователй.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<User> query = session.createQuery(FIND_ALL_ORDER_BY);
        List<User> result = query.list();
        return result;
    }

    /**
     * найти пользователя по id.
     * @param id id.
     * @return Optional или пользователь.
     */
    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<User> query = session.createQuery(FIND_BY_ID);
        Optional<User> result = query.setParameter("id", id).uniqueResultOptional();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * список пользователей по login LIKE %key%.
     * @param key key.
     * @return список пользователй.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<User> query = session.createQuery(FIND_BY_LIKE_LOGIN);
        List<User> result = query.setParameter("key", "%" + key + "%").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * найти пользователя по login.
     * @param login login.
     * @return Optional или пользователь
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<User> query = session.createQuery(FIND_BY_LOGIN);
        Optional<User> result = query.setParameter("login", login).uniqueResultOptional();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
