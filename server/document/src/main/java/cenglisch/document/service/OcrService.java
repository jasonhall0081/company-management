package cenglisch.document.service;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public final class OcrService {

    private final Tesseract tesseract;

    public OcrService(final Tesseract tesseract) {
        this.tesseract = tesseract;
    }

    public String extractTextFromFile(final File file) {
        try {
            return tesseract.doOCR(file);
        } catch (TesseractException e) {
            return null;
        }
    }
}
