package model.bullet;

import java.awt.Rectangle;
import model.EntityImpl;
import utilities.Direction;
import utilities.Parameters;
import utilities.VariousMagicNumbers;

public class Bullet extends EntityImpl {

    private int damage;

    public Bullet() {
        // set default
        this.setImage(Parameters.bulletImage);
        this.setDirection(Direction.UP);
        this.getNode().setViewOrder(VariousMagicNumbers.BULLET_VIEW_ORDER);

        this.getNode().setScaleX(VariousMagicNumbers.BULLET_SCALE);
        this.getNode().setScaleY(VariousMagicNumbers.BULLET_SCALE);
    }

    /**
     * @param damage
     * @return
     */
    public Bullet bulletDamage(final int damage) {
        this.damage = damage;
        return this;
    }

    /**
     * @return bullet damage.
     */
    int getBulletDamage() {
        return this.damage;
    }

    /**
     * @return bounds of entities.
     */
    public Rectangle getBounds() {
        return new Rectangle();
    }
}
