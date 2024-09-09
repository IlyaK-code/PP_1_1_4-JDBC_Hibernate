package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pp_jdbc";
    private static final String NAME = "root";
    private static final String PASSWORD = "qwerty";
    private static Connection CON;

    public static Connection getConnection() {
        try {
            CON = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Неудалось подключиться к БД!");
        }
        return CON;
    }

    public static SessionFactory getSessionHibernate() {
        SessionFactory sessionFactory = new Configuration()
                .setProperties(getProperties())
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put(Environment.URL, URL);
        properties.put(Environment.USER, NAME);
        properties.put(Environment.PASS, PASSWORD);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "");
        return properties;
    }
}
