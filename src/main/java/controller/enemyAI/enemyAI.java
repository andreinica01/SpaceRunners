package controller.enemyAI;

import view.gameField.GameField;

public class enemyAI {
    
    private GameField gamefield ;
    long startTime;

    public enemyAI(GameField gamefield)
    {
        this.gamefield = gamefield;
        startTime = System.nanoTime();
      
        // ... the code being measured ...    
    
        

    }



    public void update()
    {
       if(System.nanoTime()-startTime>5000)
       {


       }


    }


}
