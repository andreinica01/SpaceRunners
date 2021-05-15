package controller.inputController;

import java.util.HashMap;
import java.util.Map;

import Utilities.InputCommand;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class InputControllerImpl implements EventHandler<KeyEvent>, InputController {

    private Map<KeyCode, Boolean> pressedkeys;
    private boolean attack = true;

    public InputControllerImpl() {
        this.pressedkeys = new HashMap<KeyCode, Boolean>();
        initializeKeys();

    }

    private void initializeKeys() {
        this.pressedkeys.put(KeyCode.A, false);
        this.pressedkeys.put(KeyCode.LEFT, false);
        this.pressedkeys.put(KeyCode.D, false);
        this.pressedkeys.put(KeyCode.RIGHT, false);

        this.pressedkeys.put(KeyCode.W, false);
        this.pressedkeys.put(KeyCode.UP, false);

        this.pressedkeys.put(KeyCode.DOWN, false);
        this.pressedkeys.put(KeyCode.S, false);

        this.pressedkeys.put(KeyCode.P, false);
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            this.pressedkeys.put(event.getCode(), true);

        }

        else if (event.getEventType() == KeyEvent.KEY_RELEASED) {

            if (event.getCode() == KeyCode.P) {
                this.attack = true;
            }

            this.pressedkeys.put(event.getCode(), false);
        }
        // event.consume();

    }

    private boolean isDown() {

        if (this.pressedkeys.get(KeyCode.S) || this.pressedkeys.get(KeyCode.DOWN))
            return true;

        return false;

    }

    private boolean isUP() {

        if (this.pressedkeys.get(KeyCode.W) || this.pressedkeys.get(KeyCode.UP))
            return true;

        return false;

    }

    private boolean isRight() {
        if ((this.pressedkeys.get(KeyCode.D) || this.pressedkeys.get(KeyCode.RIGHT) && !this.pressedkeys.get(KeyCode.A)
                && !this.pressedkeys.get(KeyCode.LEFT)))
            return true;

        return false;

    }

    private boolean isLeft() {
        if ((this.pressedkeys.get(KeyCode.A) || this.pressedkeys.get(KeyCode.LEFT) && !this.pressedkeys.get(KeyCode.D)
                && !this.pressedkeys.get(KeyCode.RIGHT)))
            return true;

        return false;

    }

    private boolean isAttack() {

        if (this.pressedkeys.get(KeyCode.P) && this.attack != false) {
            this.attack = false;
            return true;
        }

        return false;

    }

    @Override
    public InputCommand getCommand() {

        if (isAttack())
        return InputCommand.ATTACK;

        
        if (isDown())
            return InputCommand.GO_DOWN;

        if (isUP())
            return InputCommand.GO_UP;

        if (isLeft())
            return InputCommand.GO_LEFT;

        if (isRight())
            return InputCommand.GO_RIGHT;

     

        return InputCommand.NONE;

    }

}
