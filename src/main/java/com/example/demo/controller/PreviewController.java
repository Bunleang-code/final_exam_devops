package com.example.demo.controller;

import com.example.demo.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PreviewController {

    private final ProfileService profileService;

    public PreviewController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/preview/{id}")
    public String preview(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "profile",
                profileService.getProfileById(id)
        );

        return "preview";
    }
}