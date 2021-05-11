package PhysicsEngine;
import java.util.List;

public interface PysicsEngine {

    /**
     * return a list of all the bullets that have collided with enemy ships and the
     * corresponding ship
     */
    List getAtiveCollisions();

    /**
     * 
     * @return whether the player was hitted by a bullet
     */
    boolean isPlayerHitted();

    /**
     * 
     * @return if the player is at the margin of the canvas
     */
    boolean isPlayeratMargin();

}
