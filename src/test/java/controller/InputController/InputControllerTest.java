package controller.InputController;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import controller.inputController.InputControllerImpl;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import utilities.InputCommand;

/**
 * Class Test to verify the correct behavior of player's tasks, based on user's
 * input.
 */
class InputControllerTest {

    private InputControllerImpl inputControllerImpl;
    private Map<InputCommand, Boolean> tasks;

    /**
     * Constructor.
     */
    public InputControllerTest() {
        new JFXPanel(); // Start a JavaFX application, needed for test with JavaFX
        Scene scene = new Scene(new Group());
        this.inputControllerImpl = new InputControllerImpl(scene);
        this.refreshTask();
    }

    /**
     * Testing Player Movement tasks by input simulation. Left, Right, Stay.
     */
    @Test
    void checkMovement() {
        // Go Left
        this.simulateTask(InputCommand.LEFT, true);
        assertTrue(this.tasks.get(InputCommand.LEFT));
        this.simulateTask(InputCommand.LEFT, false);
        // Go Right
        this.simulateTask(InputCommand.RIGHT, true);
        assertTrue(this.tasks.get(InputCommand.RIGHT));
        this.simulateTask(InputCommand.RIGHT, false);
        // Stay by left and right conflict
        this.simulateTask(InputCommand.LEFT, true);
        this.simulateTask(InputCommand.RIGHT, true);
        assertFalse(this.tasks.get(InputCommand.LEFT));
        assertFalse(this.tasks.get(InputCommand.RIGHT));
        this.simulateTask(InputCommand.LEFT, false);
        this.simulateTask(InputCommand.RIGHT, false);
        // Stay
        this.simulateTask(InputCommand.LEFT, false);
        this.simulateTask(InputCommand.RIGHT, false);
        assertFalse(this.tasks.get(InputCommand.LEFT));
        assertFalse(this.tasks.get(InputCommand.RIGHT));

    }

    /**
     * Testing Player attack tasks by input simulation. Fire and his logic.
     */
    @Test
    void checkAttack() {
        // Fire
        this.simulateTask(InputCommand.ATTACK, true);
        assertTrue(this.tasks.get(InputCommand.ATTACK));
        this.simulateTask(InputCommand.ATTACK, false);
        assertFalse(this.tasks.get(InputCommand.ATTACK));
        // Fire logic
        this.simulateTask(InputCommand.ATTACK, true);
        this.simulateTask(InputCommand.ATTACK, true);
        assertFalse(this.tasks.get(InputCommand.ATTACK));
    }

    /**
     * Simulate a state of a Command.
     * 
     * @param command type of InputCommand
     * @param state   boolean state of the command (active or not)
     */
    private void simulateTask(final InputCommand command, final boolean state) {
        this.manageKeys(this.getKeyByCommand(command), state);
        this.refreshTask();
    }

    /**
     * Update task status based on the pressed keys.
     */
    private void refreshTask() {
        this.tasks = this.inputControllerImpl.getControlStates();
    }

    /**
     * Get the key mapped to a specific command.
     * 
     * @param command type of InputCommand
     * @return key mapped to the command
     */
    private KeyCode getKeyByCommand(final InputCommand command) {
        return this.inputControllerImpl.getCommandKeys().get(command);
    }

    /**
     * Setting up a state (pressed or not) of the key.
     * 
     * @param key   type of KeyCode
     * @param state boolean state of the command (active or not)
     */
    private void manageKeys(final KeyCode key, final boolean state) {
        var pressedKeys = this.inputControllerImpl.getPressedKeys();
        pressedKeys.put(key, state);
    }
}
