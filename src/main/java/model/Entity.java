package model;

import Utilities.Vector2D;
import javafx.scene.Node;
import Utilities.Direction;

public interface Entity {

    Node getNode();

    void setImage(String path);

    Vector2D<Number> getPosition();

    void setPosition(Number x, Number y);

    Vector2D<Number> getDimension();

    void setDimension(Vector2D<Number> dimension);

    Direction getDirection();

    void setDirection(Direction direction);

    Number getSpeed();

    void setSpeed(Number speed);

}
