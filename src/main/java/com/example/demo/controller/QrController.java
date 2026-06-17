package com.example.demo.controller;

import com.example.demo.service.QrCodeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class QrController {

    private final QrCodeService qrCodeService;

    public QrController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(
            value = "/qr/{id}",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public byte[] generateQr(
            @PathVariable Long id) {

        return qrCodeService.generateQr(
                "Profile ID: " + id
        );
    }
}