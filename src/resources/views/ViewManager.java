package resources.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import resources.Main;
import resources.model.AdvancedSnakeSubScene;
import resources.model.HighScore;

import java.io.IOException;

public class ViewManager {

    //Mainstage.
    private Stage primaryStage = new Stage();
    private AnchorPane root;

    //Gamestage
    private Stage gameStage;

    //Icon
    private Image snakeLogo = new Image("resources/views/res/SnakeLogoWhite.png");

    //mainSubScene
    private AdvancedSnakeSubScene mainSubScene;
    private String css = "resources/views/styling.css";
    private Image redApple = new Image("resources/views/res/snakeapple.png");
    private Image blueApple = new Image("resources/views/res/snakeappleblue.png");
    private Image rainbowApple = new Image("resources/views/res/snakeapplerainbow.png");
    private Image yellowApple = new Image("resources/views/res/snakeappleyellow.png");
    private Image brownApple = new Image("resources/views/res/snakeapplebrown.png");


    //ScoreSubScene
    private AdvancedSnakeSubScene scoreSubScene;

    //SettingSubScene
    private AdvancedSnakeSubScene settingSubScene;
    private CheckBox powerApples;
    private CheckBox checkEasy;
    private CheckBox checkMedium;
    private CheckBox checkHard;
    private TextField inputGridSizeX;
    private TextField inputGridSizeY;

    public ViewManager() {
    }

    public void initalizeMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/MainMenu.fxml"));
            root = loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        initStartSubScene();
        initScoreSubScene();
        initSettingSubScene();
        root.getChildren().addAll(scoreSubScene, settingSubScene, mainSubScene);
        Scene mainScene = new Scene(root);

        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(snakeLogo);
        primaryStage.setTitle("Advanced Snake");
        primaryStage.show();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void initStartSubScene() {
        mainSubScene = new AdvancedSnakeSubScene("resources/views/res/SubScene.png");
        mainSubScene.setLayoutX(85);
        AnchorPane mainRoot = (AnchorPane) mainSubScene.getRoot();

        Label welcomeText = new Label();
        welcomeText.setId("welcomeLabel");
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setLayoutX(60);
        welcomeText.setLayoutY(20);
        welcomeText.setText("Velkommen til Advanced Snake \n" +
                "Tryk på 'Settings' for at vælge sværhedsgrad, \n" +
                " og implementere bonusæbler og mere. \n" +
                "Under 'Score' kan du se din HighScore.\n\n" +
                "Brug piletasterne til at bevæge slangen, 'P'\n" +
                "pauser og 'Tab' starter forfra.\n" +
                "'Esc' bruges til at komme tilbage til menuen\n" +
                "Bonus æbler:");

        welcomeText.getStylesheets().add(css);

        HBox applebox = new HBox();
        HBox labelbox = new HBox();

        ImageView redAppleImage = new ImageView();
        makeAppleImages(redAppleImage, redApple);

        ImageView blueAppleImage = new ImageView();
        makeAppleImages(blueAppleImage, blueApple);

        ImageView yellowAppleImage = new ImageView();
        makeAppleImages(yellowAppleImage, yellowApple);

        ImageView rainbowAppleImage = new ImageView();
        makeAppleImages(rainbowAppleImage, rainbowApple);

        ImageView brownAppleImage = new ImageView();
        makeAppleImages(brownAppleImage, brownApple);

        Label redAppleText = new Label();
        redAppleText.setText("5 Point");
        makeAppleLabels(redAppleText);

        Label blueAppleText = new Label();
        blueAppleText.setText("15 Point");
        makeAppleLabels(blueAppleText);

        Label yellowAppleText = new Label();
        yellowAppleText.setText("Gør dig\n" +
                "hurtigere\n" +
                "5 Point");
        makeAppleLabels(yellowAppleText);

        Label rainbowAppleText = new Label();
        rainbowAppleText.setText("Gør det\n" +
                "muligt at\n" +
                "gå gennem\n" +
                "vægge\n" +
                "5 Point");
        makeAppleLabels(rainbowAppleText);

        Label brownAppleText = new Label();
        brownAppleText.setText("Placerer\n" +
                "vægge\n" +
                "på banen\n" +
                "5 Point");
        makeAppleLabels(brownAppleText);

        labelbox.setSpacing(18);
        labelbox.setLayoutX(40);
        labelbox.setLayoutY(300);

        labelbox.getChildren().addAll(redAppleText, blueAppleText, yellowAppleText, rainbowAppleText, brownAppleText);

        applebox.setSpacing(70);
        applebox.setLayoutX(50);
        applebox.setLayoutY(250);

        applebox.getChildren().addAll(redAppleImage, blueAppleImage, yellowAppleImage, rainbowAppleImage, brownAppleImage);

        mainRoot.getChildren().addAll(welcomeText, applebox, labelbox);
    }

    private void makeAppleImages(ImageView imageView, Image image) {
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    }

