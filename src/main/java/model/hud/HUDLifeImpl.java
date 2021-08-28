package model.hud;

import java.io.File;
import java.util.stream.IntStream;
import Utilities.HUDParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Class for building HUD lives components.
 */
public class HUDLifeImpl implements IHUDLife {

    /*
     * HUD structure
     */
    private static final int SPACING = 50;
    private static final int INITIAL_LIFE_POINTS = 3;
    private static final int LESS_LIVES_POSSIBLE = 1;

    /*
     * Control fields
     */
    private AnchorPane pane;
    private int lifePoints;
    private ImageView[] lives = new ImageView[HUDParameters.MAX_LIFE_POINTS];

    /*
     * Gamestatus = this is important for gamecycle to continue
     */
    private boolean gameStatus;

    /*
     * Constructor
     */
    public HUDLifeImpl(final AnchorPane gamePane) {
        this.pane = gamePane;
        this.lifePoints = INITIAL_LIFE_POINTS;
        this.gameStatus = HUDParameters.TRUE;

        IntStream.range(HUDParameters.ZERO, HUDParameters.THREE).forEach(i -> this.addLife(i));
    }

    /*
     * Getter
     */
    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    /*
     * Setter
     */
    @Override
    public void lifeUp() {
        if (this.getLifePoints() < HUDParameters.MAX_LIFE_POINTS) {
            this.addLife(this.lifePoints);
            this.lifePoints++;
        }
    }

    @Override
    public void lifeDown() {
        if (this.getLifePoints() > LESS_LIVES_POSSIBLE) {
            this.lifePoints--;
            this.removeLife();
        } else {
            this.lifePoints = HUDParameters.ZERO;
            this.gameStatus = HUDParameters.FALSE;
        }
    }

    /**
     * Helper method used to add a life on the screen, it is combined with game
     * logic.
     *
     * @param index.
     */
    public void addLife(final int index) {
        this.lives[index] = new ImageView(new Image(new File(HUDParameters.PNG_FOLDER + "life.png").toURI().toString(),
                SPACING, SPACING, HUDParameters.RATIO, HUDParameters.SMOOTH));
        this.lives[index].setLayoutX(index * SPACING);
        this.lives[index].setLayoutY(HUDParameters.FIVE);
        this.pane.getChildren().add(this.lives[index]);
        this.lives[index].setViewOrder(HUDParameters.VIEW_ORDER);
    }

    /**
     * Helper method used to remove a life on the screen, it is combined with game
     * logic.
     */
    public void removeLife() {
        this.pane.getChildren().remove(this.lives[this.lifePoints]);
    }

    @Override
    public boolean getStatus() {
        return this.gameStatus;
    }
}