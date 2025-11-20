package com.jumpytech.QrCodeGen.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class QrcodeGenService {

    @Value("${qrcode.storage-path}")
    private String storagePath;

    public List<Path> generateAndSave(String payload, List<Integer> sizes, String prefix, ErrorCorrectionLevel ecl) throws WriterException {
        if (payload == null || payload.isBlank()) throw new IllegalArgumentException("Payload vide");

        try {
            Files.createDirectories(Path.of(storagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ecl);
        hints.put(EncodeHintType.MARGIN, 2);

        List<Path> paths = new ArrayList<>();
        for (Integer size : sizes) {
            BitMatrix matrix = new MultiFormatWriter().encode(payload, BarcodeFormat.QR_CODE, size, size, hints);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

            String filename = String.format("%s_%d.png", prefix, size);
            Path out = Path.of(storagePath, filename);
            try {
                ImageIO.write(image, "PNG", out.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            paths.add(out);
        }
        return paths;
    }

    // ✅ Méthode utilitaire pour exposer le chemin de stockage
    public String getStoragePath() {
        return storagePath;
    }
}
