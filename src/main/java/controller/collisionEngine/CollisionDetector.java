package controller.collisionEngine;

import javafx.geometry.Bounds;
import view.gameField.GameField;

public class CollisionDetector {

  private GameField gamefield;
  private Bounds fieldBounds;

  public CollisionDetector(GameField gamefield) {
    this.gamefield = gamefield;
    this.fieldBounds = this.gamefield.getScene().getRoot().getBoundsInLocal();
  }

  public boolean isPlayerCollidingLeftWall() {
    return (
      this.gamefield.getPlayer().getPosition().getX().intValue() > this.fieldBounds.getMaxX() - 120
    );
  }

  public boolean isPlayerCollidingRightWall() {
    return (
      this.gamefield.getPlayer().getPosition().getX().intValue() < this.fieldBounds.getMinX() - 25
    );
  }
}
