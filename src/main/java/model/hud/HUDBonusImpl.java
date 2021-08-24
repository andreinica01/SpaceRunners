package model.hud;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.status.StatusEnum;
import view.gameField.GameField;

public class HUDBonusImpl implements IHUDBonus {

    /*
     * HUD parameters
     */
    private final static int DIMENSIONS = 30;
    private final static int SPACING = 30;
    private static final double X_LAYOUT = 450;
    private static final double X_TRANSLATION = 645;

    /*
     * Control fields
     */
    private ImageView[] bonus = new ImageView[HUDParameters.TOTAL_BONUS];
    private GameField gamfield;

    /*
     * Constructor
     */
    public HUDBonusImpl(final GameField gamefield) {

        this.gamfield = gamefield;

        this.addBonuses();
    }

    @Override
    public ImageView[] getBonusTaken() {
        return this.bonus;
    }

    @Override
    public void addBonuses() {

        for (StatusEnum status : StatusEnum.values()) {

            this.bonus[status.ordinal()] = new ImageView(
            new Image(new File(HUDParameters.PNG_FOLDER + "Bonus"+status.ordinal()+".png").toURI().toString(), DIMENSIONS,
             DIMENSIONS, HUDParameters.RATIO, HUDParameters.SMOOTH));
            this.bonus[status.ordinal()].setLayoutX(X_LAYOUT);
            this.bonus[status.ordinal()].setLayoutY(status.ordinal() * -SPACING);
            this.bonus[status.ordinal()].setTranslateY(X_TRANSLATION);
            this.bonus[status.ordinal()].setViewOrder(HUDParameters.VIEW_ORDER);

            this.gamfield.getGameContainer().getChildren().add(this.bonus[status.ordinal()]);

        }

    }

    
    

    @Override
    public void showBonus(StatusEnum bonus) {
        this.bonus[bonus.ordinal()].setDisable(false);
      //  this.gamfield.getGameContainer().getChildren().remove(this.bonus[index]);

        
    }

    @Override
    public void hideBonus(StatusEnum bonus) {
        this.bonus[bonus.ordinal()].setDisable(true);
        
    }
}
