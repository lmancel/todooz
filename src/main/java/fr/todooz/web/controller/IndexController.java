package fr.todooz.web.controller;


import fr.todooz.web.servlet.DummyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @RequestMapping({"/","/index", "/index.html"})
    public String index(Model model) {
        model.addAttribute("tasks", DummyData.tasks());
        return "index";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world";
    }
}
