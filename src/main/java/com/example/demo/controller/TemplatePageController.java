package com.example.demo.controller;

import com.example.demo.model.Template;
import com.example.demo.service.TemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TemplatePageController {

    private final TemplateService templateService;

    public TemplatePageController(
            TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("/templates")
    public String templates(Model model) {

        model.addAttribute(
                "templates",
                templateService.getAllTemplates()
        );

        return "idcard-template";
    }

    @GetMapping("/templates/new")
    public String newTemplate(Model model) {

        model.addAttribute(
                "template",
                new Template()
        );

        return "template-form";
    }

    @GetMapping("/templates/edit/{id}")
    public String editTemplate(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "template",
                templateService.getTemplateById(id)
        );

        return "template-form";
    }

    @PostMapping("/templates/save")
    public String saveTemplate(
            Template template) {

        if (template.getId() == null) {

            templateService.createTemplate(template);

        } else {

            templateService.updateTemplate(
                    template.getId(),
                    template
            );
        }

        return "redirect:/templates";
    }

    @GetMapping("/templates/delete/{id}")
    public String deleteTemplate(
            @PathVariable Long id) {

        templateService.deleteTemplate(id);

        return "redirect:/templates";
    }
}