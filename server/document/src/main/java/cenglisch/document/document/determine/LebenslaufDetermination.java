package cenglisch.document.document.determine;

public final class LebenslaufDetermination implements Determination {

    public boolean determine(final String fileContent) {
        return fileContent.contains("LEBENSLAUF");
    }

    public DocumentType getDocumentType() {
        return DocumentType.LEBENSLAUF;
    }
}
