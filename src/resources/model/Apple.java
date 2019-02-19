package resources.model;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.Random;


/** Model til at indeholde informationer om
 * et æble-instans. Dvs. dens position og farve.
 */

public class Apple {
    private Point pos;
    private Image image = new Image("resources/views/res/snakeapple.png");
    /** Konstruktøren danner en ny position for
     * æblet, indtil positionen ikke allerede er taget.
     * @param x Spillets bredde.
     * @param y Spillets højde.
     * @param snek Slangen som æblet ikke må placeres oveni.
     */
    public Apple(int x, int y, Snake snek, Obstacle obstacle) {
        int tx;
        int ty;
        do {
            Random rnd = new Random();

            tx = rnd.nextInt(x);
            ty = rnd.nextInt(y);
            pos = new Point(tx, ty);
        } while (snek.getHead().equals(pos) || snek.getBody().contains(pos) || obstacle.getObstacles().contains(pos));
    }

    public Point getPos() {
        return new Point(pos);
    }

    public int getPoints() {
        int points = 5;
        return points;
    }

    public Image getImage() {
        return image;
    }
}