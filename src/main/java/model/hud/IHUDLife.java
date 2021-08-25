package model.hud;

/** This interface gives an indication how to handle a basic HUD lives system. */
public interface IHUDLife {

  /** @return life points. */
  int getLifePoints();

  /** It increases life points. */
  void lifeUp();

  /** It decreases life points. */
  void lifeDown();

  /** @return game status. */
  boolean getStatus();
}
