package com.example.musicplayerpro.files;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DatabaseManager {

    private static final SessionFactory sessionFactory;

    static
    {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static List<User> getAllUsers()
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    public static User getUserById(int userId)
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.get(User.class, userId);
        }
    }
    public static User getUserByUserName(String userName)
    {
        try (Session session = sessionFactory.openSession())
        {
            Query<User> query = session.createQuery("FROM User WHERE userName = :userName", User.class);
            query.setParameter("userName", userName);
            return query.uniqueResult();
        }
    }

    public static void updateUser(User user)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        }
    }
    public static void deleteUser(User user)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

    public static void saveUser(User user)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }

}
