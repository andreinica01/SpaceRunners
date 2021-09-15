package model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import utilities.Direction;
import utilities.Vector2D;

public interface Entity {

    /**
     * @return the node.
     */
    Node getNode();

    /**
     * Set the image for the entity.
     * @param image.
     */
    void setImage(Image image);

    /**
     * @return entity position.
     */
    Vector2D<Number> getPosition();

    /**
     * Set new entity position.
     * @param x.
     * @param y.
     */
    void setPosition(Number x, Number y);

    /**
     * @return return dimension of entity.
     */
    Vector2D<Number> getDimension();

    /**
     * Set entity dimension.
     * @param dimension
     */
    void setDimension(Vector2D<Number> dimension);

    /**
     * @return entity direction.
     */
    Direction getDirection();

    /**
     * Set entity direction.
     * @param direction
     */
    void setDirection(Direction direction);

    /**
     * @return entity speed.
     */
    Number getSpeed();

    /**
     * Set entity speed.
     * @param speed
     */
    void setSpeed(Number speed);

    /**
     * Set horizontal speed.
     * @param speed
     */
    void setHorizontalSpeed(Number speed);

    /**
     * @return entity horizontal speed.
     */
    Number getHorrizontalSpeed();

    /**
     * Inverts entity direction.
     */
    void invertDirection();
}
