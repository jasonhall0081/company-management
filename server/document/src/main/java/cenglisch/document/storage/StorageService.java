package cenglisch.document.storage;

import cenglisch.document.document.determine.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public final class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public void deleteStorageItem(final String storageId) {
        storageRepository.deleteById(storageId);
    }

    public Storage storeFileContent(
            final String reference,
            final DocumentType documentType,
            final String filePath,
            final String fileContent
    ) {
        return storageRepository.saveAndFlush(
                new Storage(
                        reference,
                        filePath,
                        fileContent,
                        documentType
                )
        );
    }

    public Collection<Storage> getStorageItemsByReference(final String reference) {
        return storageRepository.findByReference(reference);
    }

    public Collection<Storage> getAllStorageItems() {
        return storageRepository.findAll();
    }
}
