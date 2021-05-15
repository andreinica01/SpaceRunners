package view.mainWindow;

import view.gameField.GameFieldImpl;

public interface MainWindow {

    void setVisible(boolean visible);
    
    void addGameField(GameFieldImpl gamefield);

    void setWidth(Number width);
    void setHeight(Number heigth);

    

}
