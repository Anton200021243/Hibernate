package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbcProject";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "qwe";

    private Connection connection;
    private SessionFactory sessionFactory;

    public void createJDBCConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getJDBCConnection() {
        createJDBCConnection();
        return connection;
    }

    public void configureHibernate() {
        sessionFactory = new Configuration()
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.username", USERNAME)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        configureHibernate();
        return sessionFactory;
    }
}
