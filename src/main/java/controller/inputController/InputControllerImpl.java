package controller.inputController;

import Utilities.HUDParameters;
import Utilities.InputCommand;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.ship.SpaceShip;

public class InputControllerImpl {

    private Map<KeyCode, Boolean> pressedkeys;
    private Map<InputCommand, Boolean> controlStates;

    private Scene scene;
    private SpaceShip player;

    private boolean fireFlag; // Used for firing 1 bullet at time

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
        this.Listeners();
    }

    /**
     * Initialize the game controls.
     */
    private void initializeControlStates() {
        this.controlStates.put(InputCommand.GO_LEFT, HUDParameters.FALSE);
        this.controlStates.put(InputCommand.GO_RIGHT, HUDParameters.FALSE);
        this.controlStates.put(InputCommand.NONE, HUDParameters.FALSE);
        this.controlStates.put(InputCommand.ATTACK, HUDParameters.FALSE);
    }

    /**
     * Initialize keys that you can press in the game.
     */
    private void initializeKeys() {
        this.pressedkeys.put(KeyCode.A, HUDParameters.FALSE);
        this.pressedkeys.put(KeyCode.LEFT, HUDParameters.FALSE);

        this.pressedkeys.put(KeyCode.D, HUDParameters.FALSE);
        this.pressedkeys.put(KeyCode.RIGHT, HUDParameters.FALSE);

        this.pressedkeys.put(KeyCode.W, HUDParameters.FALSE);
        this.pressedkeys.put(KeyCode.UP, HUDParameters.FALSE);

        this.pressedkeys.put(KeyCode.DOWN, HUDParameters.FALSE);
        this.pressedkeys.put(KeyCode.S, HUDParameters.FALSE);

        this.pressedkeys.put(KeyCode.P, HUDParameters.FALSE);
    }

    /**
     * Listeners used for performing an action after a key is pressed.
     */
    public void Listeners() {
        this.scene.setOnKeyPressed(e -> {
            pressedkeys.put(e.getCode(), HUDParameters.TRUE);
        });

        this.scene.setOnKeyReleased(e -> {
            pressedkeys.put(e.getCode(), HUDParameters.FALSE);
        });
    }

    /**
     * Moves the player based on the key pressed.
     */
    private void movePlayerShip() {
        if ((this.pressedkeys.get(KeyCode.A)) && (!this.pressedkeys.get(KeyCode.D))) {
            this.controlStates.put(InputCommand.GO_LEFT, HUDParameters.TRUE);
            this.controlStates.put(InputCommand.GO_RIGHT, HUDParameters.FALSE);
            this.controlStates.put(InputCommand.NONE, HUDParameters.FALSE);
        }

        if ((this.pressedkeys.get(KeyCode.D)) && (!this.pressedkeys.get(KeyCode.A))) {
            this.controlStates.put(InputCommand.GO_RIGHT, HUDParameters.TRUE);
            this.controlStates.put(InputCommand.GO_LEFT, HUDParameters.FALSE);
            this.controlStates.put(InputCommand.NONE, HUDParameters.FALSE);
        }

        if ((!this.pressedkeys.get(KeyCode.D)) && (!this.pressedkeys.get(KeyCode.A))
                || (this.pressedkeys.get(KeyCode.D)) && (this.pressedkeys.get(KeyCode.A))) {
            this.controlStates.put(InputCommand.NONE, HUDParameters.TRUE);
            this.controlStates.put(InputCommand.GO_RIGHT, HUDParameters.FALSE);
            this.controlStates.put(InputCommand.GO_LEFT, HUDParameters.FALSE);
        }

        if (this.pressedkeys.get(KeyCode.P) && this.player.getCanFire()) {
            if (this.getFireFlag()) {
                this.controlStates.put(InputCommand.ATTACK, HUDParameters.TRUE);
                this.setFireFlag(HUDParameters.FALSE);
            } else
                this.controlStates.put(InputCommand.ATTACK, HUDParameters.FALSE);
        } else {
            this.controlStates.put(InputCommand.ATTACK, HUDParameters.FALSE);
            this.setFireFlag(HUDParameters.TRUE);
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