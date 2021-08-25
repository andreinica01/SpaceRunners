package model.bullet;

import Utilities.*;
import java.awt.Rectangle;
import model.EntityImpl;

public class Bullet extends EntityImpl {

  private int damage;

  public Bullet() {
    //set default
    this.setImage(Parameters.bulletImage);
    this.setDirection(Direction.UP);
    this.getNode().setViewOrder(-50);

    this.getNode().setScaleX(0.08);
    this.getNode().setScaleY(0.08);
  }

  public Bullet bulletDamage(int damage) {
    this.damage = damage;
    return this;
  }

  int getBulletDamage() {
    return this.damage;
  }

  public Rectangle getBounds() {
    return new Rectangle();
  }
}