    private void makeAppleLabels(Label label) {
        label.setId("applelabel");
        label.getStylesheets().add(css);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setPrefWidth(100);
    }

    private void initScoreSubScene() {
        scoreSubScene = new AdvancedSnakeSubScene("resources/views/res/SubScene.png");

        Label highScoreLabel = new Label();
        highScoreLabel.getStylesheets().add(css);
        highScoreLabel.setLayoutX(115);
        highScoreLabel.setLayoutY(5);

        HighScore highScore = new HighScore();
        highScore.readHighScoreFromFile();
        StringBuilder scores = new StringBuilder();

        for (int i = 0; i < highScore.getTotalArray().length; i++) {
            scores.append(highScore.getTotalArray()[i][0]).append("\t  -\t").append(highScore.getTotalArray()[i][1]).append("\n");
        }

        highScoreLabel.setText("     HIGH SCORE \n" + scores);
        highScoreLabel.setVisible(true);

        AnchorPane scoreRoot = (AnchorPane) scoreSubScene.getRoot();
        scoreRoot.getChildren().add(highScoreLabel);
    }

    private void initSettingSubScene() {
        settingSubScene = new AdvancedSnakeSubScene("resources/views/res/SubScene.png");
        AnchorPane settingRoot = (AnchorPane) settingSubScene.getRoot();

        powerApples = new CheckBox("Power Ups");
        makeCheckBox(powerApples, true, 100, 200);
        checkEasy = new CheckBox("Easy");
        makeCheckBox(checkEasy, false, 100,250);
        checkMedium = new CheckBox("Normal");
        makeCheckBox(checkMedium, true, 100, 280);
        checkHard = new CheckBox("Hard");
        makeCheckBox(checkHard, false, 100,310);

        Label powerDescription = new Label("Slå bonus æbler til eller fra");
        makeLabel(powerDescription, 250, 203);
        Label easyDescription = new Label("Normal spil hastighed og ingen væg");
        makeLabel(easyDescription,250, 253);
        Label normalDescription = new Label("Normal spil hastighed og væg");
        makeLabel(normalDescription, 250, 283);
        Label hardDescription = new Label("Hurtig spil hastighed og væg");
        makeLabel(hardDescription, 250, 313);

        inputGridSizeX = new TextField();
        inputGridSizeX.setPromptText("Indsæt bredde(5 - 100)");
        inputGridSizeX.setLayoutX(100);
        inputGridSizeX.setLayoutY(100);
        inputGridSizeY = new TextField();
        inputGridSizeY.setPromptText("Indsæt Højde(5 - 100)");
        inputGridSizeY.setLayoutX(100);
        inputGridSizeY.setLayoutY(140);
        inputGridSizeX.getStylesheets().add(css);
        inputGridSizeY.getStylesheets().add(css);

        settingRoot.getChildren().addAll(checkEasy, checkMedium, checkHard, powerApples, inputGridSizeX, inputGridSizeY, powerDescription, easyDescription, normalDescription, hardDescription);
    }

    private void makeCheckBox(CheckBox checkBox, boolean selected, int x, int y) {
        checkBox.setSelected(selected);
        checkBox.getStylesheets().add(css);
        checkBox.setLayoutX(x);
        checkBox.setLayoutY(y);
        checkBox.setOnAction(e -> {
            if (e.getSource().equals(checkEasy)) {
                checkEasy.setSelected(true);
                checkMedium.setSelected(false);
                checkHard.setSelected(false);
            } else if (e.getSource().equals(checkMedium)) {
                checkEasy.setSelected(false);
                checkMedium.setSelected(true);
                checkHard.setSelected(false);
            } else if (e.getSource().equals(checkHard)) {
                checkEasy.setSelected(false);
                checkMedium.setSelected(false);
                checkHard.setSelected(true);
            }
        });
    }

    private void makeLabel(Label label, int x, int y) {
        label.getStylesheets().add(css);
        //label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle("-fx-font-size: 15;");
        label.setLayoutX(x);
        label.setLayoutY(y);
    }

    public AdvancedSnakeSubScene getScoreSubScene() {
        return scoreSubScene;
    }

    public AdvancedSnakeSubScene getSettingSubScene() {
        return settingSubScene;
    }

    public AdvancedSnakeSubScene getMainSubScene() { return mainSubScene; }

    public CheckBox getPowerApples() {
        return powerApples;
    }

    public CheckBox getCheckEasy() {
        return checkEasy;
    }

    public CheckBox getCheckMedium() {
        return checkMedium;
    }

    public CheckBox getCheckHard() {
        return checkHard;
    }

    public TextField getInputGridSizeX() {
        return inputGridSizeX;
    }

    public TextField getInputGridSizeY() {
        return inputGridSizeY;
    }

    public void initGameStage() {
        gameStage = new Stage();
        gameStage.setTitle("Advanced Snake");
        gameStage.getIcons().add(snakeLogo);
        gameStage.setResizable(false);
    }

    public Stage getGameStage() {
        return gameStage;
    }
}
