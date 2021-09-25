package controller.gameEventController;

import controller.collisionEngine.IHelper;
import model.hud.HUDBonusImpl;
import view.gameField.GameField;
import view.hud.HUDImpl;

public class GameEventController implements IGameEventController {

    /*
     * Game Container reference and HUD elements.
     */
    private HUDImpl hudBuilder;

    /**
     * Constructor.
     * @param game field.
     */
    public GameEventController(final GameField gameField) {
        this.hudBuilder = new HUDImpl(gameField);
    }

    @Override
    public final void endGame() {

    }

    @Override
    public final boolean checkGameStatus() {
        return this.hudBuilder.checkGameStatus();
    }

    @Override
    public final int checkPoints() {
        return this.hudBuilder.checkPoints();
    }

    @Override
    public final int checkLives() {
        return this.hudBuilder.checkLives();
    }

    @Override
    public final HUDBonusImpl getBonusImpl() {
        return this.hudBuilder.getBonusImpl();
    }

    @Override
    public final IHelper getCollisionEngine() {
        return this.hudBuilder.getCollisionEngine();
    }
}
