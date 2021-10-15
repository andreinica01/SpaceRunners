package utilities;

/**
 * Interface that model a 2D Vector that is used to get and set position of all
 * the elements.
 * 
 * @param <NumType>
 */
public interface Vector2D<NumType extends Number> {

    /**
     * Returns the x coordinate of the vector.
     * 
     * @return x coordinate.
     */
    NumType getX();

    /**
     * Returns the y coordinate of the vector.
     * 
     * @return y coordinate.
     */
    NumType getY();

    /**
     * Sets the x coordinate of the vector.
     * 
     * @param x.
     */
    void setX(NumType x);

    /**
     * Sets the y coordinate of the vector.
     * 
     * @param y.
     */
    void setY(NumType y);

    /**
     * Sets the coordinates of a vector equals to another vector.
     * 
     * @param vector to copy coordinates from.
     */
    void setFromVector(Vector2D<NumType> vector);
}
