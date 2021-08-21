package controller.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import model.status.bonus.BonusSpeed;

class StatusControllerTest {

	private SpaceShip player = new SpaceShip();
	private StatusController controller = new StatusController(player);
	private StatusFactory factory = new StatusFactory();

	@Test
	void bonuLifeTest() {
		refreshField();
		controller.applyEffect(factory.createStatus(StatusEnum.BonusLife));
		waitUntilApplied();
		assertEquals(4, player.getLifePoints());
	}

	@Test
	void bonusSpeedTest() {
		refreshField();
		Status bonusSpeed = factory.createStatus(StatusEnum.BonusSpeed);
		double speed = player.getSpeed().doubleValue() * bonusSpeed.getBoostFactor();
		controller.applyEffect(bonusSpeed);
		waitUntilApplied();
		assertEquals(player.getSpeed().doubleValue(), speed);
	}

	@Test
	void malusCommandTest() {
		refreshField();
		controller.applyEffect(factory.createStatus(StatusEnum.MalusCommand));
		waitUntilApplied();
		assertTrue(player.isInvertedCommand()); // Testing MalusFire
	}

	@Test
	void malusFire() {
		refreshField();
		controller.applyEffect(factory.createStatus(StatusEnum.MalusFire));
		waitUntilApplied();
		assertFalse(player.getCanFire());
	}
	@Test
	void malusSpeedTest() {
		refreshField();
		Status malusSpeed = factory.createStatus(StatusEnum.MalusSpeed);
		double speed = player.getSpeed().doubleValue() * malusSpeed.getBoostFactor();
		controller.applyEffect(malusSpeed);
		waitUntilApplied();
		assertEquals(player.getSpeed().doubleValue(), speed);
	}
	@Test
	void refreshingTimeTest () {
		bonusSpeedTest();
		long timeSpan = (long) (new BonusSpeed().getDuration() - 2.0) * 1000;
		while(controller.getPlayerStatus().get(StatusEnum.BonusSpeed).get().getDelay(TimeUnit.MILLISECONDS) > timeSpan);
		controller.applyEffect(factory.createStatus(StatusEnum.BonusSpeed));
		waitUntilApplied();
		assertTrue(controller.getPlayerStatus().get(StatusEnum.BonusSpeed).get().getDelay(TimeUnit.MILLISECONDS) > timeSpan);
	}
	

	/*
	 * }
	 * 
	 * @Test void applyEffectsOneTime() {
	 * 
	 * //Testing BonusSpeed
	 * 
	 * //Testing MalusCommand
	 * controller.applyEffect(factory.createStatus(StatusEnum.MalusCommand));
	 * waitUntilApplied(StatusEnum.MalusCommand);
	 * assertTrue(player.isInvertedCommand()); //Testing MalusFire
	 * controller.applyEffect(factory.createStatus(StatusEnum.MalusFire));
	 * waitUntilApplied(StatusEnum.MalusFire); assertFalse(player.getCanFire());
	 * //Testing MalusSpeed
	 * 
	 * }
	 */

	private void refreshField() {
		this.player = new SpaceShip();
		this.controller = new StatusController(player);
		this.factory = new StatusFactory();
	}

	private void waitUntilApplied() {
		SpaceShip p2 = new SpaceShip();
		while (player.equals(p2))
			;
	}

}
