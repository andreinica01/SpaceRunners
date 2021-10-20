package model.hud;

import java.io.File;
import java.util.stream.IntStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.status.StatusEnum;
import utilities.MagicEnumInt;
import utilities.MagicEnumString;
import view.gameField.GameField;

public class HUDBonusImpl implements IHUDBonus {

    /*
     * HUD parameters
     */
    private static final int SPACING = 30;
    private static final int X_LAYOUT = 50;
    private static final int Y_TRANSLATION = 645;

    /*
     * Control fields
     */
    private ImageView[] bonus = new ImageView[MagicEnumInt.FIVE.getValue()];
    private boolean[] statusTracker;
    private GameField gameField;

    /**
     * Constructor.
     * 
     * @param gameField
     */
    public HUDBonusImpl(final GameField gameField) {
        this.gameField = gameField;
        this.statusTracker = new boolean[MagicEnumInt.FIVE.getValue()];
        this.addBonuses();
    }

    @Override
    public final ImageView[] getBonus() {
        return this.bonus;
    }

    /**
     * Add all bonuses.
     */
    private void addBonuses() {

        IntStream.range(MagicEnumInt.ZERO.getValue(), MagicEnumInt.FIVE.getValue()).forEach(index -> {
            this.bonus[index] = new ImageView(new Image(
                    new File(MagicEnumString.IMAGE_FOLDER.getValue() + "bonus" + index + ".png").toURI().toString(),
                    SPACING, SPACING, false, true));
            this.bonus[index].setLayoutX(MagicEnumInt.WIDTH.getValue() - X_LAYOUT);
            this.bonus[index].setLayoutY(index * -SPACING);
            this.bonus[index].setTranslateY(Y_TRANSLATION);
            this.bonus[index].setViewOrder(MagicEnumInt.COMMON_VIEW_ORDER.getValue());
        });
    }

    @Override
    public final void showBonus(final StatusEnum bonus) {
        try {
            switch (bonus) {
            case BonusLife:
                this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.ZERO.getValue()]);
                this.statusTracker[MagicEnumInt.ZERO.getValue()] = true;
                break;
            case BonusSpeed:
                this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.ONE.getValue()]);
                this.statusTracker[MagicEnumInt.ONE.getValue()] = true;
                break;
            case MalusCommand:
                this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.TWO.getValue()]);
                this.statusTracker[MagicEnumInt.TWO.getValue()] = true;
                break;
            case MalusFire:
                this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.THREE.getValue()]);
                this.statusTracker[MagicEnumInt.THREE.getValue()] = true;
                break;
            case MalusSpeed:
                this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.FOUR.getValue()]);
                this.statusTracker[MagicEnumInt.FOUR.getValue()] = true;
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
            this.statusTracker[bonus.ordinal()] = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final boolean getTracker(final int index) {
        return this.statusTracker[index];
    }
}
