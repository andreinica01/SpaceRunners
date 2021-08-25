package model.ship;

import Utilities.*;
import model.bullet.Bullet;
import view.gameField.GameField;

public class BossShip extends SpaceShip {

  public BossShip(GameField gamefield) {
    super(gamefield);
    // this.setDimension(new Vector2DImpl<Number>(50, 50));

    this.setImage(Parameters.bossShipImage);
    this.setDirection(Direction.DOWN);
    this.getNode().setScaleX(0.15);
    this.getNode().setScaleY(0.15);
    this.setSpeed(0);
  }

  @Override
  public void attack() {
    Bullet bullet = new Bullet().bulletDamage(10);

    bullet.setPosition(
        this.gamefield.getPlayer().getNode().getTranslateX() - 190,
        this.gamefield.getPlayer().getNode().getTranslateY() - 200);
    bullet.setSpeed(10);
    bullet.setDirection(Direction.DOWN);

    this.gamefield.addBullet(bullet);
    this.gamefield.getSoundManager().playBulletSound();
  }
}
