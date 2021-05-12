package GameField;

import javafx.scene.canvas.Canvas;
import java.util.HashSet;
import java.util.Set;

import GameObjects.BonusObject;
import GameObjects.Entity;
import GameObjects.SpaceShip;



public class GameFieldImpl implements GameField {

    private Canvas gameCanvas;
    private Set<Entity> entities;

 

    public GameFieldImpl(final int width, final int height)
    {
        this.gameCanvas = new Canvas();
        
        this.gameCanvas.setWidth(width);
        this.gameCanvas.setHeight(height);

        this.entities = new HashSet<Entity>();
    }

    public Canvas getCanvas()
    {
        return this.gameCanvas;
    }


    @Override
    public void addEntity(Entity entity) {
        this.entities.add(entity);
        
    }

    @Override
    public Set<Entity> getActiveEntities() {
        return this.entities;
    }

    @Override
    public Number getWidth() {
       
        return this.gameCanvas.getWidth();
    }

    @Override
    public Number getHeight() {
        return this.gameCanvas.getHeight();
    }

    @Override
    public void setPlayer(SpaceShip player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public SpaceShip getPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<SpaceShip> getActiveEnemyShips() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<BonusObject> getBonusObjects() {
        // TODO Auto-generated method stub
        return null;
    }

 
}


