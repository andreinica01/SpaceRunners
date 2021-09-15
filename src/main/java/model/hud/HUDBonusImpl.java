package model.hud;

import java.io.File;
import java.util.stream.IntStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.status.StatusEnum;
import utilities.HUDParameters;
import utilities.VariousMagicNumbers;
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
     * Constructor.
     * @param gameField
     */
    public HUDBonusImpl(final GameField gameField) {
        this.gameField = gameField;

        this.addBonuses();
    }

    @Override
    public final ImageView[] getBonus() {
        return this.bonus;
    }

    @Override
    public final void addBonuses() {

        IntStream.range(VariousMagicNumbers.ZERO, VariousMagicNumbers.FIVE).forEach(index -> {
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
    public final void showBonus(final StatusEnum bonus) {
        try {
            switch (bonus) {
            case BonusLife:
                this.gameField.getGameContainer().getChildren().add(this.bonus[VariousMagicNumbers.ZERO]);
                break;
            case BonusSpeed:
                this.gameField.getGameContainer().getChildren().add(this.bonus[VariousMagicNumbers.ONE]);
                break;
            case MalusCommand:
                this.gameField.getGameContainer().getChildren().add(this.bonus[VariousMagicNumbers.TWO]);
                break;
            case MalusFire:
                this.gameField.getGameContainer().getChildren().add(this.bonus[VariousMagicNumbers.THREE]);
                break;
            case MalusSpeed:
                this.gameField.getGameContainer().getChildren().add(this.bonus[VariousMagicNumbers.FOUR]);
                break; 
            default:
            } 

        } catch (Exception e) {
            this.hideBonus(bonus);
            this.showBonus(bonus);
        }
    }

    @Override
    public final void hideBonus(final StatusEnum bonus) {
        try {
            this.gameField.getGameContainer().getChildren().remove(this.bonus[bonus.ordinal()]);
        } catch (Exception e) { 
        } 
    }
}
