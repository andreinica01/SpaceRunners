package model.ship;

import Utilities.Parameters;
import Utilities.Vector2DImpl;

public class PlayerSpaceShip extends SpaceShip {
    
    public PlayerSpaceShip()
    {
        this.setDimension(new Vector2DImpl<Number>(100, 100));
        this.setImage(Parameters.playerImage);
        this.setSpeed(4);
    }
}
