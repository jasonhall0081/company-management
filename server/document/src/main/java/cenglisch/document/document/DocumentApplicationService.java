package cenglisch.document.document;

import cenglisch.document.document.determine.DocumentDetermineService;
import cenglisch.document.document.determine.DocumentType;
import cenglisch.document.service.FileSystemService;
import cenglisch.document.service.OcrService;
import cenglisch.document.storage.Storage;
import cenglisch.document.storage.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public final class DocumentApplicationService {

    private final FileSystemService fileSystemService;
    private final OcrService ocrService;
    private final StorageService storageService;
    private final DocumentDetermineService documentDetermineService;

    public DocumentApplicationService(
        final FileSystemService fileSystemService,
        final OcrService ocrService,
        final StorageService storageService,
        final DocumentDetermineService documentDetermineService
    ) {
        this.fileSystemService = fileSystemService;
        this.ocrService = ocrService;
        this.storageService = storageService;
        this.documentDetermineService = documentDetermineService;
    }

    public DocumentResponse determineDocumentType(final MultipartFile file) {
        File fileSystemStoredFile = fileSystemService.storeMultipartFile(file);
        String extractedText = ocrService.extractTextFromFile(fileSystemStoredFile);
        DocumentType documentType = documentDetermineService.determineFileType(extractedText);

        Storage storage = storageService.storeFileContent(
                "cenglisch",
                documentType,
                fileSystemStoredFile.getPath(),
                extractedText
        );

        return new DocumentResponse(
                storage.getId(),
                documentType,
                file.getName()
        );
    }
}
