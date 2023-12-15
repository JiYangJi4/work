package org.example.Hibernate.CRUD;

import javax.persistence.*;

import javax.persistence.*;

import org.example.Hibernate.Publisher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Iterator;
import java.util.List;

public class PublisherDAO {
    private static SessionFactory factory;

    public PublisherDAO() {}
    public PublisherDAO(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }
    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        PublisherDAO publisherDAO = new PublisherDAO();

        /* Add few publisher records in database */
        System.out.println("--------Add P1, P2---------");
        Integer publisherID1 = publisherDAO.addPublisher("Publisher 1");
        Integer publisherID2 = publisherDAO.addPublisher("Publisher 2");
        System.out.println("-----------------");
        /* List down all the publishers */
        publisherDAO.listPublishers();

        /* Update publisher's records */
        System.out.println("---------Update P1--------");
        publisherDAO.updatePublisher(publisherID1, "New Publisher Name");
        System.out.println("-----------------");
        System.out.println("--------Delete P2---------");
        /* Delete an publisher from the database */
        publisherDAO.deletePublisher(publisherID2);

        System.out.println("-------View P----------");
        /* List down new list of the publishers */
        publisherDAO.listPublishers();

        /*publisherDAO.deletePublisher(16);
        publisherDAO.deletePublisher(18);
        publisherDAO.deletePublisher(20);
        publisherDAO.deletePublisher(22);*/
    }
    /* Method to CREATE a publisher in the database */
    public Integer addPublisher(String publisherName) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer publisherID = null;
        try {
            tx = session.beginTransaction();
            Publisher publisher = new Publisher(publisherName);
            publisherID = (Integer) session.save(publisher);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (tx != null) {
                session.close();
            }
        }
        return publisherID;
    }

    /* Method to READ all the publishers */
    public void listPublishers() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Publisher> publishers = session.createQuery("FROM Publisher").list();
            for (Iterator<Publisher> iterator = publishers.iterator(); iterator.hasNext(); ) {
                Publisher publisher = iterator.next();
                System.out.println("Publisher ID: " + publisher.getPublisherID() + ", Name: " + publisher.getName());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE publisher */
    public void updatePublisher(Integer publisherID, String publisherName) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Publisher publisher = session.get(Publisher.class, publisherID);
            publisher.setName(publisherName);
            session.update(publisher);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE a publisher from the records */
    public void deletePublisher(Integer publisherID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Publisher publisher = session.get(Publisher.class, publisherID);
            session.delete(publisher);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}