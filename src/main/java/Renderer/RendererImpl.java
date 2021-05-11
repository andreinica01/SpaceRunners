package Renderer;

import java.util.Set;

import GameField.GameField;
import GameObjects.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class RendererImpl implements Renderer {

    private GameField gamefield;
    private Canvas gameCanvas;
    private Set<Entity> entities;

    GraphicsContext graphicsContext2D;

    public RendererImpl(GameField gamefield) {
        this.gamefield = gamefield;
        this.gameCanvas = this.gamefield.getCanvas();
        this.graphicsContext2D = this.gameCanvas.getGraphicsContext2D();
        this.entities = this.gamefield.getActiveEntities();
    }

    private void renderEntities() {

        entities.forEach(entity -> {
            this.graphicsContext2D.drawImage(entity.getImage(), entity.getPosition().getX().intValue(),
                    entity.getPosition().getY().intValue());
        });

    }

    @Override
    public void render() {

        this.graphicsContext2D.clearRect(0, 0, this.gamefield.getWidth().intValue(),this.gamefield.getHeight().intValue());
        this.renderEntities();

    }

 
}
