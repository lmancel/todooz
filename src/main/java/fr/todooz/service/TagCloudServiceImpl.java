package fr.todooz.service;


import fr.todooz.util.TagCloud;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.inject.Inject;
import java.util.List;

@Service
public class TagCloudServiceImpl implements TagCloudService {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public TagCloud buildTagCloud() {
        TagCloud tagCloud = new TagCloud();

        for (String tag : findTags()) {
            tagCloud.add(tag.split(","));
        }
        return tagCloud;
    }

    private List<String> findTags() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select tags from Task").list();
    }
}
