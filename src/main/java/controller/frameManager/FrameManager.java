package controller.frameManager;
import java.util.Set;

import view.gameField.*;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;
import model.Entity;



public class FrameManager {

    private GameField gamefield;
    private Set<Entity> entities;

    public FrameManager(GameField gamefield) {
        this.gamefield = gamefield;
        this.entities = this.gamefield.getActiveEntities();

    }

    

    public void update() {

        updateBackground();

        entities.forEach(entity->{
            updateEntityPosition(entity);
        });




    }

    private void updateEntityPosition(Entity entity) {
        switch (entity.getDirection()) {
            case UP:
                entity.setPosition(entity.getPosition().getX(),
                        entity.getPosition().getY().intValue() - entity.getSpeed().intValue());
                break;
            case DOWN:
                entity.setPosition(entity.getPosition().getX(),
                        entity.getPosition().getY().intValue() + entity.getSpeed().intValue());
                break;
            case LEFT:
                entity.setPosition(
                        entity.getPosition().getX().intValue() - entity.getSpeed().intValue(),
                                entity.getPosition().getY());
                break;
            case RIGHT:
                entity.setPosition(
                        entity.getPosition().getX().intValue() + entity.getSpeed().intValue(),
                                entity.getPosition().getY());
                break;
            case NONE:
               //do nothing
                break;

        }
    }

    private void updateBackground()
    {
    //  this.gamefield.getBackground().setLayoutY(this.gamefield.getBackground().getLayoutY()+1f);
        //this.gamefield.getBackground().rep
  
      //  trans1.play();
    }
}

