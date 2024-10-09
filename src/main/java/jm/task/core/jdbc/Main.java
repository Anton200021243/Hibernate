package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("app.log", true); // true для добавления в файл
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            LOGGER.severe("Failed to set up file handler: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LOGGER.info("Start main method");

        UserService userService = new UserServiceImpl();
        userService.saveUser("ivan", "ivanov", (byte) 32);

//        User user1 = new User("egor", "letov", (byte) 25);
//        User user2 = new User("max", "korzh", (byte) 32);
//        User user3 = new User("nilletto", "nilettov", (byte) 54);
//        User user4 = new User("john", "connor", (byte) 23);
//
//        userService.createUsersTable();
//
//        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
//        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
//        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
//        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
//
//        userService.getAllUsers().forEach(System.out::println);
//
//        userService.cleanUsersTable();
//
//        userService.dropUsersTable();

        Util newSessionFactory = new Util();
        SessionFactory sessionFactory = newSessionFactory.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        User user1 = new User("test1", "t", (byte) 39);
        User user2 = new User("test2", "e", (byte) 32);
        User user3 = new User("test3", "s", (byte) 26);

        session.save(user1);
        session.save(user2);
        session.save(user3);

        session.getTransaction().commit();

        sessionFactory.close();

        LOGGER.info("End main method");
    }
}
