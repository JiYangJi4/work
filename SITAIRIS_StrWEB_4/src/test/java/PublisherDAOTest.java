import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.example.Hibernate.CRUD.PublisherDAO;
import org.example.Hibernate.Publisher;
import org.hibernate.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublisherDAOTest {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private PublisherDAO publisherDao;

    @Before
    public void setup() {
        factory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        publisherDao = new PublisherDAO();
        publisherDao.setSessionFactory(factory);

        when(factory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    public void testAddPublisher() {
        when(session.save(any(Publisher.class))).thenReturn(1);
        Integer publisherID = publisherDao.addPublisher("Test Publisher");
        assertEquals(Integer.valueOf(1), publisherID);
    }

    @Test
    public void testAddPublisherWithMockSessionAndTx() {
        Session session = Mockito.mock(Session.class);
        Transaction tx = Mockito.mock(Transaction.class);

        // Создаем mock объект PublisherDAO с реальным SessionFactory
        PublisherDAO publisherDao = new PublisherDAO(createMockSessionFactoryWithTx(session, tx));

        // Задаем поведение для метода save
        when(session.save(any(Publisher.class))).thenReturn(1);
        when(session.beginTransaction()).thenReturn(tx);

        // Вызываем метод addPublisher
        Integer publisherID = publisherDao.addPublisher("Test Publisher");

        // Проверяем, что возвращенный идентификатор соответствует ожидаемому значению
        assertEquals(Integer.valueOf(1), publisherID);
    }

    // Метод для создания mock объекта SessionFactory с Transaction
    private SessionFactory createMockSessionFactoryWithTx(Session session, Transaction tx) {
        SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(tx);
        return sessionFactory;
    }

    @Test
    public void testUpdatePublisher() {
        Publisher publisher = new Publisher("NewPublusher");
        when(session.get(Publisher.class, 1)).thenReturn(publisher);

        publisherDao.updatePublisher(1, "New Publisher Name");

        assertEquals("New Publisher Name", publisher.getName());
        verify(session).update(publisher);
    }

    @Test
    public void testDeletePublisher() {
        Publisher publisher = new Publisher("Test Publisher");
        publisher.setId(1); // Установка ID для теста
        when(session.get(Publisher.class, 1)).thenReturn(publisher);

        publisherDao.deletePublisher(1);

        verify(session).delete(publisher);
    }

    @Test
    public void testListPublishers() {
        org.hibernate.query.Query query = Mockito.mock(org.hibernate.query.Query.class);
        when(session.createQuery("FROM Publisher")).thenReturn(query);

        // Предположим, что ваш метод listPublishers использует query для получения результатов
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(new Publisher("Publisher 1"));
        publishers.add(new Publisher("Publisher 2"));
        when(query.list()).thenReturn(publishers);

        // Здесь вы можете добавить свой код тестирования вывода в консоль или другой проверки
    }
}