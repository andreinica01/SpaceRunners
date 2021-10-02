package controller.inputController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utilities.InputCommand;

public class InputControllerImpl {

	private Map<KeyCode, Boolean> pressedKeys;
	private Map<KeyCode, InputCommand> commandKeys;
	private Map<InputCommand, Boolean> task;

	private Scene scene;

	// Flag used to implement fire logic.
	private boolean fireFlag;

	/**
	 * Constructor.
	 * 
	 * @param scene
	 * @param player
	 */
	public InputControllerImpl(final Scene scene) {
		this.pressedKeys = new HashMap<>();
		this.commandKeys = new HashMap<>();
		this.task = new HashMap<>();
		this.scene = scene;
		this.fireFlag = true;
		this.initializeKeys();
		this.initializeControlStates();
		this.listeners();
	}

	/**
	 * Initialize the game controls.
	 */
	private void initializeControlStates() {
		List.of(InputCommand.values()).forEach(e -> this.task.put(e, false));
		List.of(KeyCode.values()).forEach(e -> this.pressedKeys.put(e, false));
	}

	/**
	 * Initialize keys that you can press in the game.
	 */
	private void initializeKeys() {
		this.commandKeys.put(KeyCode.A, InputCommand.LEFT);
		this.commandKeys.put(KeyCode.LEFT, InputCommand.LEFT);
		this.commandKeys.put(KeyCode.D, InputCommand.RIGHT);
		this.commandKeys.put(KeyCode.RIGHT, InputCommand.RIGHT);
		this.commandKeys.put(KeyCode.P, InputCommand.ATTACK);
		this.commandKeys.put(KeyCode.SPACE, InputCommand.ATTACK);
	}

	/**
	 * Listeners used for performing an action after a key is pressed.
	 */
	public void listeners() {
		this.scene.setOnKeyPressed(e -> {
			this.pressedKeys.put(e.getCode(), true);
		});

		this.scene.setOnKeyReleased(e -> {
			this.pressedKeys.put(e.getCode(), false);
		});
	}

	/**
	 * Update state of tasks, based on pressed keys.
	 */
	private void updateTask() {
		var grouped = this.getMapGrouped();

		grouped.keySet().stream()
				.filter(key -> grouped.get(key).stream().filter(e -> this.pressedKeys.get(e)).count() > 0)
				.forEach(key -> this.task.put(key, true));

		grouped.keySet().stream()
				.filter(key -> grouped.get(key).stream().filter(e -> this.pressedKeys.get(e)).count() == 0)
				.forEach(key -> this.task.put(key, false));
	}

	public Map<InputCommand, List<KeyCode>> getMapGrouped() {
		return this.commandKeys.keySet().stream().collect(Collectors.groupingBy(e -> this.commandKeys.get(e)));
	}

	/**
	 * Update player task.
	 */
	private void updatePlayerTasks() {
		this.updateTask();

		if (this.task.get(InputCommand.LEFT) && this.task.get(InputCommand.RIGHT)) {
			this.task.put(InputCommand.LEFT, false);
			this.task.put(InputCommand.RIGHT, false);
		}
		this.fireLogic();
	}

	/**
	 * Managing fire logic. Making player firing only 1 bullet at time.
	 */
	private void fireLogic() {
		if (this.task.get(InputCommand.ATTACK)) {
			if (this.getFireFlag()) {
				this.setFireFlag(false);
			} else {
				this.task.put(InputCommand.ATTACK, false);
			}
		} else {
			this.setFireFlag(true);
		}

	}

	/**
	 * @return a map containing player tasks.
	 */
	public Map<InputCommand, Boolean> getControlStates() {
		this.updatePlayerTasks();
		return this.task;
	}
	/**
	 * Get the Map mapping keys to his state (pressed or not).
	 * @return Map<KeyCode, Boolean>
	 */
	public Map<KeyCode, Boolean> getPressedKeys() {
		return this.pressedKeys;
	}

	/**
	 * @return true if a bullet is fired.
	 */
	public boolean getFireFlag() {
		return this.fireFlag;
	}

	/**
	 * Set true if bullet is fired, false if not.
	 * 
	 * @param boolean value to be set.
	 */
	public void setFireFlag(final boolean bool) {
		this.fireFlag = bool;
	}
}
