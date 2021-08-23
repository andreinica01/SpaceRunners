package controller.enemyAI;

import java.util.Random;

import model.ship.EnemyShip;
import model.ship.SpaceShip;
import model.status.Status;
import model.status.StatusEnum;
import model.status.StatusFactory;
import view.gameField.GameField;

/**
 * Class to manage the movement of the enemy ships
 */
public class enemyAI {

	private GameField gamefield;
	private StatusFactory statusFactory;

	private int enemySpeed = 10;

	long enemyInterval;
	long enemyResetTime;

	long statusInterval;
	long statusResetTime;

	private final Random rnd;

	public enemyAI(GameField gamefield) {
		this.gamefield = gamefield;
		statusFactory = new StatusFactory();
		enemyResetTime = System.currentTimeMillis();
		statusResetTime = System.currentTimeMillis();
		enemyInterval = (long) ((getRandomDouble(0.0, 5.0) * 1000));
		statusInterval = (long) ((getRandomDouble(5.0, 15.0) * 1000));
		rnd = new Random();
	}

	public void update() {
		generateEnemy();
		generateStatus();
	}

	private void generateEnemy() {
		if (System.currentTimeMillis() - enemyResetTime > enemyInterval) {
			SpaceShip enemyship = new EnemyShip();
			enemyship.setSpeed(this.enemySpeed);
			enemyship.setPosition(-rnd.nextInt(400), -300);

			this.gamefield.addEnemyShip(enemyship);

			enemyInterval = (long) ((getRandomDouble(0.0, 3.5) * 1000));
			enemyResetTime = System.currentTimeMillis();
		}
	}

	private void generateStatus() {
		if (System.currentTimeMillis() - statusResetTime > statusInterval) {
			Status status = statusFactory.createStatus(StatusEnum.getRandom());
			status.setPosition(rnd.nextInt(400), -300);

			this.gamefield.addBonus(status);

			statusInterval = (long) ((getRandomDouble(4.0, 15.0) * 1000));
			statusResetTime = System.currentTimeMillis();

		}

	}

	/**
	 * Get a random Double between two values.
	 * 
	 * @param min
	 * @param max
	 * @return A random Double
	 */
	private Double getRandomDouble(Double min, Double max) {
		return min + new Random().nextDouble() * (max - min);
	}

}
