package com.example.demo.service;

import com.example.demo.model.Profile;
import com.example.demo.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfileService {

    private final ProfileRepository repository;
    private final RegistrationNumberService registrationNumberService;

    public ProfileService(
            ProfileRepository repository,
            RegistrationNumberService registrationNumberService) {

        this.repository = repository;
        this.registrationNumberService = registrationNumberService;
    }

    public List<Profile> getAllProfiles() {
        return repository.findAll();
    }

    public Profile getProfileById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public Profile createProfile(Profile profile) {

        profile.setUuid(UUID.randomUUID().toString());

        if (profile.getRegistrationNumber() == null ||
                profile.getRegistrationNumber().isBlank()) {

            profile.setRegistrationNumber(
                    registrationNumberService.generate(
                            profile.getDepartment()
                    )
            );
        }

        return repository.save(profile);
    }

    public Profile updateProfile(Long id, Profile request) {

        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setFullName(request.getFullName());
        profile.setDepartment(request.getDepartment());
        profile.setTitle(request.getTitle());
        profile.setEmail(request.getEmail());
        profile.setPhone(request.getPhone());
        profile.setBloodGroup(request.getBloodGroup());
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setIssueDate(request.getIssueDate());
        profile.setExpiryDate(request.getExpiryDate());
        profile.setType(request.getType());
        profile.setBarcodeType(request.getBarcodeType());
        profile.setTemplate(request.getTemplate());

        return repository.save(profile);
    }

    public void deleteProfile(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Profile not found");
        }

        repository.deleteById(id);
    }
}