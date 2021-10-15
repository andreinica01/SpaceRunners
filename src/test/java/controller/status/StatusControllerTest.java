package controller.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import model.ship.PlayerSpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import view.gameField.GameField;
import view.gameField.GameFieldImpl;

/**
 * Class Test to verify the correct Status behavior.
 */
class StatusControllerTest {

    private PlayerSpaceShip player;
    private StatusController controller;
    private StatusFactory factory;
    private GameField gamefield;

    /**
     * Constructor.
     */
    StatusControllerTest() {
        new JFXPanel(); // Start a JavaFX application, needed for testing with JavaFX.
        this.gamefield = new GameFieldImpl(1920, 1080);
        this.player = new PlayerSpaceShip(this.gamefield);
        this.controller = new StatusController(this.player);
        this.factory = new StatusFactory();
    }

    /**
     * Testing if BonusLife is applied.
     */
    @Test
    void bonuLifeTest() {
        Integer prevValue = 3;
        player.setLifePoints(prevValue);
        controller.applyEffect(factory.createStatus(StatusEnum.BonusLife));
        waitUntilApplied(() -> prevValue.equals(player.getLifePoints()));
        assertEquals(prevValue + 1, player.getLifePoints());
    }

    /**
     * Testing if BonusSpeed is applied.
     */
    @Test
    void bonusSpeedTest() {
        Status bonusSpeed = factory.createStatus(StatusEnum.BonusSpeed);
        Double expectedSpeed = player.getSpeed().doubleValue() * bonusSpeed.getBoostFactor();
        Double prevValue = player.getSpeed().doubleValue();
        controller.applyEffect(bonusSpeed);
        waitUntilApplied(() -> prevValue.equals(player.getSpeed().doubleValue()));
        assertEquals(player.getSpeed().doubleValue(), expectedSpeed);
    }

    /**
     * Testing if MalusCommand is applied.
     */
    @Test
    void malusCommandTest() {
        Boolean prevValue = player.isInvertedCommand();
        controller.applyEffect(factory.createStatus(StatusEnum.MalusCommand));
        waitUntilApplied(() -> prevValue.equals(player.isInvertedCommand()));
        assertTrue(player.isInvertedCommand()); // Testing MalusFire
    }

    /**
     * Testing if MalusFire is applied.
     */
    @Test
    void malusFire() {
        Boolean prevValue = player.getCanFire();
        controller.applyEffect(factory.createStatus(StatusEnum.MalusFire));
        waitUntilApplied(() -> prevValue.equals(player.getCanFire()));
        assertFalse(player.getCanFire());
    }

    /**
     * Testing if MalusSpeed is applied.
     */
    @Test
    void malusSpeedTest() {
        Status malusSpeed = factory.createStatus(StatusEnum.MalusSpeed);
        Double expectedSpeed = player.getSpeed().doubleValue() * malusSpeed.getBoostFactor();
        Double prevValue = player.getSpeed().doubleValue();
        controller.applyEffect(malusSpeed);
        waitUntilApplied(() -> prevValue.equals(player.getSpeed().doubleValue()));
        assertEquals(player.getSpeed().doubleValue(), expectedSpeed);
    }

    /**
     * Verify that cooldown is refreshed after applying for the second time, before
     * the first expires, a Status (of the same type).
     */
    @Test
    void refreshTimeTest() {
        var status = factory.createStatus(StatusEnum.BonusSpeed);
        var cooldownMap = this.controller.getAllCooldown(TimeUnit.MILLISECONDS);
        // Applying the Status
        controller.applyEffect(status);

        // Waiting a delta time before re-applying the Status.
        double delta = 0.5; // seconds
        long timeSpan = TimeUnit.MILLISECONDS.convert((long) (status.getCoolDown() - delta), TimeUnit.SECONDS);
        waitUntilApplied(() -> cooldownMap.get(StatusEnum.BonusSpeed) < timeSpan);

        // Re-applying the Status
        controller.applyEffect(status);

        // Testing changes
        waitUntilApplied(() -> cooldownMap.get(StatusEnum.BonusSpeed) > timeSpan);
        assertTrue(cooldownMap.get(StatusEnum.BonusSpeed) < timeSpan);
    }
    
    /**
     * Waiting until the condition is satisfied.
     * This method will wait only for a certain quantity of time.
     * 
     * @param condition condition to satisfy.
     */
    private void waitUntilApplied(final Supplier<Boolean> condition) {
        Integer timeSpan = 500;
        CountDownLatch latch = new CountDownLatch(1);
        try {
            latch.await(timeSpan, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}