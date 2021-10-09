package utilities;

public class Vector2DImpl<NumType extends Number> implements Vector2D<NumType> {

	private static final int HASH_NUMBER = 31;
	private NumType x;
	private NumType y;

	/**
	 * Constructor used to create a 2D Vector from x, y coordinates.
	 * 
	 * @param x coordinate.
	 * @param y coordinate.
	 */
	public Vector2DImpl(final NumType x, final NumType y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor used to create a 2D Vector from a NumType vector.
	 * 
	 * @param vector.
	 */
	public Vector2DImpl(final Vector2D<NumType> vector) {
		this.setFromVector(vector);
	}

	@Override
	public final NumType getX() {
		return this.x;
	}

	@Override
	public final NumType getY() {
		return this.y;
	}

	@Override
	public final void setX(final NumType x) {
		this.x = x;
	}

	@Override
	public final void setY(final NumType y) {
		this.y = y;
	}

	@Override
	public final void setFromVector(final Vector2D<NumType> vector) {
		this.x = vector.getX();
		this.y = vector.getY();
	}

	/**
	 * Returns the euclidean distance between two 2D vectors.
	 * 
	 * @param v1 vector.
	 * @param v2 vector.
	 * @return euclidean distance.
	 */
	public static double getDistance(final Vector2D<? extends Number> v1, final Vector2D<? extends Number> v2) {
		final double x = v1.getX().doubleValue() - v2.getX().doubleValue();
		final double y = v1.getY().doubleValue() - v2.getY().doubleValue();

		return Math.sqrt(Math.pow(x, MagicEnumInt.TWO.getValue()) + Math.pow(y, MagicEnumInt.TWO.getValue()));
	}

	@Override
	public final int hashCode() {
		final int prime = HASH_NUMBER;
		int result = MagicEnumInt.ONE.getValue();

		result = prime * result + ((this.x == null) ? MagicEnumInt.ZERO.getValue() : this.x.hashCode());
		result = prime * result + ((this.y == null) ? MagicEnumInt.ZERO.getValue() : this.y.hashCode());

		return result;
	}

	@Override
	public final boolean equals(final Object obj) {

		Vector2DImpl<?> other = (Vector2DImpl<?>) obj;

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		if (this.x == null) {
			if (other.x != null) {
				return false;
			}
		} else if (!this.x.equals(other.x)) {
			return false;
		}

		if (this.y == null) {
			if (other.y != null) {
				return false;
			}
		} else if (!this.y.equals(other.y)) {
			return false;
		}

		return true;
	}
}
