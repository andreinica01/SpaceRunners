package model.ship;

import model.bullet.Bullet;
import utilities.Parameters;
import utilities.VariousMagicNumbers;
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
        this.getNode().setScaleX(VariousMagicNumbers.PLAYER_SCALE);
        this.getNode().setScaleY(VariousMagicNumbers.PLAYER_SCALE);
        this.lifePoints = Parameters.INITIAL_PLAYER_POINTS;
    }

    @Override
    public final void attack() {
        Bullet bullet = new Bullet().bulletDamage(10);

        bullet.setPosition(this.getNode().getTranslateX() + VariousMagicNumbers.BULLET_X_TRANSITION, 
                this.getNode().getTranslateY() - VariousMagicNumbers.BULLET_Y_TRANSITION);
        bullet.setSpeed(10);

        this.gamefield.addPlayerBullet(bullet);
        this.gamefield.getSoundManager().playBulletSound();
    }
}
