package fr.todooz.service;

import fr.todooz.domain.Task;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Task task) {
        Session session = sessionFactory.getCurrentSession();

        session.save(task);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();

        session.createQuery("delete from Task where id = :id")
            .setLong("id", id)
            .executeUpdate();

    }


    @Override
    @Transactional
    public List<Task> findAll() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Task");

        List<Task> tasks = query.list();

        return tasks;
    }

    @Override
    @Transactional
    public List<Task> findByQuery(String query) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Task.class);
        criteria.add(Restrictions.ilike("title", query, MatchMode.ANYWHERE));

        return criteria.list();
    }

    @Override
    @Transactional
    public List<Task> findByTag(String tag) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Task.class);
        criteria.add(Restrictions.ilike("tags", tag, MatchMode.ANYWHERE));

        return criteria.list();
    }

    @Override
    @Transactional
    public int count() {
        return this.findAll().size();

    }

}
