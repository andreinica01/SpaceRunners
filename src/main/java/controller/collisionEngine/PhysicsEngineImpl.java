package controller.collisionEngine;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Bounds;
import model.Entity;
import model.bullet.Bullet;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import model.ship.SpaceShip;
import model.status.Status;
import view.gameField.GameField;

public class PhysicsEngineImpl implements PhysicsEngine {

    private static final int RESET_X = 4;
    /*
     * Control fields
     */
    private GameField gamefield;
    private HUDPointsImpl pointsHUD;
    private HUDLifeImpl livesHUD;
    private Entity toremove;
    private Bounds fieldBounds;
    private boolean check;

    /*
     * List of entities that must be removed
     */
    private List<Entity> toBeRemovedList;

    /*
     * Constructor
     */
    public PhysicsEngineImpl(final GameField gamefield, final HUDPointsImpl pointsHUD, final HUDLifeImpl livesHUD) {
        this.gamefield = gamefield;
        this.pointsHUD = pointsHUD;
        this.livesHUD = livesHUD;

        this.toBeRemovedList = new ArrayList<Entity>();
        this.fieldBounds = this.gamefield.getScene().getRoot().getBoundsInLocal();
    }

    @Override
    public void update() {
        collisionWalls();
        playerCollisionWithEnemies();
        playerBonusCollision();
        bulletCollsionwithEnemies();
    }

    /*
     * Collision detection
     */
    /**
     * This methods detects collisions with the border of the gameField.
     */
    private void collisionWalls() {
        int limit = this.gamefield.getPlayer().getPosition().getX().intValue();

        if (this.isPlayerCollidingLeftWall()) {
            this.gamefield.getPlayer()
                .setPosition(limit - RESET_X, this.gamefield.getPlayer().getPosition().getY().intValue());

            //Sound
            this.gamefield.getSoundManager().playClashWall();

        } else if (this.isPlayerCollidingRightWall()) {
            this.gamefield.getSoundManager().playClashWall();
            this.gamefield.getPlayer()
                .setPosition(limit + RESET_X, this.gamefield.getPlayer().getPosition().getY().intValue());
            //Sound
            this.gamefield.getSoundManager().playClashWall();
        }
    }

    /**
     * Helper method.
     * @return true if player touches left side of the gameField.
     */
    private boolean isPlayerCollidingLeftWall() {
        return this.gamefield.getPlayer().getPosition().getX().intValue() > this.fieldBounds.getMaxX() - 120;
    }

    /**
     * Helper method.
     * @return true if player touches right side of the gameField.
     */
    private boolean isPlayerCollidingRightWall() {
        return this.gamefield.getPlayer().getPosition().getX().intValue() < this.fieldBounds.getMinX() - 25;
    }

    /**
     * Detects collision between player and enemy ships.
     */
    public void playerCollisionWithEnemies() {

        for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {

            Bounds shipBound = spaceship.getNode().getBoundsInParent();

            // rimozione navi se superano il player, rimozione bound

            if (this.gamefield.getPlayer().getNode().getBoundsInParent().intersects(shipBound)) {
                this.removeLife();
                this.removePoints();
                this.gamefield.getGameContainer().getChildren().remove(spaceship.getNode());
                toBeRemovedList.add(spaceship);

                this.gamefield.getSoundManager().playPlayerImpact();
            }
        }
        this.gamefield.getActiveEnemyShips().removeAll(toBeRemovedList);
        this.toBeRemovedList.clear();
    }

    @Override
    public void playerBonusCollision() {

        for (Status bonus : this.gamefield.getBonusObjects()) {
            if (this.gamefield.getPlayer().getNode().getBoundsInParent()
                    .intersects(bonus.getNode().getBoundsInParent()))
                ;
            {
                this.gamefield.getStatusController().applyEffect(bonus);
            }

        }

    }

    @Override
    public void bulletCollsionwithEnemies() {
        check = false;

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

                    check = true;
                }
            }
        }

        if (check) {
            this.gamefield.getActiveEnemyShips().removeAll(this.toBeRemovedList);
            this.gamefield.getActiveBulletsShotbyPlayer().remove(toremove);

            this.toBeRemovedList.clear();
            this.gamefield.getSoundManager().playSpaceshipExplosion();
            check = false;
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
}
