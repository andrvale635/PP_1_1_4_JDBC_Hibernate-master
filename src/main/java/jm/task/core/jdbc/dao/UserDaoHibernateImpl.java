package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private final SessionFactory sessionFactory = getSessionFactory();

    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE  IF NOT EXISTS users (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Query query1 = session.createSQLQuery(query).addEntity(User.class);
                query1.executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Query query1 = session.createSQLQuery(query).addEntity(User.class);
                query1.executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            try {
                session.beginTransaction();
                session.save(new User(name, lastName, age));
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                User user;
                if ((user = session.get(User.class, id)) != null) {
                    session.delete(user);
                }
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                List<User> list = session.createQuery("from User").getResultList();
                for (User i : list) {
                    System.out.println(i);
                }
                session.getTransaction().commit();
                return list;
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);

            }
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createQuery("delete User").executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
