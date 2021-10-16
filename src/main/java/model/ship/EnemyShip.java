package model.ship;

import model.bullet.Bullet;
import utilities.Direction;
import view.gameField.GameField;

public class EnemyShip extends SpaceShip {

    private static final double TWENTY = 20d;

    /**
     * Constructor.
     * 
     * @param gamefield
     */
    public EnemyShip(final GameField gamefield) {
        super(gamefield);
    }

    @Override
    public final void attack() {
        Bullet bullet = new Bullet().bulletDamage(10);

        // to change
        double x = this.getNode().getLayoutX() - this.getNode().getBoundsInParent().getWidth();
        double y = this.getNode().getLayoutY() - this.getNode().getBoundsInParent().getHeight();

        bullet.setPosition(x, y);
        bullet.setSpeed(10);
        bullet.setDirection(Direction.DOWN);
        bullet.getNode().setScaleX(TWENTY);
        bullet.getNode().setScaleY(TWENTY);

        super.getGameFieldReference().addEnemyBullet(bullet);
        super.getGameFieldReference().getSoundManager().playBulletSound();
    }
}
