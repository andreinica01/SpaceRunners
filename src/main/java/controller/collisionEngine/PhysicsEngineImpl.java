package controller.collisionEngine;

import javafx.geometry.Bounds;
import model.bullet.Bullet;
import model.ship.SpaceShip;
import view.gameField.GameField;

public class PhysicsEngineImpl implements PhysicsEngine {

   private GameField gamefield;

   public PhysicsEngineImpl(GameField gamefield) {
       this.gamefield = gamefield;

       // funzioni che puoi usare
         this.gamefield.getPlayer(); this.gamefield.getActiveEnemyShips();
         this.gamefield.getBonusObjects();
        
         this.gamefield.getWidth(); this.gamefield.getHeight();
         this.gamefield.getActiveBulletsShotbyEnemies();
         this.gamefield.getActiveBulletsShotbyPlayer();
         
   }



   @Override
   public void removeLife() {
       // TODO Auto-generated method stub
       
   }

   @Override
   public void removePoints() {
       // TODO Auto-generated method stub
       
   }

   @Override
   public void removeCollidedShips() {


        for (Bullet bullet : this.gamefield.getActiveBulletsShotbyPlayer()) {
            
            for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {

                Bounds bulletBound = bullet.getNode().getBoundsInParent();
                Bounds shipBound = spaceship.getNode().getBoundsInParent();
                
                if(bulletBound.intersects(shipBound))
                {
                     this.gamefield.getGameContainer().getChildren().remove(spaceship.getNode());

                }

            }
          


        }   
  
   }




}
