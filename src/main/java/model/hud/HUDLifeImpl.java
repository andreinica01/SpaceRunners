package model.hud;

import java.util.stream.IntStream;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utilities.MagicEnumImage;
import utilities.MagicEnumInt;

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
    private ImageView[] lives;

    /*
     * Gamestatus: this is important for game cycle to continue.
     */
    private boolean gameStatus;

    /**
     * Constructor.
     * @param gamePane.
     */
    public HUDLifeImpl(final AnchorPane gamePane) {
        this.pane = gamePane;
        this.lifePoints = INITIAL_LIFE_POINTS;
        this.lives = new ImageView[MagicEnumInt.FOUR.getValue()];
        this.gameStatus = true;

        IntStream.range(MagicEnumInt.ZERO.getValue(), MagicEnumInt.THREE.getValue())
        	.forEach(i -> this.addLife(i));
    }

    /*
     * Getter
     */
    @Override
    public final int getLifePoints() {
        return this.lifePoints;
    }

    /*
     * Setter
     */
    @Override
    public final void lifeUp() {
        if (this.getLifePoints() < MagicEnumInt.FOUR.getValue()) {
            this.addLife(this.getLifePoints());
            this.lifePoints++;
        }
    }

    @Override
    public final void lifeDown() {
        if (this.getLifePoints() > LESS_LIVES_POSSIBLE) {
            this.lifePoints--;
            this.removeLife();
        } else {
            this.lifePoints = MagicEnumInt.ZERO.getValue();
            this.gameStatus = false;
        }
    }

    /**
     * Helper method used to add a life on the screen, it is combined with game
     * logic.
     *
     * @param index.
     */
    private void addLife(final int index) {
        this.lives[index] = new ImageView(MagicEnumImage.LIFE.getImage());
        this.lives[index].setLayoutX(index * SPACING);
        this.pane.getChildren().add(this.lives[index]);
        this.lives[index].setViewOrder(MagicEnumInt.COMMON_VIEW_ORDER.getValue());
    }

    /**
     * Helper method used to remove a life on the screen, it is combined with game
     * logic.
     */
    private void removeLife() {
        this.pane.getChildren().remove(this.lives[this.getLifePoints()]);
    }

    @Override
    public final boolean getStatus() {
        return this.gameStatus;
    }
}
