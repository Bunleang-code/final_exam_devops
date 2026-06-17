package com.example.demo.model;

import java.util.UUID;

public class ProfileBuilder {

    private ProfileBuilder() {
    }

    public static Profile createStudent(String fullName) {

        Profile profile = new Profile();

        profile.setUuid(UUID.randomUUID().toString());
        profile.setFullName(fullName);
        profile.setType(ProfileType.STUDENT);
        profile.setBarcodeType(BarcodeType.CODE_128);

        return profile;
    }

    public static Profile createEmployee(String fullName) {

        Profile profile = new Profile();

        profile.setUuid(UUID.randomUUID().toString());
        profile.setFullName(fullName);
        profile.setType(ProfileType.EMPLOYEE);
        profile.setBarcodeType(BarcodeType.CODE_128);

        return profile;
    }

    public static Profile createUser(String fullName) {

        Profile profile = new Profile();

        profile.setUuid(UUID.randomUUID().toString());
        profile.setFullName(fullName);
        profile.setType(ProfileType.USER);
        profile.setBarcodeType(BarcodeType.CODE_128);

        return profile;
    }
}