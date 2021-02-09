package ua.mainacademy.testsutil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.mainacademy.factory.impl.PostgresSessionFactory;

public class ClearTestDB {
    private static PostgresSessionFactory postgresSessionFactory = new PostgresSessionFactory();

    public static void clearDb() {

        SessionFactory sessionFactory = postgresSessionFactory.getHibernateSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from OrderItem ").executeUpdate();
        session.createQuery("delete from Order ").executeUpdate();
        session.createQuery("delete from User ").executeUpdate();
        session.createQuery("delete from Item ").executeUpdate();
        transaction.commit();
        session.close();
    }
}
