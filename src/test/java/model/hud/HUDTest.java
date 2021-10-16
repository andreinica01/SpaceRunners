package model.hud;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import model.status.StatusEnum;
import utilities.MagicEnumInt;
import view.gameField.GameField;
import view.gameField.GameFieldImpl;

/**
 * 
 */
public final class HUDTest {

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
        System.out.println("Lives at the beginning: " + this.lifeModel.getLifePoints());
        System.out.println("Status at the beginning: " + this.lifeModel.getStatus() + "\n");

        /*
         * Life goes up! Game status is still true. Lives cannot increment further.
         */
        this.lifeModel.lifeUp();
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.FOUR.getValue());
        assertTrue(this.lifeModel.getStatus());
        this.lifeModel.lifeUp();
        assertFalse(this.lifeModel.getLifePoints() == MagicEnumInt.FIVE.getValue());
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.FOUR.getValue());
        System.out.println("Lives up: " + this.lifeModel.getLifePoints());
        System.out.println("Status at lives up: " + this.lifeModel.getStatus() + "\n");

        /*
         * Several hits taken, life points drops to one. Game status is still true.
         */
        this.lifeModel.lifeDown();
        this.lifeModel.lifeDown();
        this.lifeModel.lifeDown();
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.ONE.getValue());
        assertTrue(this.lifeModel.getStatus());
        System.out.println("Lives down: " + this.lifeModel.getLifePoints());
        System.out.println("Status at lives down: " + this.lifeModel.getStatus() + "\n");

        /*
         * Now the player dies, life points automatically sets himself to 0 and
         * gameStatus bacome false. Games ends!
         */
        this.lifeModel.lifeDown();
        assertTrue(this.lifeModel.getLifePoints() == MagicEnumInt.ZERO.getValue());
        assertFalse(this.lifeModel.getStatus());
        System.out.println("Lives at the end: " + this.lifeModel.getLifePoints());
        System.out.println("Status at the end: " + this.lifeModel.getStatus() + "\n");

        /*
         * This situation will cause the end of the game!
         */
    }

    @Test
    public void testBonusCounterBehaviour() {
        /*
         * I'm mapping the status in 2 arrays in which i will consider each of 5 bonus status
         * values. At the beginning, all bonuses are hidden.
         * The status order that I've implemented is:
         * Life up
         * Speed up
         * Inverted Command
         * No shoot
         * Speed down
         */
        for (int i = 0; i < MagicEnumInt.FIVE.getValue(); i++) {
            assertFalse(this.bonusModel.getTracker(i));
            System.out.println("Bonus " + i + " is " + this.bonusModel.getTracker(i));
        }

        /*
         * So if we want to activate one we can check if it's hidden or not. One is ..
         */
        this.bonusModel.showBonus(StatusEnum.BonusSpeed);
        assertTrue(this.bonusModel.getTracker(StatusEnum.BonusSpeed.ordinal()));
        System.out.println("\n" + StatusEnum.BonusSpeed.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.BonusSpeed.ordinal()));

        /*
         * .. and for example an another one is not.
         */
        assertFalse(this.bonusModel.getTracker(StatusEnum.BonusLife.ordinal()));
        System.out.println(StatusEnum.BonusLife.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.BonusLife.ordinal()));

        /*
         * We can do this for every bonus ..
         */
        System.out.println("\nActivating all others bonuses:");
        this.bonusModel.showBonus(StatusEnum.BonusLife);
        assertTrue(this.bonusModel.getTracker(StatusEnum.BonusLife.ordinal()));
        System.out.println(StatusEnum.BonusLife.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.BonusLife.ordinal()));

        this.bonusModel.showBonus(StatusEnum.MalusCommand);
        assertTrue(this.bonusModel.getTracker(StatusEnum.MalusCommand.ordinal()));
        System.out.println(StatusEnum.MalusCommand.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.MalusCommand.ordinal()));

        this.bonusModel.showBonus(StatusEnum.MalusFire);
        assertTrue(this.bonusModel.getTracker(StatusEnum.MalusFire.ordinal()));
        System.out.println(StatusEnum.MalusFire.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.MalusFire.ordinal()));

        this.bonusModel.showBonus(StatusEnum.MalusSpeed);
        assertTrue(this.bonusModel.getTracker(StatusEnum.MalusSpeed.ordinal()));
        System.out.println(StatusEnum.MalusSpeed.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.MalusSpeed.ordinal()) + "\n");

        /*
         * .. multiple activations can't cause problems.
         */
        try {
            this.bonusModel.showBonus(StatusEnum.BonusLife);
            assertTrue(this.bonusModel.getTracker(StatusEnum.BonusLife.ordinal()));
            System.out.println(StatusEnum.BonusLife.toString() + " is activated? " 
                                + this.bonusModel.getTracker(StatusEnum.BonusLife.ordinal()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * .. and also we can deactivate them.
         */
        System.out.println("Trying to dectivating all of them:");
        this.bonusModel.hideBonus(StatusEnum.BonusSpeed);
        assertTrue(!this.bonusModel.getTracker(StatusEnum.BonusSpeed.ordinal()));
        System.out.println(StatusEnum.BonusSpeed.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.BonusSpeed.ordinal()));

        this.bonusModel.hideBonus(StatusEnum.BonusLife);
        assertTrue(!this.bonusModel.getTracker(StatusEnum.BonusLife.ordinal()));
        System.out.println(StatusEnum.BonusLife.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.BonusLife.ordinal()));

        this.bonusModel.hideBonus(StatusEnum.MalusCommand);
        assertTrue(!this.bonusModel.getTracker(StatusEnum.MalusCommand.ordinal()));
        System.out.println(StatusEnum.MalusCommand.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.MalusCommand.ordinal()));

        this.bonusModel.hideBonus(StatusEnum.MalusFire);
        assertTrue(!this.bonusModel.getTracker(StatusEnum.MalusFire.ordinal()));
        System.out.println(StatusEnum.MalusFire.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.MalusFire.ordinal()));

        this.bonusModel.hideBonus(StatusEnum.MalusSpeed);
        assertTrue(!this.bonusModel.getTracker(StatusEnum.MalusSpeed.ordinal()));
        System.out.println(StatusEnum.MalusSpeed.toString() + " is activated? " 
                            + this.bonusModel.getTracker(StatusEnum.MalusSpeed.ordinal()) + "\n");
    }

    @Test
    public void testPointsCounterBehaviour() {
        /*
         * At the beginning of the game the points are 0.
         */
        assertTrue(this.pointsModel.getPoints() == MagicEnumInt.ZERO.getValue());
        System.out.println("Points at the beginning: " + this.pointsModel.getPoints());

        /*
         * Points are incremented by killing enemies! At the end of this section
         * points must be 11.
         */
        this.pointsModel.pointsUp();
        assertTrue(this.pointsModel.getPoints() == MagicEnumInt.ONE.getValue());
        System.out.println("Points up by 1: " + this.pointsModel.getPoints());
        this.pointsModel.pointsSetter(MagicEnumInt.TEN.getValue());
        assertTrue(this.pointsModel.getPoints() == (MagicEnumInt.TEN.getValue()
                                                    + MagicEnumInt.ONE.getValue()));
        System.out.println("Points up by 10: " + this.pointsModel.getPoints());

        /*
         * When you take damage points will decrease by 5, such a pain! At the end of this section
         * points must be 6.
         */
        this.pointsModel.pointsDown();
        assertTrue(this.pointsModel.getPoints() == (MagicEnumInt.TEN.getValue()
                                                    + MagicEnumInt.ONE.getValue()
                                                    - MagicEnumInt.FIVE.getValue()));
        System.out.println("Points down by 5: " + this.pointsModel.getPoints() + "\n");

        /*
         * Now let's imagine that the player takes too many hits and all of his points drops 
         * to 0. Points counter can't go below 0 ..
         */
        this.pointsModel.pointsDown();
        assertTrue(this.pointsModel.getPoints() == MagicEnumInt.ONE.getValue());
        System.out.println("Points should be 1 after another points down: "
                            + this.pointsModel.getPoints());
        this.pointsModel.pointsDown();
        System.out.println("Should be 0 and not -4 after another points down: "
                            + this.pointsModel.getPoints() + "\n");
        /*
         * .. and can't also go over 999.
         */
        this.pointsModel.pointsSetter(MagicEnumInt.MAX_POINTS_POSSIBLE.getValue());
        assertTrue(this.pointsModel.getPoints() == MagicEnumInt.MAX_POINTS_POSSIBLE.getValue());
        System.out.println("Points should be 999 if the player has skills: "
                + this.pointsModel.getPoints());
        this.pointsModel.pointsUp();
        this.pointsModel.pointsUp();
        this.pointsModel.pointsUp();
        this.pointsModel.pointsUp();
        assertTrue(this.pointsModel.getPoints() == MagicEnumInt.MAX_POINTS_POSSIBLE.getValue());
        System.out.println("Points should be still 999 after many points earned: "
                + this.pointsModel.getPoints());
    }
}
