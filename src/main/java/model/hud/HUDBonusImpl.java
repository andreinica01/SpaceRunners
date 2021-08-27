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
    private static final double X_TRANSLATION = 645;

    /*
     * Control fields
     */
    private ImageView[] bonus = new ImageView[HUDParameters.TOTAL_BONUS];
    private GameField gamefield;

    /*
     * Constructor
     */
    public HUDBonusImpl(final GameField gamefield) {
        this.gamefield = gamefield;

        this.addBonuses();
    }

    @Override
    public ImageView[] getBonusTaken() {
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
            this.bonus[index].setTranslateY(X_TRANSLATION);
            this.bonus[index].setViewOrder(HUDParameters.VIEW_ORDER);
        });
    }

    @Override
    public void showBonus(final int index) {
        try {
            this.gamefield.getGameContainer().getChildren().add(this.bonus[index]);
        } catch (Exception e) {
            this.hideBonus(index);
            this.showBonus(index);
        } finally {
            
        }
    }

    @Override
    public void hideBonus(final int index) {
        this.gamefield.getGameContainer().getChildren().remove(this.bonus[index]);
    }
    
    public void statusHandler() {
        Map<StatusEnum, Boolean> map = this.gamefield.getStatusController().getActiveStatus();
        
        Stream.of(map.values())
        .forEach(e -> {
            if(map.get(e) == HUDParameters.TRUE) {
                
            }
        });
    }
}