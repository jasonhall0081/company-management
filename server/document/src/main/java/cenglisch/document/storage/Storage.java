package cenglisch.document.storage;

import cenglisch.document.document.determine.DocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import ja final karta.persistence.Enumerated;
import ja final karta.persistence.Id;
import ja final karta.persistence.Lob;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "storage")
public final class Storage {
    @Id
    private String id;
    @Column
    private String reference;
    @Column
    private String path;
    @Lob
    @Column
    private String content;
    @Column
    @Enumerated
    private DocumentType documentType;

    public Storage() {
    }

    public Storage(
        final String reference,
        final String path,
        final String content,
        final DocumentType documentType
    ) {
        //TODO generate UUID over hibernate
        this.id = UUID.randomUUID().toString().toUpperCase();
        this.reference = reference;
        this.path = path;
        this.content = content;
        this.documentType = documentType;
    }

    public String getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }
}
