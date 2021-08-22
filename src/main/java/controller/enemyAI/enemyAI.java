package controller.enemyAI;

import java.util.Random;

import Utilities.*;
import model.ship.SpaceShip;
import view.SoundManager.SoundManager;
import view.gameField.GameField;

/**
 * a class to manage the movement of the enemy ships
 */
public class enemyAI {

    private GameField gamefield;
    long interval = 999999999;
    long startTime;
    Random rnd;


    public enemyAI(GameField gamefield) {
        this.gamefield = gamefield;
        startTime = System.nanoTime();
        rnd = new Random();
    }

    public void update() {
        if (System.nanoTime() - startTime > interval) {
            SpaceShip enemyship = new SpaceShip();

            enemyship.setDimension(new Vector2DImpl<Number>(50, 50));
            enemyship.setImage(Parameters.enemyImage);
            enemyship.setSpeed(10);
            enemyship.setDirection(Direction.DOWN);
           
            enemyship.getNode().setRotate(0);

            enemyship.getNode().setScaleX(0.15);
            enemyship.getNode().setScaleY(0.15);

            enemyship.setPosition(-rnd.nextInt(400), -300 + Parameters.HUD_HEIGHT);

    
            this.gamefield.addEnemyShip(enemyship);

            SoundManager.playShipPassing();

            startTime = System.nanoTime();
        }

    }
}
