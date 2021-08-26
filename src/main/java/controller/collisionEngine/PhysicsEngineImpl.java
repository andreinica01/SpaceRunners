package controller.collisionEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Utilities.Direction;
import Utilities.HUDParameters;
import javafx.geometry.Bounds;
import model.Entity;
import model.bullet.Bullet;
import model.hud.HUDBonusImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import model.ship.SpaceShip;
import model.status.Status;
import view.gameField.GameField;

public class PhysicsEngineImpl implements PhysicsEngine {

  private static final double X_LEFT_BORDER = 146;
  private static final double X_RIGHT_BORDER = 70;
  /*
   * Control fields
   */
  private GameField gamefield;
  private HUDPointsImpl pointsHUD;
  private HUDLifeImpl livesHUD;
  private HUDBonusImpl bonusHUD;
  private Entity toremove;
  private Bounds fieldBounds;
  private boolean check;
  public static double resetX;

  /*
   * List of entities that must be removed
   */
  private List<Entity> toBeRemovedList;

  /*
   * Constructor
   */
  public PhysicsEngineImpl(
      final GameField gamefield,
      final HUDPointsImpl pointsHUD,
      final HUDLifeImpl livesHUD,
      final HUDBonusImpl bonusHUD) {
    this.gamefield = gamefield;
    this.pointsHUD = pointsHUD;
    this.livesHUD = livesHUD;
    this.bonusHUD = bonusHUD;
    resetBound();

    this.toBeRemovedList = new ArrayList<Entity>();
    this.fieldBounds = this.gamefield.getScene().getRoot().getBoundsInLocal();
  }

  @Override
  public void update() {
    collisionWalls();
    playerCollisionWithEnemies();
    playerBonusCollision();
    bulletCollsionwithEnemies();
    bossesCollisionwithWall();
  }


  private void playerCollisionWithBullets()
  {

    Iterator<Bullet> x = this.gamefield.getActiveBulletsShotbyEnemies().iterator();

   /*  while(x.hasNext())
    {
      if(x.next().getNode().getBoundsInParent().intersects(this.gamefield.getPlayer().getNode().getBoundsInParent())


    }
 */

  }

  private void bossesCollisionwithWall()
  {

    this.gamefield.getActiveBosses().forEach(boss -> {
      if((isEntityCollidingLeftWall(boss) && boss.getDirection() == Direction.LEFT)
       ||(isEntityCollidingRightWall(boss)&& boss.getDirection()==Direction.RIGHT))
      {
        boss.invertDirection();
      }
  
    });
  }

  /*
   * Collision detection
   */
  /** This methods detects collisions with the border of the gameField. */
  private void collisionWalls() {
    int limit = this.gamefield.getPlayer().getPosition().getX().intValue();

    if (this.isEntityCollidingLeftWall(this.gamefield.getPlayer())) {
      this.gamefield
          .getPlayer()
          .setPosition(limit - resetX, this.gamefield.getPlayer().getPosition().getY().intValue());

      // Sound
      this.gamefield.getSoundManager().playClashWall();
    } else if (this.isEntityCollidingRightWall(this.gamefield.getPlayer())) {
      this.gamefield.getSoundManager().playClashWall();
      this.gamefield
          .getPlayer()
          .setPosition(limit + resetX, this.gamefield.getPlayer().getPosition().getY().intValue());
      // Sound
      this.gamefield.getSoundManager().playClashWall();
    }
  }

  /**
   * Helper method.
   *
   * @return true if player touches left side of the gameField.
   */
  private boolean isEntityCollidingLeftWall(Entity entity) {
    return (entity.getPosition().getX().intValue()
        > this.fieldBounds.getMaxX() - X_LEFT_BORDER);
  }

  /**
   * Helper method.
   *
   * @return true if player touches right side of the gameField.
   */
  private boolean isEntityCollidingRightWall(Entity entity) {
    return (entity.getPosition().getX().intValue()
        < this.fieldBounds.getMinX() - X_RIGHT_BORDER);
  }

  /** Detects collision between player and enemy ships. */
  public void playerCollisionWithEnemies() {
    for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {
      Bounds shipBound = spaceship.getNode().getBoundsInParent();

      if (this.gamefield.getPlayer().getNode().getBoundsInParent().intersects(shipBound)) {
        this.removeLife();
        this.removePoints();
        this.gamefield.getGameContainer().getChildren().remove(spaceship.getNode());
        this.toBeRemovedList.add(spaceship);

        // Sound
        this.gamefield.getSoundManager().playPlayerImpact();
      }
    }

    this.gamefield.getActiveEnemyShips().removeAll(this.toBeRemovedList);
    this.toBeRemovedList.clear();
  }

  /** Collision between player and bonus entities. */
  public void playerBonusCollision() {
    for (Status bonus : this.gamefield.getBonusObjects()) {
      if (this.gamefield
          .getPlayer()
          .getNode()
          .getBoundsInParent()
          .intersects(bonus.getNode().getBoundsInParent())) {
        this.gamefield.getStatusController().applyEffect(bonus);

        switch (bonus.getStatusName()) {
          case BonusLife:
            this.bonusHUD.showBonus(HUDParameters.ZERO);
            this.addLife();
            break;
          case BonusSpeed:
            this.bonusHUD.showBonus(HUDParameters.ONE);
            resetX *= 1.5;
            break;
          case MalusCommand:
            this.bonusHUD.showBonus(HUDParameters.TWO);
            break;
          case MalusFire:
            this.bonusHUD.showBonus(HUDParameters.THREE);
            break;
          case MalusSpeed:
            this.bonusHUD.showBonus(HUDParameters.FOUR);
            resetX *= 0.5;
            break;
        }
        bonus.setPosition(-1000, bonus.getNode().getLayoutY());
      }
    }
  }

  /** Collision between bullet and enemy entities. */
  public void bulletCollsionwithEnemies() {
    this.check = false;

    for (Bullet bullet : this.gamefield.getActiveBulletsShotbyPlayer()) {
      for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {
        Bounds bulletBound = bullet.getNode().getBoundsInParent();
        Bounds shipBound = spaceship.getNode().getBoundsInParent();

        if (bulletBound.intersects(shipBound)) {
          this.gamefield.getGameContainer().getChildren().remove(spaceship.getNode());
          this.gamefield.getGameContainer().getChildren().remove(bullet.getNode());
          bullet.setPosition(-1000, bullet.getBounds().getY());

          this.addPoints();
          this.toBeRemovedList.add(spaceship);
          this.toremove = spaceship;
          this.check = true;
        }
      }
    }

    if (this.check) {
      this.gamefield.getActiveEnemyShips().removeAll(this.toBeRemovedList);
      this.gamefield.getActiveBulletsShotbyPlayer().remove(this.toremove);
      this.toBeRemovedList.clear();
      this.gamefield.getSoundManager().playSpaceshipExplosion();
      this.check = false;
    }
  }

  /*
   * HUD change methods
   */
  @Override
  public void removePoints() {
    this.pointsHUD.pointsDown();
  }

  @Override
  public void removeLife() {
    this.livesHUD.lifeDown();
  }

  @Override
  public void addPoints() {
    this.pointsHUD.pointsUp();
  }

  @Override
  public void addLife() {
    this.livesHUD.lifeUp();
  }

  public static void resetBound() {
    resetX = 4;
  }
}
