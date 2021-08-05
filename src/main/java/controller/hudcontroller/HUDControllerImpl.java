package controller.hudcontroller;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.hud.HUDViewImpl;
import view.gameField.GameField;

//public class HUDControllerImpl implements HUDControllerDesign {
//
//    private static final String LIFEPOINTS = "src/main/resources/Images/spaceshipLife.png";
//    private HUDViewImpl hudViewImpl;
//    private ImageView[] playerLifes;
//            
//    public HUDControllerImpl(final GameField gameContainer) {
//       createHUD(gameContainer);
//    }
//
//    @Override
//    public void endGame() {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void createHUD(final GameField gamePane) {
//        this.hudViewImpl = new HUDViewImpl("Points: " + hudViewImpl.getPoints());
//        this.hudViewImpl.setLayoutX(460);
//        this.hudViewImpl.setLayoutY(20);
//        gamePane.getPlayer().getNode().add(hudViewImpl);
//        
//        playerLifes = new ImageView[HUDViewImpl.INITIAL_LIFE_POINTS];
//        
//        for(int i = 0; i < playerLifes.length; i++) {
//            this.playerLifes[i] = new ImageView(LIFEPOINTS);
//            this.playerLifes[i].setLayoutX(455 + (i * 50));
//            this.playerLifes[i].setLayoutY(80);
//            gamePane.getChildren().add(playerLifes[i]);
//        }
//    }
//
//    @Override
//    public boolean checkGameStatus() {
//        return this.hudViewImpl.getGameCondition();
//    }
//
//    @Override
//    public int checkPoints() {
//        return this.hudViewImpl.getPoints();
//    }
//
//    @Override
//    public int checkLives() {
//        return this.hudViewImpl.getLifePoints();
//    }
//
//    @Override
//    public int checkLevel() {
//        return this.hudViewImpl.getActualLevel();
//    }
//}
