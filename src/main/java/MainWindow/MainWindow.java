package MainWindow;

import GameField.GameFieldImpl;
import javafx.scene.Scene;

public interface MainWindow {

    void setVisible(boolean visible);
    
    void addGameField(GameFieldImpl gamefield);

    void setWidth(Number width);
    void setHeight(Number heigth);

    Scene getScene();
    

}
