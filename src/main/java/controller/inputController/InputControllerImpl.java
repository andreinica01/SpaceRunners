package controller.inputController;

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

  public InputControllerImpl(Scene scene, SpaceShip player) {
    this.pressedkeys = new HashMap<>();
    this.controlStates = new HashMap<>();
    this.scene = scene;
    this.player = player;
    this.fireFlag = true;
    initializeKeys();
    initializeControlStates();
    Listeners();
  }

  private void initializeControlStates() {
    this.controlStates.put(InputCommand.GO_LEFT, false);
    this.controlStates.put(InputCommand.GO_RIGHT, false);
    this.controlStates.put(InputCommand.NONE, false);
    this.controlStates.put(InputCommand.ATTACK, false);
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

  public void Listeners() {
    this.scene.setOnKeyPressed(
        e -> {
          pressedkeys.put(e.getCode(), true);
        }
      );

    this.scene.setOnKeyReleased(
        e -> {
          pressedkeys.put(e.getCode(), false);
        }
      );
  }

  private void movePlayerShip() {
    if (
      (this.pressedkeys.get(KeyCode.A)) && (!this.pressedkeys.get(KeyCode.D))
    ) {
      this.controlStates.put(InputCommand.GO_LEFT, true);
      this.controlStates.put(InputCommand.GO_RIGHT, false);
      this.controlStates.put(InputCommand.NONE, false);
    }

    if (
      (this.pressedkeys.get(KeyCode.D)) && (!this.pressedkeys.get(KeyCode.A))
    ) {
      this.controlStates.put(InputCommand.GO_RIGHT, true);
      this.controlStates.put(InputCommand.GO_LEFT, false);
      this.controlStates.put(InputCommand.NONE, false);
    }

    if (
      (!this.pressedkeys.get(KeyCode.D)) &&
      (!this.pressedkeys.get(KeyCode.A)) ||
      (this.pressedkeys.get(KeyCode.D)) &&
      (this.pressedkeys.get(KeyCode.A))
    ) {
      this.controlStates.put(InputCommand.NONE, true);
      this.controlStates.put(InputCommand.GO_RIGHT, false);
      this.controlStates.put(InputCommand.GO_LEFT, false);
    }

    if (this.pressedkeys.get(KeyCode.P) && this.player.getCanFire()) {
      if (getFireFlag()) {
        this.controlStates.put(InputCommand.ATTACK, true);
        setFireFlag(false);
      } else this.controlStates.put(InputCommand.ATTACK, false);
    } else {
      this.controlStates.put(InputCommand.ATTACK, false);
      setFireFlag(true);
    }
    //	System.out.println(fireFlag);

  }

  public Map<InputCommand, Boolean> getControlStates() {
    movePlayerShip();
    return this.controlStates;
  }

  public boolean getFireFlag() {
    return this.fireFlag;
  }

  public void setFireFlag(boolean bool) {
    this.fireFlag = bool;
  }
}
