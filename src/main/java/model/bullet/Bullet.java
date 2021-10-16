package model.bullet;

import java.awt.Rectangle;
import model.EntityImpl;
import utilities.Direction;
import utilities.MagicEnumDouble;
import utilities.MagicEnumImage;
import utilities.MagicEnumInt;

public class Bullet extends EntityImpl {

    private int damage;

    /**
     * Constructor.
     */
    public Bullet() {
        // set default
        this.setImage(MagicEnumImage.BULLET.getImage());
        this.setDirection(Direction.UP);
        this.getNode().setViewOrder(MagicEnumInt.BULLET_VIEW_ORDER.getValue());

        this.getNode().setScaleX(MagicEnumDouble.BULLET_SCALE.getValue());
        this.getNode().setScaleY(MagicEnumDouble.BULLET_SCALE.getValue());
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
