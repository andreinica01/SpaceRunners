package controller.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import model.ship.PlayerSpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import model.status.bonus.BonusSpeed;

/**
 * Class Test to verify the correct Status behavior
 */
class StatusControllerTest {

	private PlayerSpaceShip player;
	private StatusController controller;
	private StatusFactory factory;
	@SuppressWarnings("unused")
	private JFXPanel panel; // Needed for test purpose

	public StatusControllerTest() {
		this.panel = new JFXPanel();
		this.player = new PlayerSpaceShip();
		this.controller = new StatusController(this.player);
		this.factory = new StatusFactory();
	}

	/**
	 * Testing if BonusLife is applied
	 */
	@Test
	void bonuLifeTest() {
		controller.applyEffect(factory.createStatus(StatusEnum.BonusLife));
		Integer prevValue = player.getLifePoints();
		waitUntilApplied(() -> prevValue.equals(player.getLifePoints()));
		assertEquals(4, player.getLifePoints());
	}

	/**
	 * Testing if BonusSpeed is applied
	 */
	@Test
	void bonusSpeedTest() {
		Status bonusSpeed = factory.createStatus(StatusEnum.BonusSpeed);
		Double speed = player.getSpeed().doubleValue() * bonusSpeed.getBoostFactor();
		Double prevValue = player.getSpeed().doubleValue();
		controller.applyEffect(bonusSpeed);
		waitUntilApplied(() -> prevValue.equals(player.getSpeed().doubleValue()));
		assertEquals(player.getSpeed().doubleValue(), speed);
	}

	/**
	 * Testing if MalusCommand is applied
	 */
	@Test
	void malusCommandTest() {
		Boolean prevValue = player.isInvertedCommand();
		controller.applyEffect(factory.createStatus(StatusEnum.MalusCommand));
		waitUntilApplied(() -> prevValue.equals(player.isInvertedCommand()));
		assertTrue(player.isInvertedCommand()); // Testing MalusFire
	}

	/**
	 * Testing if MalusFire is applied
	 */
	@Test
	void malusFire() {
		Boolean prevValue = player.getCanFire();
		controller.applyEffect(factory.createStatus(StatusEnum.MalusFire));
		waitUntilApplied(() -> prevValue.equals(player.getCanFire()));
		assertFalse(player.getCanFire());
	}

	/**
	 * Testing if MalusSpeed is applied
	 */
	@Test
	void malusSpeedTest() {
		Status malusSpeed = factory.createStatus(StatusEnum.MalusSpeed);
		Double speed = player.getSpeed().doubleValue() * malusSpeed.getBoostFactor();
		Double prevValue = player.getSpeed().doubleValue();
		controller.applyEffect(malusSpeed);
		waitUntilApplied(() -> prevValue.equals(player.getSpeed().doubleValue()));
		assertEquals(player.getSpeed().doubleValue(), speed);
	}

	/**
	 * Verify that cooldown is refreshed after applying for the second time, before
	 * the first expires, a Status.
	 */
	@Test
	void refreshTimeTest() {
		// Testing only one Status (with cooldown feature), because this mechanism is
		// shared
		bonusSpeedTest();
		long timeSpan = (long) (new BonusSpeed().getCoolDown() - 2.0) * 1000;
		// Waiting until cooldown goes down of a bit (timeSpan)
		while (controller.getPlayerStatus().get(StatusEnum.BonusSpeed).get().getDelay(TimeUnit.MILLISECONDS) > timeSpan)
			;
		// Testing changes
		controller.applyEffect(factory.createStatus(StatusEnum.BonusSpeed));
		waitUntilApplied(() -> controller.getPlayerStatus().get(StatusEnum.BonusSpeed).get()
				.getDelay(TimeUnit.MILLISECONDS) < timeSpan);
		assertTrue(controller.getPlayerStatus().get(StatusEnum.BonusSpeed).get()
				.getDelay(TimeUnit.MILLISECONDS) > timeSpan);
	}

	private void waitUntilApplied(Supplier<Boolean> condition) {
		while (condition.get()) {
		}
	}

}
