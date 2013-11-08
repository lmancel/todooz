package fr.todooz.web.controller;


import fr.todooz.domain.Task;
import fr.todooz.service.TagCloudService;
import fr.todooz.service.TaskService;
import fr.todooz.web.servlet.DummyData;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    @Inject
    private TaskService taskService;

    @Inject
    private TagCloudService tagCloudService;

    @RequestMapping({"/","/index", "/index.html"})
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("tags", tagCloudService.buildTagCloud());
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world";
    }

    @RequestMapping("/search")
    public String search(String query, Model model) {
        model.addAttribute("query", taskService.findByQuery(query));
        return "index";

    }

    @RequestMapping("/tag/{tag}")
    public String tag(@PathVariable String tag, Model model) {
        model.addAttribute("tags", taskService.findByTag(tag));
        return "index";
    }

    @PostConstruct
    public void bootstrap() {
        if (taskService.count() == 0) {
            for (int i = 0; i < 3; i++) {
                taskService.save(buildTask("test"));
            }
        }
    }
    private Task buildTask(String tags) {
        Task task = new Task();

        task.setDate(new Date());
        task.setTitle("Read Effective Java");
        task.setText("Read Effective Java before it's too late");
        task.setTags(tags);

        return task;
    }
}
