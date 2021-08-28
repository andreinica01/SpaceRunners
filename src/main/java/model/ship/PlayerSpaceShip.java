package model.ship;

import Utilities.Parameters;
import model.bullet.*;
import view.gameField.GameField;

public class PlayerSpaceShip extends SpaceShip {

    /**
     * Constructor.
     * @param gamefield
     */
    public PlayerSpaceShip(final GameField gamefield) {
        super(gamefield);
        this.setImage(Parameters.playerImage);
        this.setSpeed(4);
        this.getNode().setScaleX(0.3);
        this.getNode().setScaleY(0.3);
        this.lifePoints = Parameters.INITIAL_PLAYER_POINTS;
    }

    @Override
    public void attack() {
        Bullet bullet = new Bullet().bulletDamage(10);

        bullet.setPosition(this.getNode().getTranslateX() - 157, this.getNode().getTranslateY() - 145);
        bullet.setSpeed(10);

        this.gamefield.addPlayerBullet(bullet);
        this.gamefield.getSoundManager().playBulletSound();
    }
}