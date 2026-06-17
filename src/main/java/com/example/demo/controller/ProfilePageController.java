package com.example.demo.controller;

import com.example.demo.model.Profile;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfilePageController {

    private final ProfileService profileService;
    private final FileStorageService fileStorageService;

    public ProfilePageController(
            ProfileService profileService,
            FileStorageService fileStorageService) {

        this.profileService = profileService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/profiles")
    public String profiles(Model model) {

        model.addAttribute(
                "profiles",
                profileService.getAllProfiles()
        );

        return "profile-list";
    }

    @GetMapping("/profiles/new")
    public String newProfile(Model model) {

        model.addAttribute(
                "profile",
                new Profile()
        );

        return "profile-form";
    }

    @PostMapping("/profiles/save")
    public String saveProfile(
            Profile profile,
            @RequestParam("photo") MultipartFile photo) {

        if (!photo.isEmpty()) {

            String filename =
                    fileStorageService.store(photo);

            profile.setPhotoFileName(filename);
        }

        if (profile.getId() == null) {

            profileService.createProfile(profile);

        } else {

            profileService.updateProfile(
                    profile.getId(),
                    profile
            );
        }

        return "redirect:/profiles";
    }

    @GetMapping("/profiles/edit/{id}")
    public String editProfile(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "profile",
                profileService.getProfileById(id)
        );

        return "profile-form";
    }

    @GetMapping("/profiles/delete/{id}")
    public String deleteProfile(
            @PathVariable Long id) {

        profileService.deleteProfile(id);

        return "redirect:/profiles";
    }
}