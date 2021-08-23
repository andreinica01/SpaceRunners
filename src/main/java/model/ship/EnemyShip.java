package model.ship;

import Utilities.Direction;
import Utilities.Parameters;
import Utilities.Vector2DImpl;

public class EnemyShip extends SpaceShip {
    
    public EnemyShip()
    {
        this.setDimension(new Vector2DImpl<Number>(50, 50));	
        this.setImage(Parameters.enemyImage);
        this.setDirection(Direction.DOWN);
        this.getNode().setScaleX(0.15);
        this.getNode().setScaleY(0.15);

    }
}
