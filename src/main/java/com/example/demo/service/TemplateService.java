package com.example.demo.service;

import com.example.demo.model.Template;
import com.example.demo.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    private final TemplateRepository repository;

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public List<Template> getAllTemplates() {
        return repository.findAll();
    }

    public Template getTemplateById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    public Template createTemplate(Template template) {
        return repository.save(template);
    }

    public Template updateTemplate(Long id, Template request) {

        Template template = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        template.setCode(request.getCode());
        template.setName(request.getName());
        template.setOrganizationName(request.getOrganizationName());
        template.setLayout(request.getLayout());
        template.setPrimaryColor(request.getPrimaryColor());
        template.setSecondaryColor(request.getSecondaryColor());
        template.setTextColor(request.getTextColor());
        template.setTagline(request.getTagline());

        return repository.save(template);
    }

    public void deleteTemplate(Long id) {
        repository.deleteById(id);
    }
}