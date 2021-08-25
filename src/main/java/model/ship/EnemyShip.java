package model.ship;

import Utilities.Direction;
import Utilities.Parameters;
import Utilities.Vector2DImpl;
import view.gameField.*;

public class EnemyShip extends SpaceShip {

  public EnemyShip(GameField gamefield) {
    super(gamefield);
    this.setDimension(new Vector2DImpl<Number>(50, 50));
    this.setImage(Parameters.enemyImage);
    this.setDirection(Direction.DOWN);
    this.getNode().setScaleX(0.15);
    this.getNode().setScaleY(0.15);
    this.setSpeed(10);
  }

  @Override
  public void attack() {
    // TODO Auto-generated method stub

  }
}
