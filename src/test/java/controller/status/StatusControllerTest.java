package controller.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import model.status.bonus.BonusSpeed;

class StatusControllerTest {

	private SpaceShip player;
	private StatusController controller;
	private StatusFactory factory;
	private JFXPanel panel; // Needed for test purpose


	public StatusControllerTest() {
		renewField();
		this.factory = new StatusFactory();
		this.panel = new JFXPanel();
	}
	
	@Test
	void bonuLifeTest() {
		renewField();
		controller.applyEffect(factory.createStatus(StatusEnum.BonusLife));
		SpaceShip p2 = player.clone();
		waitUntilApplied(p2);
		System.out.println(player.getLifePoints());		
		assertEquals(4, player.getLifePoints());
	}

	@Test
	void bonusSpeedTest() {
		renewField();
		Status bonusSpeed = factory.createStatus(StatusEnum.BonusSpeed);
		double speed = player.getSpeed().doubleValue() * bonusSpeed.getBoostFactor();
		SpaceShip p2 = player.clone();
		controller.applyEffect(bonusSpeed);
		waitUntilApplied(p2);
		assertEquals(player.getSpeed().doubleValue(), speed);
	}

	@Test
	void malusCommandTest() {
		renewField();
		SpaceShip p2 = player.clone();
		System.out.println(p2.isInvertedCommand());
		controller.applyEffect(factory.createStatus(StatusEnum.MalusCommand));
		System.out.println(player.isInvertedCommand());
		waitUntilApplied(p2);
		System.out.println(player.isInvertedCommand());
		assertTrue(player.isInvertedCommand()); // Testing MalusFire
	}

	@Test
	void malusFire() {
		renewField();
		SpaceShip p2 = player.clone();
		controller.applyEffect(factory.createStatus(StatusEnum.MalusFire));
		waitUntilApplied(p2);
		assertFalse(player.getCanFire());
	}

	@Test
	void malusSpeedTest() {
		renewField();
		Status malusSpeed = factory.createStatus(StatusEnum.MalusSpeed);
		double speed = player.getSpeed().doubleValue() * malusSpeed.getBoostFactor();
		SpaceShip p2 = player.clone();
		controller.applyEffect(malusSpeed);
		waitUntilApplied(p2);
		System.out.println(player.getSpeed());
		assertEquals(player.getSpeed().doubleValue(), speed);
	}

	@Test
	void refreshTimeTest() {
		renewField();
		//Testing only one Status (with cooldown feature), because this mechanism is shared
		bonusSpeedTest();
		long timeSpan = (long) (new BonusSpeed().getDuration() - 2.0) * 1000;
		//Waiting until delay goes down of a bit (timeSpan)
		while (controller.getPlayerStatus().get(StatusEnum.BonusSpeed).get()
				.getDelay(TimeUnit.MILLISECONDS) > timeSpan);
		//Testing changes
		controller.applyEffect(factory.createStatus(StatusEnum.BonusSpeed));
		waitUntilApplied(new SpaceShip());
		assertTrue(controller.getPlayerStatus().get(StatusEnum.BonusSpeed).get()
				.getDelay(TimeUnit.MILLISECONDS) > timeSpan);
	}

	private void waitUntilApplied(SpaceShip p2) {
		while (player.equals(p2));
	}
	
	private void renewField() {
		this.player = new SpaceShip();
		this.controller = new StatusController(this.player);
	}

}
