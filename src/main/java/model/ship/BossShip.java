package model.ship;

import Utilities.*;
import view.gameField.GameField;

public class BossShip extends EnemyShip {

  public BossShip(GameField gamefield) {
    super(gamefield);
    this.setImage(Parameters.bossShipImage);
    this.setDirection(Direction.DOWN);
    this.getNode().setScaleX(0.15);
    this.getNode().setScaleY(0.15);
    this.setSpeed(0);
    this.getNode().setPickOnBounds(true);
  }
}
