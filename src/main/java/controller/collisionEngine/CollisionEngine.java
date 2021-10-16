package controller.collisionEngine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.geometry.Bounds;
import model.Entity;
import model.bullet.Bullet;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import model.hud.IHUDBonus;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import utilities.MagicEnumDouble;
import utilities.MagicEnumInt;
import view.gameField.GameField;

/**
 * Collision engine implementation.
 */
public class CollisionEngine extends AbstractHelper implements ICollisionEngine {

    private static final int X_PLAYER_LEFT_BORDER = 69;
    private static final int X_PLAYER_RIGHT_BORDER = 146;
    private static final int X_BOSS_LEFT_BORDER = 271;
    private static final int X_BOSS_RIGHT_BORDER = 385;

    /*
     * Control fields.
     */
    private GameField gameField;
    private Bounds fieldBounds;
    private List<Entity> toBeRemovedList;

    /*
     * Constructor.
     */
    public CollisionEngine(final GameField gamefield, final HUDPointsImpl pointsHUD, final HUDLifeImpl livesHUD,
            final IHUDBonus bonusHUD) {
        super(pointsHUD, livesHUD, bonusHUD);

        this.gameField = gamefield;
        this.toBeRemovedList = new ArrayList<Entity>();
        this.fieldBounds = this.gameField.getScene().getRoot().getBoundsInLocal();

        super.resetBounds();
    }

    @Override
    public final void update() {
        this.collisionWalls();
        this.playerCollisionWithEnemies();
        this.playerBonusCollision();
        this.bulletCollisionWithEnemies();
        this.bossesCollisionwithWall();
        this.bossCollisionWithBullets();
        this.removeUnusedEntities();
    }

    /**
     * Helper method.
     * 
     * @return true if player touches left side of the gameField.
     */
    private boolean isEntityCollidingRightWall(final Entity entity, final int delay) {
        return (entity.getPosition().getX().intValue() > this.fieldBounds.getMaxX() - delay);
    }

    /**
     * Helper method.
     * 
     * @return true if player touches right side of the gameField.
     */
    private boolean isEntityCollidingLeftWall(final Entity entity, final int delay) {
        return (entity.getPosition().getX().intValue() < this.fieldBounds.getMinX() - delay);
    }

    /**
     * Helper method. Handles bonus spawn on the HUD.
     */
    private void statusHandler() {
        Map<StatusEnum, Boolean> map = this.gameField.getStatusController().getActiveStatus();

        for (final StatusEnum elem : map.keySet()) {
            if (map.get(elem)) {
                super.getBonusHUD().showBonus(elem);
            } else {
                super.getBonusHUD().hideBonus(elem);
            }
        }
    }

    /**
     * Helper method. Removes all collided and not anymore on the game field
     * entities.
     */
    private void removeUnusedEntities() {
        this.toBeRemovedList.forEach(entity -> this.gameField.removeEntity(entity));
        this.toBeRemovedList.clear();
    }

    @Override
    public final void collisionWalls() {
        int limit = this.gameField.getPlayer().getPosition().getX().intValue();

        if (this.isEntityCollidingLeftWall(this.gameField.getPlayer(), X_PLAYER_LEFT_BORDER)) {
            this.gameField.getSoundManager().playClashWall();
            this.gameField.getPlayer().setPosition(limit + AbstractHelper.resetX,
                    this.gameField.getPlayer().getPosition().getY().intValue());

        } else if (this.isEntityCollidingRightWall(this.gameField.getPlayer(), X_PLAYER_RIGHT_BORDER)) {
            this.gameField.getSoundManager().playClashWall();
            this.gameField.getPlayer().setPosition(limit - AbstractHelper.resetX,
                    this.gameField.getPlayer().getPosition().getY().intValue());
        }
    }

