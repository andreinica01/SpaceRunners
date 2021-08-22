package controller.frameManager;

import java.util.Set;

import Utilities.Parameters;
import javafx.scene.Node;
import model.Entity;
import view.gameField.GameField;

public class FrameManager {

	private GameField gamefield;
	private Set<Entity> entities;

	public FrameManager(GameField gamefield) {
		this.gamefield = gamefield;
		this.entities = this.gamefield.getActiveEntities();
	}

	public void update() {

        updateBackground();

        entities.forEach(entity -> 
            updateEntityPosition(entity));
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
			image.setLayoutY(image.getLayoutY() + (this.gamefield.getPlayer().getSpeed().intValue() * 2 / 3));

			if (image.getLayoutY() >= this.gamefield.getHeight().intValue()) {
				image.setLayoutY(-this.gamefield.getHeight().intValue());
			}
		}

	}
}
