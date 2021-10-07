package view.hud;

import controller.collisionEngine.CollisionEngine;
import model.hud.HUDBonusImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import utilities.MagicEnumInt;
import view.gameField.GameField;

public class HUDImpl implements IHUD {

     /*
     * Game Container reference and HUD elements.
     */
    private HUDPointsImpl pointsHUD;
    private HUDLifeImpl livesHUD;
    private HUDBonusImpl bonusHUD;
    private CollisionEngine collisionEngine;
    private GameField gameField;

    /**
     * Constructor.
     * @param gameField
     */
    public HUDImpl(final GameField gameField) {
        this.gameField = gameField;
    	this.createHUD();
    }

    /**
     * Sets all HUD parts for the game.
     */
    private void createHUD() {
        /*
         * Points HUD, setting position on screen due to his definition:
         * it is a label part so it needs a position on screen and it needs
         * to be added on the nodes system, same for other nodes.
         */
        this.pointsHUD = new HUDPointsImpl();
        this.gameField.getGameContainer().getChildren().add(this.pointsHUD);
        this.pointsHUD.setViewOrder(MagicEnumInt.COMMON_VIEW_ORDER.getValue());

        /*
         * Lives HUD
         */
        this.livesHUD = new HUDLifeImpl(this.gameField.getGameContainer());

        /*
         * PowerUP HUD
         */
        this.bonusHUD = new HUDBonusImpl(this.gameField);

        /*
         * Collision engine comes with HUD creation!
         */
        this.collisionEngine = new CollisionEngine(this.gameField, this.pointsHUD, this.livesHUD, this.bonusHUD);
    }

    @Override
    public final boolean checkGameStatus() {
        return this.livesHUD.getStatus();
    }

    @Override
    public final int checkPoints() {
        return this.pointsHUD.getPoints();
    }

    @Override
    public final int checkLives() {
        return this.livesHUD.getLifePoints();
    }

    @Override
    public final HUDBonusImpl getBonusImpl() {
        return this.bonusHUD;
    }

    @Override
    public final CollisionEngine getCollisionEngine() {
        return this.collisionEngine;
    }
}
