package model;

import Utilities.Direction;
import Utilities.Vector2D;
import Utilities.Vector2DImpl;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class EntityImpl implements Entity {

	private Vector2D<Number> position;
	private Vector2D<Number> dimension;

	private Number speed;

	private Node entityNode;
	private Direction direction;

	private boolean canFire;

	public EntityImpl() {
		this.position = new Vector2DImpl<Number>(0, 0);
		this.dimension = new Vector2DImpl<Number>(0, 0);
		this.canFire = true;

<<<<<<< HEAD
	}
=======
    public Node getNode() {
        //this.entityNode.getBoundsInLocal().i
        return this.entityNode;
    }
>>>>>>> 6e5ccb49e1a380f84dcec45fe1910f2ef0b81ece

	public Node getNode() {
		return this.entityNode;
	}

	public void setImage(Image image) {
		this.entityNode = new ImageView(image);
		this.entityNode.setRotate(180);
	}

	public void SetDirection(Direction direction) {
		this.direction = direction;

	}

	@Override
	public Vector2D<Number> getPosition() {

		return this.position;

	}

	@Override
	public void setPosition(Number x, Number y) {

		this.position.setX(x);
		this.position.setY(y);

		this.entityNode.setTranslateX(x.intValue());
		this.entityNode.setTranslateY(y.intValue());

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

	public boolean getCanFire() {
		return canFire;
	}

	public void setCanFire(boolean canFire) {
		this.canFire = canFire;
	}
}
