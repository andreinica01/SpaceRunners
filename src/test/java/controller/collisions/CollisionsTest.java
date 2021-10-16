package controller.collisions;

import controller.collisionEngine.CollisionEngine;
import javafx.scene.layout.AnchorPane;
import model.hud.HUDBonusImpl;
import model.hud.HUDLifeImpl;
import model.hud.HUDPointsImpl;
import model.hud.IHUDBonus;
import utilities.MagicEnumInt;
import view.gameField.GameField;
import view.gameField.GameFieldImpl;

public class CollisionsTest {

    private CollisionEngine engine;
    private GameField gameField;
    private HUDPointsImpl pointsHUD;
    private HUDLifeImpl livesHUD;
    private IHUDBonus bonusHUD;
    private AnchorPane gamePane;

    /**
     * Constructor.
     */
    public CollisionsTest() {
        this.gamePane = new AnchorPane();
        this.gameField = new GameFieldImpl(MagicEnumInt.WIDTH.getValue(), 
                                            MagicEnumInt.HEIGHT.getValue());
        this.pointsHUD = new HUDPointsImpl();
        this.livesHUD = new HUDLifeImpl(this.gamePane);
        this.bonusHUD = new HUDBonusImpl(this.gameField);
        this.engine = new CollisionEngine(this.gameField, this.pointsHUD, 
                                            this.livesHUD, this.bonusHUD);
    }
}
