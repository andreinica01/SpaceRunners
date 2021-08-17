package controller.hudcontroller;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.hud.HUDViewImpl;
import view.gameField.GameField;

public class HUDControllerImpl implements HUDControllerDesign {

    private static final String LIFEPOINTS = "src/main/resources/Images/spaceshipLife.png";
    private HUDViewImpl hudViewImpl;
    private ImageView[] playerLifes;
            
    public HUDControllerImpl(final AnchorPane gameContainer) {
       createHUD(gameContainer);
    }

    @Override
    public void endGame() {
        //gamestage and gametimer ends
        //now call a new stage for saving data and quit
    }

    @Override
    public void createHUD(final AnchorPane gameContainer) {
        this.hudViewImpl = new HUDViewImpl("Points: ");
        this.hudViewImpl.setLayoutX(460);
        this.hudViewImpl.setLayoutY(20);
        gameContainer.getChildren().add(hudViewImpl);
        //this.hudViewImpl.toFront();
        
        
        playerLifes = new ImageView[HUDViewImpl.INITIAL_LIFE_POINTS];
        
        for(int i = 0; i < playerLifes.length; i++) {
            this.playerLifes[i] = new ImageView(LIFEPOINTS);
            this.playerLifes[i].setLayoutX(455 + (i * 50));
            this.playerLifes[i].setLayoutY(80);
            gameContainer.getChildren().add(playerLifes[i]);
        }
    }

    @Override
    public boolean checkGameStatus() {
        return this.hudViewImpl.getGameCondition();
    }

    @Override
    public int checkPoints() {
        return this.hudViewImpl.getPoints();
    }

    @Override
    public int checkLives() {
        return this.hudViewImpl.getLifePoints();
    }

    @Override
    public int checkLevel() {
        return this.hudViewImpl.getActualLevel();
    }
}
