package FrameManager;

import java.util.Set;

import GameField.GameField;
import GameObjects.Entity;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

public class FrameManager {

    private GameField gamefield;
    private Set<Entity> entities;

    public FrameManager(GameField gamefield) {
        this.gamefield = gamefield;
        this.entities = this.gamefield.getActiveEntities();

    }

    public void update() {

        updateBackground();

        entities.forEach(entity -> {
            updateEntityPosition(entity);
        });

    }

    private void updateEntityPosition(Entity entity) {
        switch (entity.getDirection()) {
            case UP:
                entity.setPosition(entity.getPosition().getX(),
                        entity.getPosition().getY().intValue() - entity.getSpeed().intValue());
                break;
            case DOWN:
                entity.setPosition(entity.getPosition().getX(),
                        entity.getPosition().getY().intValue() + entity.getSpeed().intValue());
                break;
            case LEFT:
                entity.setPosition(entity.getPosition().getX().intValue() - entity.getSpeed().intValue(),
                        entity.getPosition().getY());
                break;
            case RIGHT:
                entity.setPosition(entity.getPosition().getX().intValue() + entity.getSpeed().intValue(),
                        entity.getPosition().getY());
                break;
            case NONE:
                // do nothing
                break;

        }
    }

    private void updateBackground() {

        for (Node image : this.gamefield.getBackground()) {
            image.setLayoutY(image.getLayoutY() + 5);

            if (image.getLayoutY() >= this.gamefield.getHeight().intValue()) {
                image.setLayoutY(-this.gamefield.getHeight().intValue());
            }
        }

    }
}
