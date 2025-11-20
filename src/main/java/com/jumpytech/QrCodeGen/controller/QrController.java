package com.jumpytech.QrCodeGen.controller;

import com.jumpytech.QrCodeGen.dto.QrRequest;
import com.jumpytech.QrCodeGen.dto.QrResponse;
import com.jumpytech.QrCodeGen.Service.*;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/qrcode")
public class QrController {

    private final QrcodeGenService qrService;

    public QrController(QrcodeGenService qrService) {
        this.qrService = qrService;
    }

    @PostMapping
    public QrResponse generate(@RequestBody QrRequest request) throws WriterException, IOException {
        // Appel au service pour générer et sauvegarder les QR codes
        List<Path> paths = qrService.generateAndSave(
            request.payload(),
            request.sizes(),
            request.prefix(),
            ErrorCorrectionLevel.valueOf(request.ecl())
        );

        // Transformer les Path en chemins relatifs utilisables par le front
        List<String> files = paths.stream()
                .map(path -> "/api/qrcode/files/" + path.getFileName().toString())
                .collect(Collectors.toList());

        return new QrResponse(request.payload(), files);
    }

    // Endpoint pour servir les fichiers générés
    @GetMapping("/files/{filename}")
    public org.springframework.core.io.Resource getFile(@PathVariable String filename) throws IOException {
        Path filePath = Path.of(qrService.getStoragePath(), filename);
        return new org.springframework.core.io.FileSystemResource(filePath);
    }
}
