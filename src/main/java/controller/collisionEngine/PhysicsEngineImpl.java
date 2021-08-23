package controller.collisionEngine;

import javafx.geometry.Bounds;
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

    public PhysicsEngineImpl(final GameField gamefield, final HUDPointsImpl pointsHUD, final HUDLifeImpl livesHUD) {
           
        this.gamefield = gamefield;
        this.pointsHUD = pointsHUD;
        this.livesHUD = livesHUD;
        this.bounds = this.gamefield.getScene().getRoot().getBoundsInLocal(); 

        this.gamefield.getPlayer(); this.gamefield.getActiveEnemyShips();
        this.gamefield.getBonusObjects();

        this.gamefield.getWidth(); this.gamefield.getHeight();
        this.gamefield.getActiveBulletsShotbyEnemies();
        this.gamefield.getActiveBulletsShotbyPlayer();
   }
   
    @Override
    public void playerCollsionBorders() {
       if(this.gamefield.getPlayer().getPosition().getX().intValue() > this.bounds.getMaxX() - 120) {
           this.gamefield.getSoundManager().playClashWall();
           int limit = this.gamefield.getPlayer().getPosition().getX().intValue() - 4;
           this.gamefield.getPlayer().setPosition(limit, this.gamefield.getPlayer().getPosition().getY().intValue());
       }
               
       if(this.gamefield.getPlayer().getPosition().getX().intValue() < this.bounds.getMinX() - 25) {
           this.gamefield.getSoundManager().playClashWall();
           int limit = this.gamefield.getPlayer().getPosition().getX().intValue() + 4;
           this.gamefield.getPlayer().setPosition(limit, this.gamefield.getPlayer().getPosition().getY().intValue());
       }        
    }

    @Override
    public void removeLife() {
         this.livesHUD.lifeDown();
     }
    
    @Override
    public void removePoints() {
        this.pointsHUD.pointsDown();
    }

    @Override
    public void removeCollidedShips() {
        
        for (Bullet bullet : this.gamefield.getActiveBulletsShotbyPlayer()) {
            for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {
                
                Bounds bulletBound = bullet.getNode().getBoundsInParent();
                Bounds shipBound = spaceship.getNode().getBoundsInParent();
                
                if(bulletBound.intersects(shipBound)) {
                     this.gamefield.getGameContainer().getChildren().remove(spaceship.getNode());
                     this.gamefield.getActiveEnemyShips().remove(spaceship);
                     this.gamefield.getGameContainer().getChildren().remove(bullet.getNode());
                     this.gamefield.getActiveBulletsShotbyPlayer().remove(bullet);
                     this.pointsHUD.pointsUp();
                }
            }
        }   
    }

    public void playerShipCollision() {
        for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {
                Bounds shipBound = spaceship.getNode().getBoundsInParent();
            
                if(this.gamefield.getPlayer().getNode().getBoundsInParent().intersects(shipBound)) {
                    this.removeLife();
                    this.removePoints();
                    this.gamefield.getGameContainer().getChildren().remove(spaceship.getNode());
                    this.gamefield.getActiveEnemyShips().remove(spaceship);
                }
        }
    }
}
