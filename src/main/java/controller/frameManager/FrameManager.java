package controller.frameManager;

import java.util.Set;
import javafx.scene.Node;
import model.Entity;
import utilities.VariousMagicNumbers;
import view.gameField.GameField;

public class FrameManager {

    private GameField gamefield;
    private Set<Entity> entities;

    /**
     * Constructor.
     * @param gamefield
     */
    public FrameManager(final GameField gamefield) {
        this.gamefield = gamefield;
        this.entities = this.gamefield.getActiveEntities();
    }

    /**
     * Game update.
     */
    public void update() {
        this.updateBackground();
        this.gamefield.getSoundManager().playPlayerMovementSound();
        this.entities.forEach(entity -> updateEntityPosition(entity));
    }

    /**
     * Entity update.
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
        for (Node image : this.gamefield.getBackground()) {
            image.setLayoutY(image.getLayoutY() + VariousMagicNumbers.FOUR);

            if (image.getLayoutY() >= this.gamefield.getHeight().intValue()) {
                image.setLayoutY(-this.gamefield.getHeight().intValue());
            }
        }
    }
}
