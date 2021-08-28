package model.ship;

import Utilities.Direction;
import model.bullet.*;
import view.gameField.GameField;

public class EnemyShip extends SpaceShip {

    /**
     * Constructor.
     * 
     * @param gamefield
     */
    public EnemyShip(final GameField gamefield) {
        super(gamefield);
    }

    @Override
    public void attack() {
        Bullet bullet = new Bullet().bulletDamage(10);

        // to change
        double x = this.getNode().getLayoutX() - this.getNode().getBoundsInParent().getWidth();
        double y = this.getNode().getLayoutY() - this.getNode().getBoundsInParent().getHeight();

        bullet.setPosition(x, y);
        bullet.setSpeed(10);
        bullet.setDirection(Direction.DOWN);
        bullet.getNode().setScaleX(20);
        bullet.getNode().setScaleY(20);

        this.gamefield.addEnemyBullet(bullet);
        this.gamefield.getSoundManager().playBulletSound();
    }
}