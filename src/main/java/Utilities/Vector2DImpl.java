package Utilities;

public class Vector2DImpl<NumType extends Number> implements Vector2D<NumType> {

  private NumType x;
  private NumType y;

  /**
   * Constructor used to create a 2D Vector from x, y coordinates
   * @param x coordinate
   * @param y coordinate
   */
  public Vector2DImpl(final NumType x, final NumType y) {
    this.x = x;
    this.y = y;
  }

  public Vector2DImpl(final Vector2D<NumType> vector) {
    setFromVector(vector);
  }

  @Override
  public NumType getX() {
    return this.x;
  }

  @Override
  public NumType getY() {
    return this.y;
  }

  @Override
  public void setX(NumType x) {
    this.x = x;
  }

  @Override
  public void setY(NumType y) {
    this.y = y;
  }

  @Override
  public void setFromVector(Vector2D<NumType> vector) {
    this.x = vector.getX();
    this.y = vector.getY();
  }

  /**
   * Returns the euclidean distance between two 2D vectors
   * @param v1 vector
   * @param v2 vector
   * @return euclidean distance
   */
  public static double getDistance(
    final Vector2D<? extends Number> v1,
    final Vector2D<? extends Number> v2
  ) {
    final double x = v1.getX().doubleValue() - v2.getX().doubleValue();
    final double y = v1.getY().doubleValue() - v2.getY().doubleValue();
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((x == null) ? 0 : x.hashCode());
    result = prime * result + ((y == null) ? 0 : y.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Vector2DImpl<?> other = (Vector2DImpl<?>) obj;
    if (x == null) {
      if (other.x != null) return false;
    } else if (!x.equals(other.x)) return false;
    if (y == null) {
      if (other.y != null) return false;
    } else if (!y.equals(other.y)) return false;
    return true;
  }
}
