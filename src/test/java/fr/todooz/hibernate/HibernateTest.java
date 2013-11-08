package fr.todooz.hibernate;


import fr.todooz.domain.Task;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class HibernateTest {

    private SessionFactory sessionFactory;

    @Before
    public void createSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyTenFiveDialect");
        configuration.setProperty("hibernate.connection.url", "jdbc:derby:target/testdb;create=true");
        configuration.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.addAnnotatedClass(Task.class);

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).buildServiceRegistry();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void saveTask() {
        Task task = new Task();

        task.setDate(new Date());
        task.setTitle("Read Effective Java");
        task.setText("Read Effective Java before it's too late");
        task.setTags("java,java");

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(task);

        transaction.commit();

        session.close();
    }

    @Test
    public void findTask() {
        saveTask();

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Task where title = :title");

        query.setString("title", "Read Effective Java");

        List<Task> tasks = query.list();

        session.close();

        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("Read Effective Java", tasks.get(0).getTitle());
    }

    @Test
    public void findTaskWithCriteria() {
        saveTask();

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Task.class);

        criteria.add(Restrictions.eq("title", "Read Effective Java"));

        List<Task> tasks = criteria.list();

        session.close();

        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("Read Effective Java", tasks.get(0).getTitle());
    }

    @After
    public void cleanDb() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from Task").executeUpdate();

        transaction.commit();

        session.close();

        sessionFactory.close();
    }
}
