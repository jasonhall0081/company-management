package cenglisch.document.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("v1/document/storage")
public final class StorageController {

    @Autowired
    private StorageService storageService;

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
