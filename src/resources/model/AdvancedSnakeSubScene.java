package resources.model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AdvancedSnakeSubScene extends SubScene {

    private final int WIDTH = 630;

    public AdvancedSnakeSubScene(String IMAGE_BACKGROUND) {
        super(new AnchorPane(), 630, 550);
        int HEIGHT = 517;
        prefWidth(HEIGHT); prefHeight(WIDTH);
        setLayoutX(810); setLayoutY(190);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(IMAGE_BACKGROUND,WIDTH, HEIGHT,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane subSceneRoot = (AnchorPane) this.getRoot();
        subSceneRoot.setBackground(new Background(backgroundImage));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0,0,0,0.29));
        setEffect(dropShadow);
    }

    public void translateSubSceneIn() {
        TranslateTransition animTransition = new TranslateTransition();
        animTransition.setDuration(Duration.seconds(0.3));
        animTransition.setNode(this);
        animTransition.setToX(WIDTH+95);
        animTransition.play();
    }

    public  void translateSubSceneOut() {
        TranslateTransition animTransition = new TranslateTransition();
        animTransition.setDuration(Duration.seconds(0.3));
        animTransition.setNode(this);
        animTransition.setToX(-WIDTH-95);
        animTransition.play();
    }

}