package controller.inputController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
		this.initializeDefaultKeys();
		this.initializeControlStates();
		this.listeners();
	}

	/**
	 * Change Scene of this InputController.
	 * @param scene
	 */
	public void changeScene(final Scene scene) {
		this.scene = scene;
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
	 * Initialize default keys that player use in the game.
	 */
	private void initializeDefaultKeys() {
		this.commandKeys.put(KeyCode.A, InputCommand.LEFT);
		this.commandKeys.put(KeyCode.D, InputCommand.RIGHT);
		this.commandKeys.put(KeyCode.P, InputCommand.ATTACK);
	}
	
	/**
	 * Add an association key-command.
	 * There can only be one association key-command per command.
	 * @param key
	 * @param command
	 */
	public void addCommandKeys(KeyCode key, InputCommand command) {
		if (!this.commandKeys.containsKey(key)) {
			this.getMapGrouped().get(command).forEach(e -> this.commandKeys.remove(e));
			this.commandKeys.put(key, command);
		}
	}

	/**
	 * Listeners used to check if a key is pressed or released.
	 */
	private void listeners() {
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

	/**
	 * Get a map that groups Keys based on the InputCommand.
	 * @return
	 */
	public Map<InputCommand, List<KeyCode>> getMapGrouped() {
		return this.commandKeys.keySet().stream().collect(Collectors.groupingBy(e -> this.commandKeys.get(e)));
	}

	/**
	 * Managing tasks.
	 * Checking if the state of some of them should be changed or not.
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
			if (this.fireFlag) {
				this.fireFlag = false;
			} else {
				this.task.put(InputCommand.ATTACK, false);
			}
		} else {
			this.fireFlag = true;
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
	 * 
	 * @return Map<KeyCode, Boolean>
	 */
	public Map<KeyCode, Boolean> getPressedKeys() {
		return this.pressedKeys;
	}
	
	/**
	 * Get the Key associated with the specified command.
	 * @param command
	 * @return
	 */
	public List<KeyCode> getKeysListByCommand(InputCommand command) {
		var grouped = this.getMapGrouped();
		Optional<List<KeyCode>> list = Optional.ofNullable(grouped.get(command));
		if (list.isPresent()) {
			return list.get();
		}
		return new ArrayList<>();
	}

	/**
	 * Get a Map mapping Keys to Command.
	 * @return
	 */
	public Map<KeyCode, InputCommand> getCommandKeys() {
		return this.commandKeys;
	}
}
