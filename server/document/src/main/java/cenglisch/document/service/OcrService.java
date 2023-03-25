package cenglisch.document.service;


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public final class OcrService {

    @Autowired
    private Tesseract tesseract;

    public String extractTextFromFile(final File file) {
        try {
            return tesseract.doOCR(file);
        } catch (TesseractException e) {
            return null;
        }
    }
}
