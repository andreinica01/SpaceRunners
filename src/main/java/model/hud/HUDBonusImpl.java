package model.hud;

import java.awt.List;
import java.io.File;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import Utilities.HUDParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.status.Status;
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
    private long[] cooldowns = new long[HUDParameters.FIVE];
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
    public void showBonus(final Status bonus) {
        try {
            switch (bonus.getStatusName()) {
            case BonusLife:
                this.gameField.getGameContainer().getChildren().add(this.bonus[HUDParameters.ZERO]);
                break;
            case BonusSpeed:
                this.gameField.getGameContainer().getChildren().add(this.bonus[HUDParameters.ONE]);
                break;
            case MalusCommand:
                this.gameField.getGameContainer().getChildren().add(this.bonus[HUDParameters.TWO]);
                break;
            case MalusFire:
                this.gameField.getGameContainer().getChildren().add(this.bonus[HUDParameters.THREE]);
                break;
            case MalusSpeed:
                this.gameField.getGameContainer().getChildren().add(this.bonus[HUDParameters.FOUR]);
                break;
           default:
                break;    
            }
        } catch (Exception e) {
            this.hideBonus(bonus);
            this.showBonus(bonus);
        }
    }

    @Override
    public void hideBonus(final Status bonus) {
        try {
            this.gameField.getGameContainer().getChildren().remove(this.bonus[bonus.getStatusName().ordinal()]);
        } catch (Exception e) {
            
        } finally {
            
        }
    }

    public void statusHandler(final Status status) {
        Map<StatusEnum, Boolean> map = this.gameField.getStatusController().getActiveStatus();
        
        Stream.of(map.keySet())
        .forEach(e -> {
            if(e.toString() == status.getStatusName().toString() && map.get(e).equals(HUDParameters.ZERO)){
                this.showBonus(status);
            } else if (e.toString() == status.getStatusName().toString() && map.get(e).equals(HUDParameters.FALSE)) {
                this.hideBonus(status);
            }
        });
    }
}