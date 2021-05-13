package FrameManager;
import java.util.Set;

import GameField.GameField;
import GameObjects.Entity;



public class FrameManager {

    private GameField gamefield;
    private Set<Entity> entities;

    public FrameManager(GameField gamefield) {
        this.gamefield = gamefield;
        this.entities = this.gamefield.getActiveEntities();

    }

    public void update() {

        entities.forEach(entity->{
            updateEntityPosition(entity);
        });

    }

    private void updateEntityPosition(Entity entity) {
        switch (entity.getDirection()) {
            case UP:
                entity.setPosition(entity.getPosition().getX(),
                        entity.getPosition().getY().intValue() + entity.getSpeed().intValue());
                break;
            case DOWN:
                entity.setPosition(entity.getPosition().getX(),
                        entity.getPosition().getY().intValue() - entity.getSpeed().intValue());
                break;
            case LEFT:
                entity.setPosition(
                        entity.getPosition().getX().intValue() - entity.getSpeed().intValue(),
                                entity.getPosition().getY());
                break;
            case RIGHT:
                entity.setPosition(
                        entity.getPosition().getX().intValue() + entity.getSpeed().intValue(),
                                entity.getPosition().getY());
                break;

        }
    }

}
