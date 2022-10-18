package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/newlesson1";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
//////////////////////////////////////////////////////////////////////////////////

    public static SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect")
                .setProperty("hibernate.connection.username", USERNAME)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.connection.url", URL);
        SessionFactory sessionFactory = cfg.addAnnotatedClass(User.class).buildSessionFactory();
        return sessionFactory;
    }


}


