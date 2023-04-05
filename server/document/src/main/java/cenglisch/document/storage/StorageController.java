package cenglisch.document.storage;


import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/document/storage")
public final class StorageController {

    private final StorageService storageService;

    public StorageController(final StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public Collection<Storage> getStorageItems() {
        return storageService.getAllStorageItems();
    }

    @GetMapping("reference")
    public Collection<Storage> getStorageItemsByReference(@RequestParam final String reference) {
        return storageService.getStorageItemsByReference(reference);
    }
    @DeleteMapping
    public void deleteStorageItem(@RequestParam final String id) {
        storageService.deleteStorageItem(id);
    }
}
