package controller.InputController;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import controller.inputController.InputControllerImpl;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utilities.InputCommand;

class InputControllerTest extends ApplicationTest {

	private InputControllerImpl inputControllerImpl;
	private Map<InputCommand, Boolean> tasks;

	public InputControllerTest() {
		Scene scene = new Scene(new Group());
		this.inputControllerImpl = new InputControllerImpl(scene);
		this.refreshTask();
	}
	/**
	 * Testing Movement by input simulation.
	 * Left, Right, None
	 */
	@Test
	void checkMovement() {
		//Left
		this.simulateTask(InputCommand.LEFT, true);
		assertTrue(this.tasks.get(InputCommand.LEFT));
		this.simulateTask(InputCommand.LEFT, false);
		//Right
		this.simulateTask(InputCommand.RIGHT, true);
		assertTrue(this.tasks.get(InputCommand.RIGHT));
		this.simulateTask(InputCommand.RIGHT, false);
		//Left and Right
		this.simulateTask(InputCommand.LEFT, true);
		this.simulateTask(InputCommand.RIGHT, true);
		assertFalse(this.tasks.get(InputCommand.LEFT));
		assertFalse(this.tasks.get(InputCommand.RIGHT));
		this.simulateTask(InputCommand.LEFT, false);
		this.simulateTask(InputCommand.RIGHT, false);
		//Staying
		this.simulateTask(InputCommand.LEFT, false);
		this.simulateTask(InputCommand.RIGHT, false);
		assertFalse(this.tasks.get(InputCommand.LEFT));
		assertFalse(this.tasks.get(InputCommand.RIGHT));
		
	}
	
	/**
	 * Testing attack by input simulation.
	 * Attack and his logic.
	 */
	@Test
	void checkAttack() {
		//Fire
		this.simulateTask(InputCommand.ATTACK, true);
		assertTrue(this.tasks.get(InputCommand.ATTACK));
		this.simulateTask(InputCommand.ATTACK, false);
		assertFalse(this.tasks.get(InputCommand.ATTACK));
		//Fire logic
		this.simulateTask(InputCommand.ATTACK, true);
		this.simulateTask(InputCommand.ATTACK, true);
		assertFalse(this.tasks.get(InputCommand.ATTACK));
	}

	/*
	 * Update task info.
	 */
	private void refreshTask() {
		this.tasks = this.inputControllerImpl.getControlStates();
	}

	/**
	 * Simulate a state of a Command.
	 * @param command
	 * @param state
	 */
	private void simulateTask(InputCommand command, boolean state) {
		this.manageKeys(this.getKeysListByCommand(command), state);
		this.refreshTask();
	}

	/**
	 * Return a list made of all the keys mapped to a specific command.
	 * @param command
	 * @return List<KeyCode>
	 */
	private List<KeyCode> getKeysListByCommand(InputCommand command) {
		var grouped = this.inputControllerImpl.getMapGrouped();
		return grouped.keySet().stream().filter(k -> k.equals(command)).map(k -> grouped.get(k)).findFirst().get();
	}

	/**
	 * Setting up a state (pressed or not) for every keys of the list.
	 * @param keysList
	 * @param state
	 */
	private void manageKeys(List<KeyCode> keysList, boolean state) {
		var pressedKeys = this.inputControllerImpl.getPressedKeys();
		keysList.forEach(e -> pressedKeys.put(e, state));
	}
}
