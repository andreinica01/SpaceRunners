package model.hud;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.gameField.GameField;

public class HUDBonusImpl implements IHUDBonus {

    /*
     * HUD parameters
     */
    private final static int SPACING = 30;
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
        int index = this.bonus.length;
        
        /*
         * Life
         */
        this.bonus[index] = new ImageView(new Image(new File(HUDParameters.PNG_FOLDER + ".png").toURI().toString(), SPACING, 
                SPACING, HUDParameters.RATIO, HUDParameters.SMOOTH));
        this.bonus[index].setLayoutX(X_LAYOUT);
        this.bonus[index].setLayoutY(index * -SPACING);
        this.bonus[index].setTranslateY(X_TRANSLATION);
        this.bonus[index].setViewOrder(HUDParameters.VIEW_ORDER);
        index++;
        
        /*
         * Speed bonus
         */
        this.bonus[index] = new ImageView(new Image(new File(HUDParameters.PNG_FOLDER + "BonusSpeed.png").toURI().toString(), SPACING, 
                SPACING, HUDParameters.RATIO, HUDParameters.SMOOTH));
        this.bonus[index].setLayoutX(X_LAYOUT);
        this.bonus[index].setLayoutY(index * -SPACING);
        this.bonus[index].setTranslateY(X_TRANSLATION);
        this.bonus[index].setViewOrder(HUDParameters.VIEW_ORDER);
        index++;
        
        /*
         * Speed malus
         */
        this.bonus[index] = new ImageView(new Image(new File(HUDParameters.PNG_FOLDER + ".png").toURI().toString(), SPACING, 
                SPACING, HUDParameters.RATIO, HUDParameters.SMOOTH));
        this.bonus[index].setLayoutX(X_LAYOUT);
        this.bonus[index].setLayoutY(index * -SPACING);
        this.bonus[index].setTranslateY(X_TRANSLATION);
        this.bonus[index].setViewOrder(HUDParameters.VIEW_ORDER);
        index++;
        
        /*
         * Command malus
         */
        this.bonus[index] = new ImageView(new Image(new File(HUDParameters.PNG_FOLDER + ".png").toURI().toString(), SPACING, 
                SPACING, HUDParameters.RATIO, HUDParameters.SMOOTH));
        this.bonus[index].setLayoutX(X_LAYOUT);
        this.bonus[index].setLayoutY(index * -SPACING);
        this.bonus[index].setTranslateY(X_TRANSLATION);
        this.bonus[index].setViewOrder(HUDParameters.VIEW_ORDER);
        index++;
        
        /*
         * Fire malus
         */
        this.bonus[index] = new ImageView(new Image(new File(HUDParameters.PNG_FOLDER + ".png").toURI().toString(), SPACING, 
                SPACING, HUDParameters.RATIO, HUDParameters.SMOOTH));
        this.bonus[index].setLayoutX(X_LAYOUT);
        this.bonus[index].setLayoutY(index * -SPACING);
        this.bonus[index].setTranslateY(X_TRANSLATION);
        this.bonus[index].setViewOrder(HUDParameters.VIEW_ORDER);
        index++;

//        for (StatusEnum status : StatusEnum.values()) {
//
//            this.bonus[status.ordinal()] = new ImageView(
//            new Image(new File(HUDParameters.PNG_FOLDER + "Bonus"+status.ordinal()+".png").toURI().toString(), DIMENSIONS,
//             DIMENSIONS, HUDParameters.RATIO, HUDParameters.SMOOTH));
//            this.bonus[status.ordinal()].setLayoutX(X_LAYOUT);
//            this.bonus[status.ordinal()].setLayoutY(status.ordinal() * -SPACING);
//            this.bonus[status.ordinal()].setTranslateY(X_TRANSLATION);
//            this.bonus[status.ordinal()].setViewOrder(HUDParameters.VIEW_ORDER);
//
//            this.gamfield.getGameContainer().getChildren().add(this.bonus[status.ordinal()]);
//
//        }
    }

    @Override
    public void showBonus(final int index) {
        this.gamefield.getGameContainer().getChildren().add(this.bonus[index]);
    }

    @Override
    public void hideBonus(final int index) {
        this.gamefield.getGameContainer().getChildren().remove(this.bonus[index]);
    }
}
