package cenglisch.document.document.determine;

public final class AnschreibenDetermination implements Determination {

    public boolean determine(final String fileContent) {
        return fileContent.contains("BEWERBUNG") || fileContent.contains("ANSCHREIBEN");
    }

    public DocumentType getDocumentType() {
        return DocumentType.ANSCHREIBEN;
    }
}
