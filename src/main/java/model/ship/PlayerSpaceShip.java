package model.ship;

import model.bullet.Bullet;
import utilities.MagicEnumDouble;
import utilities.MagicEnumImage;
import utilities.MagicEnumInt;
import view.gameField.GameField;

public class PlayerSpaceShip extends SpaceShip {

    /**
     * Constructor.
     * @param gamefield
     */
    public PlayerSpaceShip(final GameField gamefield) {
        super(gamefield);
        super.setLifePoints(MagicEnumInt.THREE.getValue());
        this.setImage(MagicEnumImage.PLAYER.getImage());
        this.setSpeed(4);
        this.getNode().setScaleX(MagicEnumDouble.PLAYER_SCALE.getValue());
        this.getNode().setScaleY(MagicEnumDouble.PLAYER_SCALE.getValue());
    }

    @Override
    public final void attack() {
        Bullet bullet = new Bullet().bulletDamage(10);

        bullet.setPosition(this.getNode().getTranslateX() + MagicEnumInt.BULLET_X_TRANSITION.getValue(), 
                this.getNode().getTranslateY() - MagicEnumInt.BULLET_Y_TRANSITION.getValue());
        bullet.setSpeed(10);

        super.getGameFieldReference().addPlayerBullet(bullet);
        super.getGameFieldReference().getSoundManager().playBulletSound();
    }
}
