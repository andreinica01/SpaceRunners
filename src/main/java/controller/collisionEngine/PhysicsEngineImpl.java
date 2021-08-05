package controller.collisionEngine;

import model.Entity;
import view.gameField.GameField;

import java.util.Set;

public class PhysicsEngineImpl implements PhysicsEngine {

    private GameField gamefield;
    private static final int SHIP_RADIUS = 27;
    private static final int BULLET_RADIUS = 10;

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

    private double checkDistance(final double x1, final double x2, final double y1, final double y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
}
