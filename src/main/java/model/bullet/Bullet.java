package model.bullet;

import Utilities.*;
import model.EntityImpl;

public class Bullet extends EntityImpl {

    private int damage;

    public Bullet() {
        
        //set default
        this.setImage(Parameters.bulletImage);
        this.setDirection(Direction.UP);
        this.getNode().setViewOrder(-50);

        this.getNode().setScaleX(0.10);
        this.getNode().setScaleY(0.10);


    }

    public Bullet bulletDamage(int damage)
    {
        this.damage = damage;

        return this;
    }

    int getBulletDamage() {
        return this.damage;

    }
}
