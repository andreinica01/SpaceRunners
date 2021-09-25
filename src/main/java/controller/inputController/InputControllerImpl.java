package controller.inputController;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.ship.SpaceShip;
import utilities.InputCommand;
import utilities.VariousMagicNumbers;

public class InputControllerImpl {

    private Map<KeyCode, Boolean> pressedkeys;
    private Map<InputCommand, Boolean> controlStates;

    private Scene scene;
    private SpaceShip player;

    // You can fire 1 bullet at time
    private boolean fireFlag; 

    /**
     * Constructor.
     * @param scene
     * @param player
     */
    public InputControllerImpl(final Scene scene, final SpaceShip player) {
        this.pressedkeys = new HashMap<>();
        this.controlStates = new HashMap<>();
        this.scene = scene;
        this.player = player;
        this.fireFlag = true;

        this.initializeKeys();
        this.initializeControlStates();
        this.listeners();
    }

    /**
     * Initialize the game controls.
     */
    private void initializeControlStates() {
        this.controlStates.put(InputCommand.GO_LEFT, VariousMagicNumbers.FALSE);
        this.controlStates.put(InputCommand.GO_RIGHT, VariousMagicNumbers.FALSE);
        this.controlStates.put(InputCommand.NONE, VariousMagicNumbers.FALSE);
        this.controlStates.put(InputCommand.ATTACK, VariousMagicNumbers.FALSE);
    }

    /**
     * Initialize keys that you can press in the game.
     */
    private void initializeKeys() {
        this.pressedkeys.put(KeyCode.A, VariousMagicNumbers.FALSE);

        this.pressedkeys.put(KeyCode.D, VariousMagicNumbers.FALSE);

        this.pressedkeys.put(KeyCode.P, VariousMagicNumbers.FALSE);
    }

    /**
     * Listeners used for performing an action after a key is pressed.
     */
    public void listeners() {
        this.scene.setOnKeyPressed(e -> {
            pressedkeys.put(e.getCode(), VariousMagicNumbers.TRUE);
        });

        this.scene.setOnKeyReleased(e -> {
            pressedkeys.put(e.getCode(), VariousMagicNumbers.FALSE);
        });
    }

    /**
     * Moves the player based on the key pressed.
     */
    private void movePlayerShip() {
        if ((this.pressedkeys.get(KeyCode.A)) && (!this.pressedkeys.get(KeyCode.D))) {
            this.controlStates.put(InputCommand.GO_LEFT, VariousMagicNumbers.TRUE);
            this.controlStates.put(InputCommand.GO_RIGHT, VariousMagicNumbers.FALSE);
            this.controlStates.put(InputCommand.NONE, VariousMagicNumbers.FALSE);
        }

        if ((this.pressedkeys.get(KeyCode.D)) && (!this.pressedkeys.get(KeyCode.A))) {
            this.controlStates.put(InputCommand.GO_RIGHT, VariousMagicNumbers.TRUE);
            this.controlStates.put(InputCommand.GO_LEFT, VariousMagicNumbers.FALSE);
            this.controlStates.put(InputCommand.NONE, VariousMagicNumbers.FALSE);
        }

        if ((!this.pressedkeys.get(KeyCode.D)) && (!this.pressedkeys.get(KeyCode.A))
                || (this.pressedkeys.get(KeyCode.D)) && (this.pressedkeys.get(KeyCode.A))) {
            this.controlStates.put(InputCommand.NONE, VariousMagicNumbers.TRUE);
            this.controlStates.put(InputCommand.GO_RIGHT, VariousMagicNumbers.FALSE);
            this.controlStates.put(InputCommand.GO_LEFT, VariousMagicNumbers.FALSE);
        }

        if (this.pressedkeys.get(KeyCode.P) && this.player.getCanFire()) {
            if (this.getFireFlag()) {
                this.controlStates.put(InputCommand.ATTACK, VariousMagicNumbers.TRUE);
                this.setFireFlag(VariousMagicNumbers.FALSE);
            } else {
                this.controlStates.put(InputCommand.ATTACK, VariousMagicNumbers.FALSE);
            }
        } else {
            this.controlStates.put(InputCommand.ATTACK, VariousMagicNumbers.FALSE);
            this.setFireFlag(VariousMagicNumbers.TRUE);
        }
    }

    /**
     * @return a map containing the command and if it is active or not.
     */
    public Map<InputCommand, Boolean> getControlStates() {
        this.movePlayerShip();
        return this.controlStates;
    }

    /**
     * @return true if a bullet is fired.
     */
    public boolean getFireFlag() {
        return this.fireFlag;
    }

    /**
     * Set true if bullet is fired, false if not.
     * @param boolean value to be set.
     */
    public void setFireFlag(final boolean bool) {
        this.fireFlag = bool;
    }
}
