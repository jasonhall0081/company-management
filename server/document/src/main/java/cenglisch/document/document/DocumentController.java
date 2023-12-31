package cenglisch.document.document;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("v1/document")
public final class DocumentController {
    private final DocumentApplicationService documentApplicationService;

    public DocumentController(
        final DocumentApplicationService documentApplicationService
    ) {
        this.documentApplicationService = documentApplicationService;
    }

    @PostMapping(value = "place", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentResponse placeDocument(@RequestPart("file") final MultipartFile file) {
        return documentApplicationService.determineDocumentType(file);
    }
}
