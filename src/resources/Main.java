package resources;

import javafx.application.Application;
import javafx.stage.Stage;
import resources.views.ViewManager;

/**
 * Herfra startes spillet, og viewManager intialiseres
 */
public class Main extends Application {

    private static ViewManager viewManager;

    @Override
    public void start(Stage primaryStage) {
        viewManager = new ViewManager();
        viewManager.initalizeMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static ViewManager getViewManager() {
        return viewManager;
    }
}

