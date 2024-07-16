package entities.factories;
import entities.Entity;
/**
 * Public interface for entity factory.
 */
public interface EntityFactory {
    /**
     * Create the entity.
     * @return the new entity.
     */
    Entity createEntity();
}
