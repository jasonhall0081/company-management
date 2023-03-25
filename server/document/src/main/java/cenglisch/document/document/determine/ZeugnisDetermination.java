package cenglisch.document.document.determine;

public final class ZeugnisDetermination implements Determination {


    public boolean determine(final String fileContent) {
        return fileContent.contains("ZEUGNIS");
    }

    public DocumentType getDocumentType() {
        return DocumentType.ZEUGNIS;
    }
}
