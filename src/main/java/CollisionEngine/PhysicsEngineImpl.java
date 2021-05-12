package CollisionEngine;

import GameField.GameField;
import GameObjects.Entity;
import javafx.util.Pair;

import java.util.Set;



public class PhysicsEngineImpl implements PhysicsEngine {

    private GameField gamefield;

    public PhysicsEngineImpl(GameField gamefield) {
        this.gamefield = gamefield;

    }

    @Override
    public Set<Pair<Entity, Number>> damagedEnemies() {
       
        return null;
    }

    @Override
    public boolean isPlayeratMargin() {
     
        return false;
    }

   

}
