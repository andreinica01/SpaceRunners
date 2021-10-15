package controller.inputController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utilities.InputCommand;

public class InputControllerImpl {

    private Map<KeyCode, Boolean> pressedKeys;
    private Map<InputCommand, KeyCode> commandKeys;
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
        this.resetStates();
        this.listeners();
    }

    /**
     * Change Scene of this InputController.
     * 
     * @param scene
     */
    public void changeScene(final Scene scene) {
        this.scene = scene;
        this.listeners();
    }

    /**
     * Initialize the game controls.
     */
    public void resetStates() {
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

    /**
     * Add an association key-command. There can only be one association key-command
     * per command.
     * 
     * @param key
     * @param command
     */
    public void addCommandKeys(final KeyCode key, final InputCommand command) {
        // If key is mapped, switch with the current values. Else just replace.
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

        Set<KeyCode> activeKeys = this.pressedKeys.entrySet().stream().filter(entry -> entry.getValue() == true)
                .map(entry -> entry.getKey()).collect(Collectors.toSet());
        Set<InputCommand> activeCommand = this.commandKeys.entrySet().stream()
                .filter(entry -> activeKeys.contains(entry.getValue())).map(entry -> entry.getKey())
                .collect(Collectors.toSet());

        this.task.keySet().stream().filter(key -> activeCommand.contains(key)).forEach(key -> this.task.put(key, true));
        this.task.keySet().stream().filter(key -> !activeCommand.contains(key))
                .forEach(key -> this.task.put(key, false));
    }

    /**
     * Get a map that groups Keys based on the InputCommand.
     * 
     * @param <K>
     * @param <V>
     * 
     * @return
     */
    private <K, V> K getKeyFromValue(Map<K, V> map, V value) {
        return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).map(e -> e.getKey()).findFirst()
                .get();
    }

    /**
     * Managing tasks. Checking if the state of some of them should be changed or
     * not.
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
     * Get a Map mapping Keys to Command.
     * 
     * @return
     */
    public Map<InputCommand, KeyCode> getCommandKeys() {
        return this.commandKeys;
    }
}
