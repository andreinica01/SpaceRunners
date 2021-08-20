package controller.status;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;


class StatusControllerTest {
	
	private SpaceShip player; 
	private StatusController controller;
	private StatusFactory factory;
	
	@Test
	void applyEffectsOneTime() {
		this.player = new SpaceShip();
		this.controller = new StatusController(player);
		this.factory = new StatusFactory();
		System.out.println();
		//Testing BonusLife
		player.setLifePoints(3);
		controller.applyEffect(factory.createStatus(StatusEnum.BonusLife));
		
		assertEquals(player.getLifePoints(), 4);
		player.setLifePoints(4);
		controller.applyEffect(factory.createStatus(StatusEnum.BonusLife));
		assertEquals(player.getLifePoints(), 4);
		//Testing BonusSpeed
		Status bonusSpeed = factory.createStatus(StatusEnum.BonusSpeed);
		double speed = player.getSpeed().intValue() * bonusSpeed.getBoostFactor();
		controller.applyEffect(bonusSpeed);
		assertEquals(player.getSpeed(), speed);
		//Testing MalusCommand
		controller.applyEffect(factory.createStatus(StatusEnum.MalusCommand));
		assertEquals(player.isInvertedCommand(), true);
		//Testing MalusFire
		controller.applyEffect(factory.createStatus(StatusEnum.MalusFire));
		assertEquals(player.getCanFire(), false);
		//Testing MalusSpeed
		Status malusSpeed = factory.createStatus(StatusEnum.BonusSpeed);
		speed = player.getSpeed().intValue() * 1/malusSpeed.getBoostFactor();
		controller.applyEffect(malusSpeed);
		assertEquals(player.getSpeed(), speed);
	}
	
	@Test
	void applyEffectsMoreTime() {
		fail("Not yet implemented");
	}

	/*void refreshField () {
		this.player = new SpaceShip();
		this.controller = new StatusController(player);
	}*/
	
}
