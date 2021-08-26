package model.ship;

import model.EntityImpl;
import view.gameField.GameField;

public abstract class SpaceShip extends EntityImpl {

  protected int lifePoints;
  private boolean invertedCommand;

  protected GameField gamefield;

  public SpaceShip(GameField gamefield) {

    setSpeed(10);
    setInvertedCommand(false);
    setCanFire(true);

    this.gamefield = gamefield;
  }

  public int getLifePoints() {
    return this.lifePoints;
  }

  public void setLifePoints(int lifepoint) {
    this.lifePoints = lifepoint;
  }

  public void increaseLifePoints(Number points) {
    this.lifePoints += points.intValue();
  }

  public void decreaselifePoints(Number points) {
    this.lifePoints -= points.intValue();
  }

  public boolean isInvertedCommand() {
    return invertedCommand;
  }

  public void setInvertedCommand(boolean invertedCommand) {
    this.invertedCommand = invertedCommand;
  }

  /** attack */
  public abstract void attack();
}
