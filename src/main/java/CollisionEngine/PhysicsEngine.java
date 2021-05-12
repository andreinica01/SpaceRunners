package CollisionEngine;
import java.util.Set;

import GameObjects.Entity;
import javafx.util.Pair;

public interface PhysicsEngine {

    /**
     * return a set with all the enemyShips hitted by a bullet, with the corresponding damage,
     * if it was hitted by more bullets, must be reported the total damage
     * example
     * Ship1 Damage=5
     * Ship2 Damage=1
     */
    Set<Pair<Entity,Number>> damagedEnemies();

    /**
     * 
     * @return check if the player was hittede by a bullet
     */
    boolean isPlayerHitted();

    /**
     * 
     * @return if the player is at the margin of the canvas
     */
    boolean isPlayeratMargin();

}
