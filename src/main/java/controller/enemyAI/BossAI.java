package controller.enemyAI;

import view.gameField.GameField;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import Utilities.Direction;
import model.ship.*;

public class BossAI {

    private final GameField gamefield;
    private SpaceShip bossShip;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public BossAI(GameField gamefield)
    {
        this.gamefield = gamefield;
    }

    public void generateBoss()
    {
        bossShip = new BossShip(this.gamefield);
		bossShip.setPosition(20, -300);
		bossShip.setDirection(Direction.LEFT);
		bossShip.setSpeed(4);

		this.gamefield.addEnemyShip(bossShip);

        //movement 
        final Runnable bossMovement = new Runnable() {
            public void run() {  
                 bossShip.invertDirection();
                
                }
          };
        
          final ScheduledFuture<?> bossThread =
            scheduler.scheduleAtFixedRate(bossMovement, 1, 1, TimeUnit.SECONDS);
          scheduler.schedule(new Runnable() {
            public void run() { 
                }
          }, 60 * 60, TimeUnit.SECONDS);


          //attack
        /*   final Runnable boss = new Runnable() {
            public void run() {  
                 bossShip.invertDirection();
                
                }
          };
        
          final ScheduledFuture<?> bossThread =
            scheduler.scheduleAtFixedRate(boss, 1, 1, TimeUnit.SECONDS);
          scheduler.schedule(new Runnable() {
            public void run() { 
                }
          }, 60 * 60, TimeUnit.SECONDS); */


        

    }

    public void removeBoss()
    {
        this.gamefield.getGameContainer().getChildren().remove(this.bossShip.getNode());
        this.gamefield.getActiveEnemyShips().remove(this.bossShip);

    }


}
