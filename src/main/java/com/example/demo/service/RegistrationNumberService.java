package com.example.demo.service;

import com.example.demo.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegistrationNumberService {

    private final ProfileRepository repository;

    public RegistrationNumberService(ProfileRepository repository) {
        this.repository = repository;
    }

    public String generate(String department) {

        int year = LocalDate.now().getYear();

        int number = 1;

        String regNo;

        do {

            regNo = String.format(
                    "%d-%s-%03d",
                    year,
                    department.toUpperCase(),
                    number
            );

            number++;

        } while (
                repository.existsByRegistrationNumber(regNo)
        );

        return regNo;
    }
}