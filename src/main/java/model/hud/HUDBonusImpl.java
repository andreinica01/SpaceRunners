package model.hud;

import java.io.File;
import java.util.stream.IntStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.status.StatusEnum;
import utilities.MagicEnumInt;
import utilities.MagicEnumString;
import view.gameField.GameField;

public class HUDBonusImpl implements IHUDBonus {

	/*
	 * HUD parameters
	 */
	private static final int SPACING = 30;
	private static final int X_LAYOUT = 50;
	private static final int Y_TRANSLATION = 645;

	/*
	 * Control fields
	 */
	private ImageView[] bonus = new ImageView[MagicEnumInt.FIVE.getValue()];
	private GameField gameField;

	/**
	 * Constructor.
	 * 
	 * @param gameField
	 */
	public HUDBonusImpl(final GameField gameField) {
		this.gameField = gameField;

		this.addBonuses();
	}

	@Override
	public final ImageView[] getBonus() {
		return this.bonus;
	}

	/**
	 * Add all bonuses.
	 */
	private void addBonuses() {

		IntStream.range(MagicEnumInt.ZERO.getValue(), MagicEnumInt.FIVE.getValue()).forEach(index -> {
			this.bonus[index] = new ImageView(new Image(
					new File(MagicEnumString.IMAGE_FOLDER.getValue() + "bonus" + index + ".png").toURI().toString(),
					SPACING, SPACING, false, true));
			this.bonus[index].setLayoutX(MagicEnumInt.WIDTH.getValue() - X_LAYOUT);
			this.bonus[index].setLayoutY(index * -SPACING);
			this.bonus[index].setTranslateY(Y_TRANSLATION);
			this.bonus[index].setViewOrder(MagicEnumInt.COMMON_VIEW_ORDER.getValue());
		});
	}

	@Override
	public final void showBonus(final StatusEnum bonus) {
		try {
			switch (bonus) {
			case BonusLife:
				this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.ZERO.getValue()]);
				break;
			case BonusSpeed:
				this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.ONE.getValue()]);
				break;
			case MalusCommand:
				this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.TWO.getValue()]);
				break;
			case MalusFire:
				this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.THREE.getValue()]);
				break;
			case MalusSpeed:
				this.gameField.getGameContainer().getChildren().add(this.bonus[MagicEnumInt.FOUR.getValue()]);
				break;
			default:
			}

		} catch (Exception e) {
			this.hideBonus(bonus);
			this.showBonus(bonus);
		}
	}

	@Override
	public final void hideBonus(final StatusEnum bonus) {
		try {
			this.gameField.getGameContainer().getChildren().remove(this.bonus[bonus.ordinal()]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
