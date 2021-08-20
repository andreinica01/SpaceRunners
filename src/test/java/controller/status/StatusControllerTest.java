package controller.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;


class StatusControllerTest {
	
	private SpaceShip player = new SpaceShip(); 
	private StatusController controller = new StatusController(player);
	private StatusFactory factory = new StatusFactory();
	
	@Test
	void bonuLifeTest() {
		refreshField();
		player.setLifePoints(3);
		controller.applyEffect(factory.createStatus(StatusEnum.BonusLife));
		assertTrue(Integer.valueOf(4) == (player.getLifePoints()));
		controller.applyEffect(factory.createStatus(StatusEnum.BonusLife));
		assertTrue(Integer.valueOf(4) == (player.getLifePoints()));
	}
	
	@Test
	void BonusSpeedTest() {
		refreshField();
		Status bonusSpeed = factory.createStatus(StatusEnum.BonusSpeed);
		double speed = player.getSpeed().doubleValue() * bonusSpeed.getBoostFactor();
		controller.applyEffect(bonusSpeed);
		assertEquals(player.getSpeed().doubleValue(), speed);
	}
	
	@Test
	void MalusSpeedTest() {
		refreshField();
		Status malusSpeed = factory.createStatus(StatusEnum.MalusSpeed);
		double speed = player.getSpeed().doubleValue() * malusSpeed.getBoostFactor();
		controller.applyEffect(malusSpeed);
		assertEquals(player.getSpeed().doubleValue(), speed);
	}
	
	/*
	@Test
	void applyEffectsOneTime() {
		
		//Testing BonusSpeed
		
		//Testing MalusCommand
		controller.applyEffect(factory.createStatus(StatusEnum.MalusCommand));
		waitUntilApplied(StatusEnum.MalusCommand);
		assertTrue(player.isInvertedCommand());
		//Testing MalusFire
		controller.applyEffect(factory.createStatus(StatusEnum.MalusFire));
		waitUntilApplied(StatusEnum.MalusFire);
		assertFalse(player.getCanFire());
		//Testing MalusSpeed

	}*/


	
	void refreshField () {
		this.player = new SpaceShip();
		this.controller = new StatusController(player);
		this.factory = new StatusFactory();
	}
	
}
