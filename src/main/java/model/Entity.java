package model;

import Utilities.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.Image;

import Utilities.Direction;

public interface Entity {

    Node getNode();

    void setImage(Image image);

    Vector2D<Number> getPosition();

    void setPosition(Number x, Number y);

    Vector2D<Number> getDimension();

    void setDimension(Vector2D<Number> dimension);

    Direction getDirection();

    void setDirection(Direction direction);

    Number getSpeed();

    void setSpeed(Number speed);
    
}
