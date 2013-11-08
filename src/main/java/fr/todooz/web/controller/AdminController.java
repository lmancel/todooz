package fr.todooz.web.controller;


import fr.todooz.domain.Task;
import fr.todooz.service.TaskService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {

    @Inject
    TaskService taskService;

    @InitBinder
    public void initBinder(DataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatter, true));
    }

    @RequestMapping("/add")
    public String add(Model model) {
        // on injecte une Task vierge dans le modèle
        model.addAttribute("task", new Task());
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String post(@ModelAttribute Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        }

        taskService.save(task);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));

        return "edit";
    }
}
