package model.status.bonus;

import controller.collisionEngine.AbstractHelper;
import model.status.Status;
import model.status.StatusEnum;
import utilities.MagicEnumDouble;
import utilities.MagicEnumInt;

/**
 * A Status that boost player's speed of a certain amount.
 */
public class BonusSpeed extends Status {

	/**
	 * Constructor.
	 */
	public BonusSpeed() {
		super();

		this.setStatusName(StatusEnum.BonusSpeed);
		this.setCoolDown(MagicEnumInt.SEVEN.getValue()); // 7 s
		this.setBoostFactor(MagicEnumDouble.BOOST.getValue());

		this.setEffect(
				() -> this.getPlayer().setSpeed(this.getPlayer().getSpeed().doubleValue() * this.getBoostFactor()));

		this.setRemoveEffect(() -> {
			this.getPlayer().setSpeed(getPlayer().getSpeed().doubleValue() * (double) MagicEnumInt.ONE.getValue()
					/ this.getBoostFactor());

			/*
			 * After the end of the effect we need to udpate collisions.
			 */
			AbstractHelper.resetBounds();
		});
	}
}
