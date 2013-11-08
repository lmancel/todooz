package fr.todooz.service;

import fr.todooz.domain.Task;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TaskService {
    private SessionFactory sessionFactory;

    public void save(Task task) {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(task);

        transaction.commit();

        session.close();
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("delete from Task where id = :id")
            .setLong("id", id)
            .executeUpdate();

        transaction.commit();
        session.close();
    }


    public List<Task> findAll() {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Task");

        List<Task> tasks = query.list();

        session.close();

        return tasks;
    }

    public List<Task> findByQuery(String query) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Task.class);
        criteria.add(Restrictions.ilike("title", query, MatchMode.ANYWHERE));

        List<Task> task = criteria.list();
        session.close();

        return task;
    }

    public int count() {
        return this.findAll().size();

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
