package view.mainWindow;

import view.gameField.GameFieldImpl;

public interface MainWindow {

    /**
     * Set the stage visible.
     * @param visible
     */
    void setVisible(boolean visible);

    /**
     * Add the gamefield to the stage and set its scene.
     * @param gamefield
     */
    void addGameField(GameFieldImpl gamefield);

    /**
     * Set stage widht.
     * @param width
     */
    void setWidth(Number width);

    /**
     * Set stage height.
     * @param heigth
     */
    void setHeight(Number heigth);
}