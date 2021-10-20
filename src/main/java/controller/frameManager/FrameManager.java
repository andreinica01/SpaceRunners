package controller.frameManager;

import java.util.Set;
import javafx.scene.Node;
import model.Entity;
import utilities.MagicEnumInt;
import view.gameField.GameField;

public class FrameManager {

    private GameField gameField;
    private Set<Entity> entities;

    /**
     * Constructor.
     * 
     * @param gameField
     */
    public FrameManager(final GameField gameField) {
        this.gameField = gameField;
        this.entities = this.gameField.getActiveEntities();
    }

    /**
     * Game update.
     */
    public void update() {
        this.updateBackground();
        this.gameField.getSoundManager().playPlayerMovementSound();
        this.entities.forEach(entity -> updateEntityPosition(entity));
    }

    /**
     * Entity update.
     * 
     * @param entity
     */
    private void updateEntityPosition(final Entity entity) {
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
            break;
        default:
        }
    }

    /**
     * BackGround update.
     */
    private void updateBackground() {
        for (Node image : this.gameField.getBackground()) {
            image.setLayoutY(image.getLayoutY() + MagicEnumInt.FOUR.getValue());

            if (image.getLayoutY() >= this.gameField.getHeight().intValue()) {
                image.setLayoutY(-this.gameField.getHeight().intValue());
            }
        }
    }
}
