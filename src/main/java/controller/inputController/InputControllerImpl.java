package controller.inputController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utilities.InputCommand;

/**
 * This Control Class detect all the input (keys) triggered by the user. In
 * addition to that, this class also manage player tasks based on the same
 * input.
 *
 */
public class InputControllerImpl implements InputController {

    private Map<KeyCode, Boolean> pressedKeys;
    private Map<InputCommand, KeyCode> commandKeys;
    private Map<InputCommand, Boolean> task;
    private Scene scene;

    // Flag used to implement fire logic.
    private boolean fireFlag;

    /**
     * Constructor.
     * 
     * @param scene where this InputController detect keys.
     */
    public InputControllerImpl(final Scene scene) {
        this.pressedKeys = new HashMap<>();
        this.commandKeys = new HashMap<>();
        this.task = new HashMap<>();
        this.scene = scene;
        this.fireFlag = true;
        this.initializeDefaultKeys();
        this.resetStates();
        this.initializeListeners();
    }

    @Override
    public final void changeScene(final Scene scene) {
        this.scene = scene;
        this.initializeListeners();
    }

    @Override
    public final void resetStates() {
        List.of(InputCommand.values()).forEach(e -> this.task.put(e, false));
        List.of(KeyCode.values()).forEach(e -> this.pressedKeys.put(e, false));
    }

    /**
     * Initialize default keys that player use in the game.
     */
    private void initializeDefaultKeys() {
        this.commandKeys.put(InputCommand.LEFT, KeyCode.A);
        this.commandKeys.put(InputCommand.RIGHT, KeyCode.D);
        this.commandKeys.put(InputCommand.ATTACK, KeyCode.P);
    }

    @Override
    public final void addCommandKeys(final KeyCode key, final InputCommand command) {
        // If key is already mapped, switch with the current values. Else just set it.
        if (this.commandKeys.values().stream().anyMatch(e -> e.equals(key))) {
            InputCommand otherCommand = this.getKeyFromValue(this.commandKeys, key);
            KeyCode otherKey = this.commandKeys.get(command);
            this.commandKeys.replace(otherCommand, key, otherKey);
            this.commandKeys.replace(command, otherKey, key);
        } else {
            this.commandKeys.replace(command, key);
        }
    }

    /**
     * Initialize listeners used to check if a key is pressed or released.
     */
    private void initializeListeners() {
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
    private void updateKeysInfo() {
        // Get activeCommand based on pressed keys.
        Set<KeyCode> activeKeys = this.pressedKeys.entrySet().stream().filter(entry -> entry.getValue())
                .map(entry -> entry.getKey()).collect(Collectors.toSet());
        Set<InputCommand> activeCommand = this.commandKeys.entrySet().stream()
                .filter(entry -> activeKeys.contains(entry.getValue())).map(entry -> entry.getKey())
                .collect(Collectors.toSet());
        // Setting state of task.
        this.task.keySet().stream().filter(key -> activeCommand.contains(key)).forEach(key -> this.task.put(key, true));
        this.task.keySet().stream().filter(key -> !activeCommand.contains(key))
                .forEach(key -> this.task.put(key, false));
    }

    /**
     * Get a Key from a map based on his mapped value.
     * 
     * @param map
     * @param value
     * 
     * @return
     */
    private <K, V> K getKeyFromValue(final Map<K, V> map, final V value) {
        return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(e -> e.getKey()).findFirst()
                .get();
    }

    @Override
    public final void updatePlayerTasks() {
        this.updateKeysInfo();
        this.fireLogic();
        this.movementLogic();

    }

    private void movementLogic() {
        if (this.task.get(InputCommand.LEFT) && this.task.get(InputCommand.RIGHT)) {
            this.task.put(InputCommand.LEFT, false);
            this.task.put(InputCommand.RIGHT, false);
        }
    }

    /**
     * Managing fire logic. Making player firing only 1 bullet at time.
     */
    private void fireLogic() {
        if (this.isTaskActive(InputCommand.ATTACK)) {
            if (this.fireFlag) {
                this.fireFlag = false;
            } else {
                this.task.put(InputCommand.ATTACK, false);
            }
        } else {
            this.fireFlag = true;
        }
    }

    @Override
    public final boolean isKeyPressed(final KeyCode key) {
        return this.pressedKeys.get(key);
    }

    @Override
    public final KeyCode getKeyMapped(final InputCommand command) {
        return this.commandKeys.get(command);
    }

    @Override
    public final boolean isTaskActive(final InputCommand task) {
        return this.task.get(task);
    }

    @Override
    public final void setKeyState(final KeyCode key, final boolean state) {
        this.pressedKeys.put(key, state);
    }

}
