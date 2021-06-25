package controller.collisionEngine;

import model.Entity;
import view.gameField.GameField;

import java.util.Set;

public class PhysicsEngineImpl implements PhysicsEngine {

    private GameField gamefield;

    public PhysicsEngineImpl(GameField gamefield) {
        this.gamefield = gamefield;

        // funzioni che puoi usare
        /*
         * this.gamefield.getPlayer(); this.gamefield.getActiveEnemyShips();
         * this.gamefield.getBonusObjects();
         * 
         * this.gamefield.getWidth(); this.gamefield.getHeight();
         * this.gamefield.getActiveBulletsShotbyEnemies();
         * this.gamefield.getActiveBulletsShotbyPlayer();
         * 
         */

    }

    @Override
    public Set<Entity> damagedEnemies() {

        return null;
    }

    @Override
    public boolean isPlayeratMargin() {
        return false;
    }

    @Override
    public boolean isPlayerHitted() {
        return false;
    }

}
