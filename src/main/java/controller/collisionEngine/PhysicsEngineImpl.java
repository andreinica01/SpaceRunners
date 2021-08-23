package controller.collisionEngine;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import model.bullet.Bullet;
import model.ship.SpaceShip;
import view.SoundManager.SoundManager;
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
   public void playerCollsionBorders()
   {
      if(this.gamefield.getPlayer().getPosition().getX().intValue()<5)
      {
        SoundManager.playClashWall();

      }
       
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
                     this.gamefield.getActiveEnemyShips().remove(spaceship);
                     this.gamefield.getGameContainer().getChildren().remove(bullet.getNode());
                     this.gamefield.getActiveBulletsShotbyPlayer().remove(bullet);
                    
                }

            }
          
        }   
  
   }

   public void playerShipCollision()
   {


    for (SpaceShip spaceship : this.gamefield.getActiveEnemyShips()) {
                
            Bounds shipBound = spaceship.getNode().getBoundsInParent();
            
            if(this.gamefield.getPlayer().getNode().getBoundsInParent().intersects(shipBound))
            {
                //game over
              //  System.exit(0);
            }

    
    }   

   }


}
