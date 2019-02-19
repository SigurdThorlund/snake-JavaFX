package resources.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resources.GameController;
import resources.Main;
import resources.model.Apple;
import resources.model.Game;
import resources.model.Obstacle;
import resources.model.Snake;

public class MainMenuController {

    private static int X_VALUE = 20;
    private static int Y_VALUE = 20;

    private Scene gameScene;

    private boolean powerUp;
    private char difficulty = 'N';

    private ViewManager viewManager = Main.getViewManager();

    public void handleStart() {
        powerUp = viewManager.getPowerApples().isSelected();
        setGridSize(viewManager.getInputGridSizeX().getText(), viewManager.getInputGridSizeY().getText());
        setDifficulty();

        viewManager.initGameStage();

        Stage gameStage = viewManager.getGameStage();
        launchSnake(gameStage);
        gameStage.setScene(gameScene);
        viewManager.getPrimaryStage().close();
        gameStage.show();
    }

    public void handleSetting() {
        viewManager.getMainSubScene().translateSubSceneOut();
        viewManager.getSettingSubScene().translateSubSceneOut();
        viewManager.getScoreSubScene().translateSubSceneIn();
    }

    public void handleScore() {
        viewManager.getMainSubScene().translateSubSceneOut();
        viewManager.getScoreSubScene().translateSubSceneOut();
        viewManager.getSettingSubScene().translateSubSceneIn();
    }

    public void handleQuit() {
        System.exit(0);
    }

    private void launchSnake(Stage gameStage) {
        //Laver slange, æble, game
        Game game = new Game(X_VALUE, Y_VALUE, powerUp, difficulty);
        Obstacle obstacle = new Obstacle(game.getWidth(), game.getHeight());
        Snake snek = new Snake(game.getWidth(), game.getHeight(), game.isNoBorder(), obstacle);
        Apple apple = new Apple(game.getWidth(), game.getHeight(), snek, obstacle);

        //Instanser af view og controller, der har de samme instanser af snake og apple.
        GameView gameView = new GameView(game);
        GameController controller = new GameController(snek, apple, game, gameView, obstacle, gameStage);

        StackPane gamePane = new StackPane();
        gameScene = new Scene(gamePane, game.getWidth() * game.getMultiplier(), game.getHeight() * game.getMultiplier());

        //Styrer on KeyPress
        gameScene.setOnKeyPressed(controller::handle);

        //Laver scene og tilføjer canvas.
        gameView.makeScene(gameScene, snek, apple);
        gamePane.getChildren().addAll(gameView.gridCanvas, gameView.gameOverLabel, gameView.gameWonLabel, gameView.highScoreLabel, gameView.viewHighScore, gameView.userName, gameView.scoreLabel);
        gamePane.setPadding(new Insets(20));
        gamePane.setAlignment(gameView.gameOverLabel, Pos.TOP_CENTER);
        gamePane.setAlignment(gameView.gameWonLabel, Pos.TOP_CENTER);
        gamePane.setAlignment(gameView.highScoreLabel, Pos.CENTER);
        gamePane.setAlignment(gameView.scoreLabel, Pos.BOTTOM_CENTER);
    }

    private void setGridSize(String width, String height) {
        boolean validGridSize = false;
        if (width.matches("-?\\d+") || height.matches("-?\\d+")) {
            validGridSize = ((Integer.parseInt(width) >= 5) && (Integer.parseInt(width) <= 100));
            if (((Integer.parseInt(height) < 5) || (Integer.parseInt(height) > 100))) {
                validGridSize = false;
            }
        }
        if (validGridSize) {
            X_VALUE = Integer.parseInt(width);
            Y_VALUE = Integer.parseInt(height);
        }
    }

    private void setDifficulty() {
        if (viewManager.getCheckEasy().isSelected()) {
            difficulty = 'E';
        } else if (viewManager.getCheckMedium().isSelected()) {
            difficulty = 'N';
        } else if (viewManager.getCheckHard().isSelected()) {
            difficulty = 'H';
        }
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public static int getxValue() {
        return X_VALUE;
    }

    public static int getyValue() {
        return Y_VALUE;
    }
}

