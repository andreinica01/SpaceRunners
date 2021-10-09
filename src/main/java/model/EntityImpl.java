package model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.Direction;
import utilities.Vector2D;
import utilities.Vector2DImpl;

public abstract class EntityImpl implements Entity {

	private Vector2D<Number> position;
	private Vector2D<Number> dimension;

	private Number speed;
	private Number horrizontalSpeed;

	private Node entityNode;
	private Direction direction;

	private boolean canFire;

	/**
	 * Constructor.
	 */
	public EntityImpl() {
		this.position = new Vector2DImpl<Number>(0, 0);
		this.dimension = new Vector2DImpl<Number>(0, 0);
		this.canFire = true;
	}

	@Override
	public final Node getNode() {
		return this.entityNode;
	}

	@Override
	public final void setImage(final Image image) {
		this.entityNode = new ImageView(image);
		this.entityNode.setRotate(180);
	}

	@Override
	public final Vector2D<Number> getPosition() {
		return this.position;
	}

	@Override
	public final void setPosition(final Number x, final Number y) {
		this.position.setX(x);
		this.position.setY(y);

		this.entityNode.setTranslateX(x.intValue());
		this.entityNode.setTranslateY(y.intValue());
	}

	@Override
	public final Vector2D<Number> getDimension() {
		return this.dimension;
	}

	@Override
	public final void setDimension(final Vector2D<Number> dimension) {
		this.dimension.setFromVector(dimension);
	}

	@Override
	public final Direction getDirection() {
		return this.direction;
	}

	@Override
	public final void setDirection(final Direction direction) {
		this.direction = direction;
	}

	@Override
	public final void invertDirection() {
		switch (this.direction) {
		case DOWN:
			this.direction = Direction.UP;
			break;
		case LEFT:
			this.direction = Direction.RIGHT;
			break;
		case RIGHT:
			this.direction = Direction.LEFT;
			break;
		case UP:
			this.direction = Direction.DOWN;
			break;
		default:
			break;
		}
	}

	@Override
	public final Number getSpeed() {
		return this.speed;
	}

	@Override
	public final void setSpeed(final Number speed) {
		this.speed = speed;
	}

	@Override
	public final void setHorizontalSpeed(final Number speed) {
		this.horrizontalSpeed = speed;
	}

	@Override
	public final Number getHorrizontalSpeed() {
		return this.horrizontalSpeed;
	}

	/**
	 * @return if the entity can fire.
	 */
	public boolean getCanFire() {
		return this.canFire;
	}

	/**
	 * Set the possibility to fire or not.
	 * 
	 * @param canFire.
	 */
	public void setCanFire(final boolean canFire) {
		this.canFire = canFire;
	}
}
