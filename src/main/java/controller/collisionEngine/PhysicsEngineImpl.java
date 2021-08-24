package controller.collisionEngine;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import model.Entity;
import model.bullet.Bullet;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import model.ship.SpaceShip;
import view.gameField.GameField;

public class PhysicsEngineImpl implements PhysicsEngine {

    private GameField gamefield;
    private HUDPointsImpl pointsHUD;
    private HUDLifeImpl livesHUD;
    private Bounds bounds;
    private Bounds bonusBounds;
    private Entity toremove;

    private List<Entity> toBeRemovedList;

    public PhysicsEngineImpl(final GameField gamefield, final HUDPointsImpl pointsHUD, final HUDLifeImpl livesHUD) {

        this.gamefield = gamefield;
        this.pointsHUD = pointsHUD;
        this.livesHUD = livesHUD;
        this.bounds = this.gamefield.getScene().getRoot().getBoundsInLocal();
        //this.bonusBounds = this.gamefield.get

        this.toBeRemovedList = new ArrayList<Entity>();

    }

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

    /*
     * Collision detection
     */
    @Override
    public void playerCollsionBorders() {
        if (this.gamefield.getPlayer().getPosition().getX().intValue() > this.bounds.getMaxX() - 120) {
            this.gamefield.getSoundManager().playClashWall();
            int limit = this.gamefield.getPlayer().getPosition().getX().intValue() - 4;
            this.gamefield.getPlayer().setPosition(limit, this.gamefield.getPlayer().getPosition().getY().intValue());
        }

        if (this.gamefield.getPlayer().getPosition().getX().intValue() < this.bounds.getMinX() - 25) {
            this.gamefield.getSoundManager().playClashWall();
            int limit = this.gamefield.getPlayer().getPosition().getX().intValue() + 4;
            this.gamefield.getPlayer().setPosition(limit, this.gamefield.getPlayer().getPosition().getY().intValue());
        }
    }

    @Override
    public void removeCollidedShips() {

        boolean check = false;
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

        if(check) {
            this.gamefield.getActiveEnemyShips().removeAll(this.toBeRemovedList);
            this.gamefield.getActiveBulletsShotbyPlayer().remove(toremove);
            
    
            this.toBeRemovedList.clear();
            this.gamefield.getSoundManager().playSpaceshipExplosion();
            check = false;
        }
       
    }

    public void playerShipCollision() {

        for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {

            Bounds shipBound = spaceship.getNode().getBoundsInParent();
            
            //rimozione navi se superano il player, rimozione bound

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
}
