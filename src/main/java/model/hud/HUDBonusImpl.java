package model.hud;

import java.io.File;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import Utilities.HUDParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.status.StatusEnum;
import view.gameField.GameField;

public class HUDBonusImpl implements IHUDBonus {

    /*
     * HUD parameters
     */
    private static final int SPACING = 30;
    private static final double X_LAYOUT = 450;
    private static final double Y_TRANSLATION = 645;

    /*
     * Control fields
     */
    private ImageView[] bonus = new ImageView[HUDParameters.TOTAL_BONUS];
    private GameField gameField;

    /**
     * Constructor
     * @param gameField
     */
    public HUDBonusImpl(final GameField gameField) {
        this.gameField = gameField;

        this.addBonuses();
    }

    @Override
    public ImageView[] getBonus() {
        return this.bonus;
    }

    @Override
    public void addBonuses() {

        IntStream.range(HUDParameters.ZERO, HUDParameters.FIVE).forEach(index -> {
            this.bonus[index] = new ImageView(
                    new Image(new File(HUDParameters.PNG_FOLDER + "bonus" + index + ".png").toURI().toString(), SPACING, SPACING,
                            HUDParameters.RATIO, HUDParameters.SMOOTH));
            this.bonus[index].setLayoutX(X_LAYOUT);
            this.bonus[index].setLayoutY(index * -SPACING);
            this.bonus[index].setTranslateY(Y_TRANSLATION);
            this.bonus[index].setViewOrder(HUDParameters.VIEW_ORDER);
        });
    }

    @Override
    public void showBonus(final int index) {
        try {
            this.gameField.getGameContainer().getChildren().add(this.bonus[index]);
        } catch (Exception e) {
            this.hideBonus(index);
            this.showBonus(index);
            this.resetTime(index);
        } finally {
            
        }
    }

    @Override
    public void hideBonus(final int index) {
        this.gameField.getGameContainer().getChildren().remove(this.bonus[index]);
    }
    
    private void resetTime(final int index) {
        // TODO Auto-generated method stub        
    }
    
    public void statusHandler() {
        Map<StatusEnum, Boolean> map = this.gameField.getStatusController().getActiveStatus();
        
//        Stream.of(map.keySet())
//        .forEach(e -> {
//            if(map. == HUDParameters.TRUE) {
//                this.showBonus(e.);
//            }
//        });
    }
}