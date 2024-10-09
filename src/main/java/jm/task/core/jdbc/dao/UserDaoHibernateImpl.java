package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
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

            session.createSQLQuery("create table if not exists User(\n" +
                    "                 id bigint primary key auto_increment,\n" +
                    "                 name varchar(255),\n" +
                    "                 lastName varchar(255),\n" +
                    "                 age tinyint\n" +
                    "             )").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.createSQLQuery("drop table if exists User").executeUpdate();

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

            session.remove(session.get(User.class, id));
            session.delete(session.get(User.class, id));

            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            users = session.createQuery("from User").getResultList();

            session.getTransaction().commit();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(SessionFactory sessionFactory = newSessionFactory.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("delete from User").executeUpdate();

            session.getTransaction().commit();
        }
    }
}
