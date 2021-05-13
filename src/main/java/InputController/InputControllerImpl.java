package InputController;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.util.ElementScanner6;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class InputControllerImpl implements EventHandler<KeyEvent>, InputController {

    private Map<KeyCode, Boolean> pressedkeys;

    public InputControllerImpl() {
        this.pressedkeys = new HashMap<KeyCode, Boolean>();
        initializeKeys();

    }

    private void initializeKeys() {
        this.pressedkeys.put(KeyCode.A, false);
        this.pressedkeys.put(KeyCode.LEFT, false);
        this.pressedkeys.put(KeyCode.D, false);
        this.pressedkeys.put(KeyCode.RIGHT, false);
    }

    @Override
    public void handle(KeyEvent event) {

        System.out.println("pressed");

        if (event.getEventType() == KeyEvent.KEY_PRESSED)
            this.pressedkeys.put(event.getCode(), true);
        else if (event.getEventType() == KeyEvent.KEY_RELEASED)
            this.pressedkeys.put(event.getCode(), false);

       // event.consume();

    }

    @Override
    public boolean isLeft() {

        if (this.pressedkeys.get(KeyCode.A)==true || this.pressedkeys.get(KeyCode.LEFT)
                && (!this.pressedkeys.get(KeyCode.D) && !this.pressedkeys.get(KeyCode.RIGHT))) 
                {
            return true;
           
        }
     

        return false;
    }

    @Override
    public boolean isRight() {

        return false;
    }

    @Override
    public boolean isTopLeft() {

        return false;
    }

    @Override
    public boolean isTopRight() {

        return false;
    }

}
