package view.gameField;

import controller.gameEventController.SoundManager;
import controller.gameLoop.GameManager;
import controller.status.StatusController;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Entity;
import model.bullet.Bullet;
import model.ship.BossShip;
import model.ship.SpaceShip;
import model.status.Status;

public interface GameField {

    /**
     * @return the draw panel where the content is drawn.
     */
    AnchorPane getGameContainer();

    /**
     * @return the active entities on the Gamefield.
     */
    Set<Entity> getActiveEntities();

    /**
     * @return Width.
     */
    Number getWidth();

    /**
     * @return Height.
     */
    Number getHeight();

    /**
     * @param add the player Entity to the game.
     */
    void setPlayer(SpaceShip player);

    /**
     * @return the player Entity.
     */
    SpaceShip getPlayer();

    /**
     * @return a set with all the current enemy ships on the field.
     */
    Set<SpaceShip> getActiveEnemyShips();

    /**
     * @return a set with all the Bonus Objects in the game.
     */
    Set<Status> getBonusObjects();

    /**
     * @return all active bullets shot by the player.
     */
    Set<Bullet> getActiveBulletsShotbyPlayer();

    /**
     * @return all active bullets shot towards the player.
     */
    Set<Bullet> getActiveBulletsShotbyEnemies();

    /**
     * @return the scene.
     */
    Scene getScene();

    /**
     * Remove entities from the game.
     * 
     * @param entity to be removed.
     */
    void removeEntity(Entity entity);

    /**
     * @return the sound manager class.
     */
    SoundManager getSoundManager();

    /**
     * Set the scene.
     * 
     * @param scene to be set.
     */
    void setScene(Scene scene);

    /**
     * Set the background image.
     * 
     * @param the path of the file.
     */
    void setBackgroundImage(String path);

    /**
     * @return the background.
     */
    Node[] getBackground();

    /**
     * Add an enemy ship to the game.
     * 
     * @param enemy.
     */
    void addEnemyShip(SpaceShip enemy);

    /**
     * Add a bonus to the game.
     * 
     * @param bonus
     */
    void addBonus(Status bonus);

    /**
     * @return StatusController
     */
    StatusController getStatusController();

    /**
     * Set the status controller.
     * 
     * @param statusController to be set.
     */
    void setStatusController(StatusController statusController);

    /**
     * @return all active bosses.
     */
    Set<SpaceShip> getActiveBosses();

    /**
     * Adds enemy bullets.
     * 
     * @param bullet.
     */
    void addEnemyBullet(Bullet bullet);

    /**
     * Adds a boss to the game.
     * 
     * @param boss.
     */
    void addBoss(BossShip boss);

    /**
     * Add player bullet.
     * 
     * @param bullet.
     */
    void addPlayerBullet(Bullet bullet);

    /**
     * Helper method for end game: set a stage reference.
     * 
     * @param stage
     */
    void setStageReference(Stage stage);

    /**
     * @return Stage reference.
     */
    Stage getStage();

    /**
     * Helper method for end game: set a GameManager reference.
     * 
     * @param manager
     */
    void setGameManager(GameManager manager);

    /**
     * @return GameManager reference.
     */
    GameManager getGameManager();

    /**
     * @return true if a new boss must spawn.
     */
    boolean isBossToBeSpawned();

    /**
     * Sets false if boss is no more active.
     * 
     * @param value.
     */
    void setBossToBeSpawned(boolean value);
}
