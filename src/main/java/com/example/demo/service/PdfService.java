package com.example.demo.service;

import com.example.demo.model.Profile;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    private final ProfileService profileService;

    public PdfService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public byte[] generatePdf(Long id) {

        Profile profile =
                profileService.getProfileById(id);

        try {

            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();

            PdfWriter writer =
                    new PdfWriter(output);

            PdfDocument pdf =
                    new PdfDocument(writer);

            Document document =
                    new Document(pdf);

            document.add(
                    new Paragraph("ID CARD")
            );

            document.add(
                    new Paragraph(
                            "Name: " +
                            profile.getFullName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Registration Number: " +
                            profile.getRegistrationNumber()
                    )
            );

            document.add(
                    new Paragraph(
                            "Department: " +
                            profile.getDepartment()
                    )
            );

            document.add(
                    new Paragraph(
                            "Email: " +
                            profile.getEmail()
                    )
            );

            document.close();

            return output.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}