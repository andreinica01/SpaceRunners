package controller.gameSwitcher;

import java.io.IOException;

import javafx.scene.Scene;

public interface IStrategyFXML {

    /**
     * load the specified scene.
     * @param path
     * @return
     * @throws IOException
     */
    Scene getSceneFromFxml(String path) throws IOException;
}
