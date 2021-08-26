package view.gameField;

import controller.inputController.InputControllerImpl;
import controller.status.StatusController;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import model.Entity;
import model.bullet.Bullet;
import model.ship.SpaceShip;
import model.status.Status;
import view.SoundManager.SoundManager;

public interface GameField {
    /** @return the draw panel where the content is drawn. */
    AnchorPane getGameContainer();

    /** @return the active entities on the Gamefield. */
    Set<Entity> getActiveEntities();

    /** @return Width. */
    Number getWidth();

    /** @return Height. */
    Number getHeight();

    /** @param add the player Entity to the game. */
    void setPlayer(SpaceShip player);

    /** @return the player Entity. */
    SpaceShip getPlayer();

    /** @return a set with all the current enemy ships on the field. */
    Set<SpaceShip> getActiveEnemyShips();

    /** @return a set with all the Bonus Objects in the game. */
    Set<Status> getBonusObjects();

    /** @return all active bullets shot by the player. */
    Set<Bullet> getActiveBulletsShotbyPlayer();

    /** @return all active bullets shot towards the player. */
    Set<Bullet> getActiveBulletsShotbyEnemies();

    /** @return the scene. */
    Scene getScene();

    /**
     * Sets the input controller.
     * @param controller
     */
    void setInputController(InputControllerImpl controller);

    /** @return the sound manager class. */
    SoundManager getSoundManager();

    /**
     * Set the scene.
     * @param scene to be set.
     */
    void setScene(Scene scene);

    /** Set the background image. 
     * @param the path of the file.
     */
    void setBackgroundImage(String path);

    /** @return the background. */
    Node[] getBackground();

    /**
     * Add a bullet to the game.
     * @param bullet.
     */
    void addBullet(Bullet bullet);

    /**
     * Add an enemy ship to the game.
     * @param enemy.
     */
    void addEnemyShip(SpaceShip enemy);

    /**
     * Add a bonus to the game.
     * @param bonus
     */
    void addBonus(Status bonus);

    /** @return StatusController */
    StatusController getStatusController();

    /**
     * Set the status controller.
     * @param statusController to be set.
     */
    void setStatusController(StatusController statusController);
}