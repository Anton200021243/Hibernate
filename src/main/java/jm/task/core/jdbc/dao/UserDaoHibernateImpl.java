package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Util newSessionFactory = new Util();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            //code
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            //code
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            //code
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            //code
            session.getTransaction().commit();
        }

        return null;
    }

    @Override
    public void cleanUsersTable() {
        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            //code
            session.getTransaction().commit();
        }
    }
}
