package controller.collisionEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import Utilities.HUDParameters;
import javafx.geometry.Bounds;
import model.Entity;
import model.bullet.Bullet;
import model.hud.HUDBonusImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
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
    private Bounds fieldBounds;
    public static double resetX;

    /*
     * List of entities that must be removed
     */
    private List<Entity> toBeRemovedList;

    /**
     * Constructor
     */
    public PhysicsEngineImpl(final GameField gamefield, final HUDPointsImpl pointsHUD,
                                final HUDLifeImpl livesHUD, final HUDBonusImpl bonusHUD) {
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
        this.collisionWalls();
        this.playerCollisionWithEnemies();
        this.playerBonusCollision();
        this.bulletCollisionWithEnemies();
        this.bossesCollisionwithWall();
        this.bossCollisionWithBullets();
        this.removeUnusedEntities();
    }

    /*
     * Collision detection
     */
    /** 
     * Handles collisions within the player and the game field. 
     */
    private void collisionWalls() {
        int limit = this.gamefield.getPlayer().getPosition().getX().intValue();

        if (this.isEntityCollidingLeftWall(this.gamefield.getPlayer())) {
            this.gamefield.getSoundManager().playClashWall();
            this.gamefield.getPlayer()
                .setPosition(limit - resetX, this.gamefield.getPlayer().getPosition().getY().intValue());
        
        } else if (this.isEntityCollidingRightWall(this.gamefield.getPlayer())) {
            this.gamefield.getSoundManager().playClashWall();
            this.gamefield.getPlayer()
                .setPosition(limit + resetX, this.gamefield.getPlayer().getPosition().getY().intValue());
        }
    }

    /**
     * Helper method.
     * @return true if player touches left side of the gameField.
     */
    private boolean isEntityCollidingRightWall(final Entity entity) {
        return (entity.getPosition().getX().intValue() < this.fieldBounds.getMinX() - X_RIGHT_BORDER);
    }

    /**
     * Helper method.
     * @return true if player touches right side of the gameField.
     */
    private boolean isEntityCollidingLeftWall(final Entity entity) {
        return (entity.getPosition().getX().intValue() > this.fieldBounds.getMaxX() - X_LEFT_BORDER);
    }

    /** 
     * Handles collision between player and enemy ships.
     */
    public void playerCollisionWithEnemies() {
        for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {
            Bounds shipBound = spaceship.getNode().getBoundsInParent();

            if (this.gamefield.getPlayer().getNode().getBoundsInParent().intersects(shipBound)) {
                this.removeLife();
                this.removePoints();
                this.gamefield.getGameContainer().getChildren().remove(spaceship.getNode());
                this.toBeRemovedList.add(spaceship);
                
                this.gamefield.getSoundManager().playPlayerImpact();
            }
        }
        this.gamefield.getActiveEnemyShips().removeAll(this.toBeRemovedList);
    }

    /** 
     * Collision between player and bonus entities.
     */
    public void playerBonusCollision() {
        
        this.statusHandler();
        
        for (Status bonus : this.gamefield.getBonusObjects()) {
            if (this.gamefield.getPlayer().getNode().getBoundsInParent()
                    .intersects(bonus.getNode().getBoundsInParent())) {
                
                this.gamefield.getStatusController().applyEffect(bonus);

                switch (bonus.getStatusName()) {
                    case BonusLife:
                        this.addLife();
                        break;
                    case BonusSpeed:
                        resetX *= 1.5;
                        break;
                    case MalusSpeed:
                        resetX *= 0.5;
                        break;  
                    case MalusCommand:
                        break;
                    case MalusFire:
                        break;
                }
                
                this.toBeRemovedList.add(bonus);
            }
        }
    }

    /**
     * Handles bonus spawn on the HUD.
     */
    private void statusHandler() {
        Map<StatusEnum, Boolean> map = this.gamefield.getStatusController().getActiveStatus();
        
        for(final StatusEnum elem : map.keySet()) {
            if(map.get(elem)) {
                this.bonusHUD.showBonus(elem);
            } else {
                this.bonusHUD.hideBonus(elem);
            }
        }
    }

    /** 
     * Collision between bullet and enemy entities. 
     */
    public void bulletCollisionWithEnemies() {
        Iterator<SpaceShip> enemies = this.gamefield.getActiveEnemyShips().iterator();
        Iterator<Bullet> bullets = this.gamefield.getActiveBulletsShotbyPlayer().iterator();

        while (enemies.hasNext()) {
            SpaceShip enemyship = enemies.next();
            Bounds shipBound = enemyship.getNode().getBoundsInParent();

            while (bullets.hasNext()) {
                Bullet bullet = bullets.next();
                Bounds bulletBound = bullet.getNode().getBoundsInParent();

                if (bulletBound.intersects(shipBound)) {
                    this.toBeRemovedList.add(bullet);
                    this.toBeRemovedList.add(enemyship);

                    bullets.remove();
                    enemies.remove();
                    this.addPoints();
                }
            }
            
            bullets = this.gamefield.getActiveBulletsShotbyPlayer().iterator();
        }
    }
    
    /**
     * Handles collision between Boss bullets and player.
     */
    private void playerCollisionWithBullets() {
        /*   Iterator<Bullet> bullets = this.gamefield.getActiveBulletsShotbyEnemies().iterator();

        while(bullets) */

        /*  while(x.hasNext())
    {
      if(x.next().getNode().getBoundsInParent().intersects(this.gamefield.getPlayer().getNode().getBoundsInParent())


    }
 */
    }

    /**
     * Handles Boss collision within game field.
     */
    private void bossesCollisionwithWall() {
        this.gamefield.getActiveBosses()
            .forEach(
                boss -> {
                    if (
                        !this.gamefield.getGameContainer()
                            .getLayoutBounds()
                            .contains(boss.getNode().getBoundsInParent())
                    ) {
                        boss.invertDirection();
                    }
                }
            );
    }

    public void bossCollisionWithBullets() {
        Iterator<SpaceShip> bosses = this.gamefield.getActiveBosses().iterator();
        Iterator<Bullet> bullets = this.gamefield.getActiveBulletsShotbyPlayer().iterator();

        while (bosses.hasNext()) {
            SpaceShip enemyship = bosses.next();

            Bounds shipBound = enemyship.getNode().getBoundsInParent();

            while (bullets.hasNext()) {
                Bullet bullet = bullets.next();

                Bounds bulletBound = bullet.getNode().getBoundsInParent();

                if (bulletBound.intersects(shipBound)) {
                    this.toBeRemovedList.add(bullet);
                    this.toBeRemovedList.add(enemyship);

                    bullets.remove();
                    bosses.remove();

                    this.addPoints();

                    break;
                }
            }
            bullets = this.gamefield.getActiveBulletsShotbyPlayer().iterator();
        }
    }
    
    /**
     * Removes all collided and not anymore on the game field entities.
     */
    private void removeUnusedEntities() {
        this.toBeRemovedList.forEach(entity -> this.gamefield.removeEntity(entity));
        this.toBeRemovedList.clear();
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

    /**
     * Static method used to reset the bounds collision everytime
     * a bonus runs off. Static because I want this to be called
     * from the outside without referencing it as a bonus.
     */
    public static void resetBound() {
        resetX = HUDParameters.FOUR;
    }
}