    @Override
    public final void playerCollisionWithEnemies() {
        for (SpaceShip spaceship : this.gameField.getActiveEnemyShips()) {
            Bounds shipBound = spaceship.getNode().getBoundsInParent();

            if (this.gameField.getPlayer().getNode().getBoundsInParent().intersects(shipBound)) {
                super.removeLife();
                super.removePoints();

                this.gameField.getGameContainer().getChildren().remove(spaceship.getNode());
                this.toBeRemovedList.add(spaceship);
                this.gameField.getSoundManager().playPlayerImpact();
            }
        }

        /*
         * Boss collision with player
         */
        this.gameField.getActiveBosses().forEach(boss -> {
            Bounds shipBound = boss.getNode().getBoundsInParent();
            if (this.gameField.getPlayer().getNode().getBoundsInParent().intersects(shipBound)) {
                super.removeLife();
                super.removePoints();
                super.setBossHP(MagicEnumInt.SEVEN.getValue());

                this.gameField.setBossToBeSpawned(true);
                this.gameField.getGameContainer().getChildren().remove(boss.getNode());
                this.toBeRemovedList.add(boss);
                this.gameField.getSoundManager().playPlayerImpact();
            }
        });

        this.gameField.getActiveEnemyShips().removeAll(this.toBeRemovedList);
    }

    @Override
    public final void playerBonusCollision() {

        this.statusHandler();

        for (Status bonus : this.gameField.getBonusObjects()) {
            if (this.gameField.getPlayer().getNode().getBoundsInParent()
                    .intersects(bonus.getNode().getBoundsInParent())) {

                this.gameField.getStatusController().applyEffect(bonus);
                this.gameField.getSoundManager().playStatusPick();

                switch (bonus.getStatusName()) {
                case BonusLife:
                    super.addLife();
                    break;
                case BonusSpeed:
                    resetX *= MagicEnumDouble.BOOST.getValue();
                    break;
                case MalusSpeed:
                    resetX *= MagicEnumDouble.SLOW.getValue();
                    break;
                case MalusCommand:
                    break;
                case MalusFire:
                    break;
                default:
                }

                this.toBeRemovedList.add(bonus);
            }
        }
    }

    @Override
    public final void bulletCollisionWithEnemies() {
        Iterator<SpaceShip> enemies = this.gameField.getActiveEnemyShips().iterator();
        Iterator<Bullet> bullets = this.gameField.getActiveBulletsShotbyPlayer().iterator();

        while (enemies.hasNext()) {
            SpaceShip enemyship = enemies.next();
            Bounds shipBound = enemyship.getNode().getBoundsInParent();

            while (bullets.hasNext()) {
                Bullet bullet = bullets.next();
                Bounds bulletBound = bullet.getNode().getBoundsInParent();

                if (bulletBound.intersects(shipBound)) {
                    this.gameField.getSoundManager().playSpaceshipExplosion();
                    this.toBeRemovedList.add(bullet);
                    this.toBeRemovedList.add(enemyship);

                    bullets.remove();
                    enemies.remove();
                    super.addPoints();
                }
            }

            bullets = this.gameField.getActiveBulletsShotbyPlayer().iterator();
        }
    }

    @Override
    public final void bossesCollisionwithWall() {

        this.gameField.getActiveBosses().forEach(boss -> {

            if (this.isEntityCollidingLeftWall(boss, X_BOSS_LEFT_BORDER)) {
                boss.invertDirection();
                boss.drop();
            } else if (this.isEntityCollidingRightWall(boss, X_BOSS_RIGHT_BORDER)) {
                boss.invertDirection();
                boss.drop();
            }
        });
    }

    @Override
    public final void bossCollisionWithBullets() {
        Iterator<SpaceShip> bosses = this.gameField.getActiveBosses().iterator();
        Iterator<Bullet> bullets = this.gameField.getActiveBulletsShotbyPlayer().iterator();

        while (bosses.hasNext()) {
            SpaceShip enemyship = bosses.next();
            Bounds shipBound = enemyship.getNode().getBoundsInParent();

            while (bullets.hasNext()) {
                Bullet bullet = bullets.next();
                Bounds bulletBound = bullet.getNode().getBoundsInParent();

                if (bulletBound.intersects(shipBound)) {
                    this.toBeRemovedList.add(bullet);

                    if (super.getBossHP() < MagicEnumInt.ONE.getValue()) {
                        bosses.remove();
                        this.toBeRemovedList.add(enemyship);
                        this.gameField.getSoundManager().playBossDeath();
                        this.gameField.setBossToBeSpawned(true);
                        super.setBossHP(MagicEnumInt.SEVEN.getValue());
                        super.getPointsHUD().pointsSetter(MagicEnumInt.THREE.getValue());
                    } else {
                        this.gameField.getSoundManager().playBossDamaged();
                    }
                    super.setBossHP(super.getBossHP() - MagicEnumInt.ONE.getValue());
                    bullets.remove();
                }
            }
            bullets = this.gameField.getActiveBulletsShotbyPlayer().iterator();
        }
    }
}
