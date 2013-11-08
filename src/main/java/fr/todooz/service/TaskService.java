package fr.todooz.service;

import fr.todooz.domain.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lmancel
 * Date: 08/11/13
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public interface TaskService {
    void save(Task task);

    void delete(Long id);

    List<Task> findAll();

    List<Task> findByQuery(String query);


    int count();

    List<Task> findByTag(String tag);

    @Transactional
    Task findById(Long id);
}
