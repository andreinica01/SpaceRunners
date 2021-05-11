package GameField;

import javafx.scene.canvas.Canvas;
import java.util.Set;

import GameObjects.Entity;




public interface GameField {

    /**
     * 
     * @return the draw panel where the content is draw
     */

    Canvas getCanvas();

    /**
     * Add an Entity to the gameField
     * 
     * @param entity
     */

    void addEntity(Entity entity);

    /**
     * 
     * @return the active entities on the Gamefield
     */
    Set<Entity> getActiveEntities();

/**
 * 
 * @return Width 
 */

    Number getWidth();

    /*
     * @return Height 
     */
    Number getHeight();



}
