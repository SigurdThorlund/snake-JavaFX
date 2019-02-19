package resources.model;

import javafx.scene.image.Image;

/** Bonusæble der øger spillets hastighed */
public class SpeedApple extends Apple{
    private Image image = new Image("resources/views/res/snakeappleyellow.png");

    public SpeedApple(int x, int y, Snake snek, Obstacle obstacle) {
        super(x, y, snek, obstacle);
    }

    public int getPoints() {
        int points = 5;
        return points;
    }

    public double getSpeedBonus() {
        double speedBonus = 5;
        return speedBonus;
    }

    public Image getImage() {
        return image;
    }
}
