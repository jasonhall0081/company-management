package cenglisch.domain.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<Entity, Identifier> {
    List<Entity> findAll();
    Optional<Entity> find(Identifier id);
    Entity save(Entity entity);
    void remove(Entity entity);

    default String generateId() {
        return UUID.randomUUID().toString().toUpperCase();
    }
}
