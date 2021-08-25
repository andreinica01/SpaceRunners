package model.ship;

import Utilities.Parameters;
import Utilities.Vector2DImpl;
import model.bullet.*;
import view.gameField.GameField;

public class PlayerSpaceShip extends SpaceShip {

  public PlayerSpaceShip(GameField gamefield) {
    super(gamefield);
    this.setDimension(new Vector2DImpl<Number>(100, 100));
    this.setImage(Parameters.playerImage);
    this.setSpeed(4);
    this.getNode().setScaleX(0.3);
    this.getNode().setScaleY(0.3);
  }

  @Override
  public void attack() {
    Bullet bullet = new Bullet().bulletDamage(10);

    bullet.setPosition(this.getNode().getTranslateX() - 158, this.getNode().getTranslateY() - 155);
    bullet.setSpeed(10);

    this.gamefield.addBullet(bullet);
    this.gamefield.getSoundManager().playBulletSound();
  }
}
