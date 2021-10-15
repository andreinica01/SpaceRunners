package model.hud;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import utilities.MagicEnumInt;
import view.gameField.GameField;
import view.gameField.GameFieldImpl;

public final class HUDTest {

    private static final int POINTS_MAX_TESTER = 998;
    private static final int POINTS_MIN_TESTER = 1;
    private static final int MAX_LIFE_TESTER = 3;
    private static final int MIN_LIFE_TESTER = 1;

    private IHUDLife lifeModel;
    private IHUDBonus bonusModel;
    private IHUDPoints pointsModel;
    private AnchorPane gamePane;
    private GameField gameField;

    public HUDTest() {
        new JFXPanel();
        this.gamePane = new AnchorPane();
        this.gameField = new GameFieldImpl(MagicEnumInt.WIDTH.getValue(), MagicEnumInt.HEIGHT.getValue());
        this.lifeModel = new HUDLifeImpl(this.gamePane);
        this.bonusModel = new HUDBonusImpl(this.gameField);
        this.pointsModel = new HUDPointsImpl();
    }

    @Test
    public void testLifeCounterBehaviour() {
        /*
         * At the beginning of the game, life points of the player must be set to 3. Now
         * let's see if the counter works properly. Also, game status must be true.
         */
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.THREE.getValue());
        assertTrue(this.lifeModel.getStatus());

        /*
         * Life goes up! Game status is still true. Lives cannot increment further.
         */
        this.lifeModel.lifeUp();
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.FOUR.getValue());
        assertTrue(this.lifeModel.getStatus());
        this.lifeModel.lifeUp();
        assertFalse(this.lifeModel.getLifePoints() == MagicEnumInt.FIVE.getValue());
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.FOUR.getValue());

        /*
         * Several hits taken, life points drops to one. Game status is still true.
         */
        this.lifeModel.lifeDown();
        this.lifeModel.lifeDown();
        this.lifeModel.lifeDown();
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.ONE.getValue());
        assertTrue(this.lifeModel.getStatus());

        /*
         * Now the player dies, life points automatically sets himself to 0 and
         * gameStatus bacome false. Games ends!
         */
        this.lifeModel.lifeDown();
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.ZERO.getValue());
        assertFalse(this.lifeModel.getStatus());
    }

    @Test
    public void testBonusCounterBehaviour() {

    }
}
