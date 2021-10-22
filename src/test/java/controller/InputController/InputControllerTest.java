package controller.InputController;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import controller.inputController.InputController;
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
class InputControllerTest  {

    private InputController inputController;

    InputControllerTest() {
        new JFXPanel(); // Start a JavaFX application, needed for testing with JavaFX.
        this.inputController = new InputControllerImpl(new Scene(new Group()));
    }
    
    
    /**
     * Testing Player Movement tasks by input simulation. Left, Right, Stay.
     */
    @Test
    void checkMovement() {
        // Go Left
        this.simulateTask(InputCommand.LEFT, true);
        assertTrue(this.inputController.isTaskActive(InputCommand.LEFT));
        this.simulateTask(InputCommand.LEFT, false);
        // Go Right
        this.simulateTask(InputCommand.RIGHT, true);
        assertTrue(this.inputController.isTaskActive(InputCommand.RIGHT));
        this.simulateTask(InputCommand.RIGHT, false);
        // Stay by left and right conflict
        this.simulateTask(InputCommand.LEFT, true);
        this.simulateTask(InputCommand.RIGHT, true);
        assertFalse(this.inputController.isTaskActive(InputCommand.LEFT));
        assertFalse(this.inputController.isTaskActive(InputCommand.RIGHT));
        this.simulateTask(InputCommand.LEFT, false);
        this.simulateTask(InputCommand.RIGHT, false);
        // Stay
        this.simulateTask(InputCommand.LEFT, false);
        this.simulateTask(InputCommand.RIGHT, false);
        assertFalse(this.inputController.isTaskActive(InputCommand.LEFT));
        assertFalse(this.inputController.isTaskActive(InputCommand.RIGHT));

    }

    /**
     * Testing Player attack tasks by input simulation. Fire and his logic.
     */
    @Test
    void checkAttack() {
        // Fire
        this.simulateTask(InputCommand.ATTACK, true);
        assertTrue(this.inputController.isTaskActive(InputCommand.ATTACK));
        this.simulateTask(InputCommand.ATTACK, false);
        assertFalse(this.inputController.isTaskActive(InputCommand.ATTACK));
        // Fire logic
        this.simulateTask(InputCommand.ATTACK, true);
        this.simulateTask(InputCommand.ATTACK, true);
        assertFalse(this.inputController.isTaskActive(InputCommand.ATTACK));
    }

    /**
     * Simulate a state of a Command.
     * 
     * @param command type of InputCommand
     * @param state   boolean state of the command (active or not)
     */
    private void simulateTask(final InputCommand command, final boolean state) {
        this.manageKeys(this.inputController.getKeyMapped(command), state);
        this.inputController.updatePlayerTasks();
    }

    /**
     * Setting up a state (pressed or not) of the key.
     * 
     * @param key   type of KeyCode
     * @param state boolean state of the command (active or not)
     */
    private void manageKeys(final KeyCode key, final boolean state) {
        this.inputController.setKeyState(key, state);
    }
    
}
