package view.gameField;

import controller.gameLoop.GameManager;
import controller.status.StatusController;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Entity;
import model.bullet.Bullet;
import model.ship.BossShip;
import model.ship.SpaceShip;
import model.status.Status;
import utilities.Parameters;
import utilities.VariousMagicNumbers;
import view.SoundManager.SoundManager;

public class GameFieldImpl implements GameField {

    private Set<Entity> entities;

    private Set<Bullet> playerBullets;
    private Set<Bullet> enemyBullets;

    private Set<SpaceShip> enemyships;
    private Set<Status> status;
    private Set<SpaceShip> bossShips;

    private SpaceShip player;

    private ImageView[] backGroundImage;

    private Scene scene;

    private final int width;
    private final int height;

    private StatusController statusController;
    private SoundManager soundmanager;
    private AnchorPane gameContainer;

    private Stage stage;
    private GameManager gameManager;

    /**
     * Constructor.
     * 
     * @param width
     * @param height
     */
    public GameFieldImpl(final int width, final int height) {
        this.gameContainer = new AnchorPane();

        this.gameContainer.prefWidth(width);
        this.gameContainer.prefHeight(height);

        this.entities = new HashSet<Entity>();

        this.playerBullets = new HashSet<Bullet>();
        this.enemyBullets = new HashSet<Bullet>();

        this.enemyships = new HashSet<SpaceShip>();
        this.bossShips = new HashSet<>();

        this.status = new HashSet<Status>();

        // to load in specific class container
        this.backGroundImage = new ImageView[VariousMagicNumbers.TWO];

        this.width = width;
        this.height = height;

        this.soundmanager = new SoundManager();
    }

    @Override
    public final AnchorPane getGameContainer() {
        return this.gameContainer;
    }

    @Override
    public final Set<Entity> getActiveEntities() {
        return this.entities;
    }

    @Override
    public final Number getWidth() {
        return this.width;
    }

    @Override
    public final Number getHeight() {
        return this.height;
    }

    @Override
    public final void setPlayer(final SpaceShip player) {
        this.player = player;
        this.gameContainer.getChildren().add(this.player.getNode());
        this.entities.add(player);
    }

    @Override
    public final SpaceShip getPlayer() {
        return this.player;
    }

    @Override
    public final Set<SpaceShip> getActiveEnemyShips() {
        return this.enemyships;
    }

    @Override
    public final Set<Status> getBonusObjects() {
        return this.status;
    }

    @Override
    public final Set<Bullet> getActiveBulletsShotbyPlayer() {
        return this.playerBullets;
    }

    @Override
    public final Set<Bullet> getActiveBulletsShotbyEnemies() {
        return this.enemyBullets;
    }

    @Override
    public final void setScene(final Scene scene) {
        this.scene = scene;
    }

    @Override
    public final void setBackgroundImage(final String path) {
        for (int i = 0; i < this.backGroundImage.length; i++) {
            this.backGroundImage[i] = new ImageView(Parameters.backgroundImage);
            this.backGroundImage[i].setLayoutX(VariousMagicNumbers.ZERO);
            this.backGroundImage[i].setFitWidth(this.width);
            this.backGroundImage[i].setFitHeight(this.height);
            this.backGroundImage[i].setViewOrder(VariousMagicNumbers.TEN);
            this.gameContainer.getChildren().add(this.backGroundImage[i]);
        }

        this.backGroundImage[VariousMagicNumbers.ZERO].setTranslateX(VariousMagicNumbers.ZERO);
        this.backGroundImage[VariousMagicNumbers.ONE].setLayoutY(-this.height);
    }

    @Override
    public final Node[] getBackground() {
        return this.backGroundImage;
    }

    @Override
    public final void addPlayerBullet(final Bullet bullet) {
        this.entities.add(bullet);
        this.playerBullets.add(bullet);
        this.gameContainer.getChildren().add(bullet.getNode());
    }

    @Override
    public final void addEnemyShip(final SpaceShip enemy) {
        this.enemyships.add(enemy);
        this.entities.add(enemy);
        this.gameContainer.getChildren().add(enemy.getNode());
    }

    @Override
    public final void addBoss(final BossShip boss) {
        this.entities.add(boss);
        this.bossShips.add(boss);
        this.gameContainer.getChildren().add(boss.getNode());
    }

    @Override
    public final void addBonus(final Status bonus) {
        this.entities.add(bonus);
        this.status.add(bonus);
        this.gameContainer.getChildren().add(bonus.getNode());
    }

    @Override
    public final Scene getScene() {
        return this.scene;
    }

    @Override
    public final SoundManager getSoundManager() {
        return this.soundmanager;
    }

    /**
     * @return statusController reference.
     */
    public final StatusController getStatusController() {
        return statusController;
    }

    /**
     * Save status controller reference.
     * @param statusController
     */
    public final void setStatusController(final StatusController statusController) {
        this.statusController = statusController;
    }

    @Override
    public final void addEnemyBullet(final Bullet bullet) {
        this.entities.add(bullet);
        this.enemyBullets.add(bullet);
        this.gameContainer.getChildren().add(bullet.getNode());

    }

    @Override
    public final void removeEntity(final Entity entity) {

        if (!this.enemyBullets.remove(entity)) {
            if (!this.playerBullets.remove(entity)) {
                if (!this.status.remove(entity)) {
                    if (!this.enemyships.remove(entity)) {
                        this.bossShips.remove(entity);
                    }
                }
            }
        }

        this.entities.remove(entity);
        this.gameContainer.getChildren().remove(entity.getNode());
    }

    @Override
    public final Set<SpaceShip> getActiveBosses() {
        return this.bossShips;
    }

    /*
     * End game Helper methods.
     */
    @Override
    public final void setStageReference(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public final Stage getStage() {
        return this.stage;
    }

    @Override
    public final void setGameManager(final GameManager manager) {
        this.gameManager = manager;
    }

    @Override
    public final GameManager getGameManager() {
        return this.gameManager;
    }
}
