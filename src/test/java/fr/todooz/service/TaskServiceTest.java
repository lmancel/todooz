package fr.todooz.service;


import fr.todooz.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

public class TaskServiceTest {
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

    @After
    public void cleanDb() {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from Task").executeUpdate();

        transaction.commit();

        session.close();

        sessionFactory.close();
    }

    @Test
    public void save() {
        TaskService taskService = new TaskService();
        taskService.setSessionFactory(sessionFactory);

        taskService.save(task());
    }


    @Test
    public void delete() {
        TaskService taskService = new TaskService();
        taskService.setSessionFactory(sessionFactory);

        Task task = task();

        taskService.save(task);

        taskService.delete(task.getId());

        Session session = sessionFactory.openSession();

        Assert.assertEquals(0, session.createQuery("from Task").list().size());

        session.close();
    }


    @Test
    public void findAll() {
        TaskService taskService = new TaskService();
        taskService.setSessionFactory(sessionFactory);

        taskService.save(task());
        taskService.save(task());

        Assert.assertEquals(2, taskService.findAll().size());
    }

    @Test
    public void findByQuery() {
        TaskService taskService = new TaskService();
        taskService.setSessionFactory(sessionFactory);

        taskService.save(task());
        taskService.save(task());

        Assert.assertEquals(2, taskService.findByQuery("read").size());
        Assert.assertEquals(2, taskService.findByQuery("java").size());
        Assert.assertEquals(0, taskService.findByQuery("driven").size());
    }

    @Test
    public void count() {
        TaskService taskService = new TaskService();
        taskService.setSessionFactory(sessionFactory);

        taskService.save(task());
        taskService.save(task());

        Assert.assertEquals(2, taskService.count());
    }

    private Task task() {
        Task task = new Task();
        task.setDate(new Date());
        task.setTitle("Read Effective Java");
        task.setText("Read Effective Java before it's too late");
        task.setTags("java,java");
        return task;
    }

}
