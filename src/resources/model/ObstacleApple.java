package resources.model;

import javafx.scene.image.Image;


/**
 * forhindringsæble der gør Snake svært ved at tilføje forhindringer
 */
public class ObstacleApple extends Apple{
    private Image image = new Image("resources/views/res/snakeapplebrown.png");

    public ObstacleApple(int x, int y, Snake snek, Obstacle obstacle) {
        super(x, y, snek, obstacle);
    }

    public Image getImage() {
        return image;
    }

    public int getPoints() {
        int points = 5;
        return points;
    }
}