package org.example;

import javax.persistence.*;
import org.example.Hibernate.Author;
import org.example.Hibernate.Title;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainHibernate {
    public static void main(String[] args) {
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.Persistence.xml", "/META-INF/persistence.xml");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("YourPersistenceUnitName", properties);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        // Пример чтения автора по идентификатору
        Author author = entityManager.find(Author.class, 1);
        System.out.println("Найденный автор: " + author.getFirstName() + " " + author.getLastName());

        // Пример выполнения запроса на выборку всех книг данного автора
        Query query = entityManager.createQuery("SELECT t FROM Title t JOIN AuthorISBN ai ON t.isbn = ai.title.isbn JOIN Author a ON ai.author.authorID = a.authorID WHERE a.authorID = :authorID");
        query.setParameter("authorID", author.getAuthorID());
        List<Title> titles = query.getResultList();
        for (Title title : titles) {
            System.out.println("Книга: " + title.getTitle());
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}