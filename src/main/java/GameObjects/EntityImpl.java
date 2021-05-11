package GameObjects;

import java.io.File;

import Utilities.Direction;
import Utilities.Vector2D;
import Utilities.Vector2DImpl;
import javafx.scene.image.Image;



public class EntityImpl implements Entity {

    private Vector2D<Number> position;
    private Vector2D<Number> dimension;

    private Number speed;

    private Image image;
    private Direction direction;

    public EntityImpl()
    {
        this.position = new Vector2DImpl<Number>(0, 0);
        this.dimension = new Vector2DImpl<Number>(0, 0);


    }

    public void setImage(String pathToImage) {
       
            this.image =  new Image(new File(pathToImage).toURI().toString());
          //  this.image = this.image.getScaledInstance(this.dimension.getX().intValue(), this.dimension.getY().intValue(),);
                        

      

    }

    public Image getImage() {
        return this.image;

    }

    public void SetDirection(Direction direction) {
        this.direction = direction;

    }

    public void move() {

    }

    @Override
    public Vector2D<Number> getPosition() {

        return this.position;

    }

    @Override
    public void setPosition(Number x, Number y) {
        
        this.position.setX(x);
        this.position.setY(y);

    }

    @Override
    public Vector2D<Number> getDimension() {
        return this.dimension;
    }

    @Override
    public void setDimension(Vector2D<Number> dimension) {

        this.dimension.setFromVector(dimension);

    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;

    }

    @Override
    public Number getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(Number speed) {

        this.speed = speed;

    }

}

