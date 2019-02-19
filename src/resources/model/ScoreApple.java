package resources.model;

import javafx.scene.image.Image;

public class ScoreApple extends Apple {
    private Image image = new Image("resources/views/res/snakeappleblue.png");

    public ScoreApple(int x, int y, Snake snek, Obstacle obstacle) {
        super(x, y, snek, obstacle);
    }

    public Image getImage() {
        return image;
    }

    public int getPoints() {
        int points = 15;
        return points;
    }
}
