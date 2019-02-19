package resources.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;


/**
 * Denne klasse indeholder information om Snake
 * samt metoder til at flytte rundt, tjekke
 * om den har ramt sig selv eller om den er uden for gitteret
 */
public class Snake {
    private Color headColor = Color.web("2eb872");
    private Color bodyColor = Color.web("a3de83");
    private boolean dead = false;
    private final Point head = new Point();
    private final ArrayList<Point> body = new ArrayList<>();
    private final int gx;
    private final int gy;
    private boolean ignoreBorders = false;
    private boolean noBorder;
    private Obstacle obstacle;
//    private Image imgInputHead = new Image(Apple.class.getResourceAsStream("../views/res/snakehead.png"));
    private Image imgInputHead = new Image("resources/views/res/snakehead.png");
    private ImageView imageHead = new ImageView(imgInputHead);
    private Image imgInputBody = new Image("resources/views/res/snakebody.png");
    private ImageView imageBody = new ImageView(imgInputBody);
    private Image imgInputTurn = new Image("resources/views/res/snaketurnleft.png");
    private ImageView imageTurn = new ImageView(imgInputTurn);
    private Image imgInputTail = new Image("resources/views/res/snaketail.png");
    private ImageView imageTail = new ImageView(imgInputTail);
    private int snakeDir = 270;

    public Snake(int x, int y, boolean noBorder, Obstacle obstacle) {
        this.obstacle = obstacle;
        this.noBorder = noBorder;
        this.head.setLocation(x / 2, y / 2);
        this.body.add(new Point(x / 2 + 1, y / 2));
        this.gx = x;
        this.gy = y;
    }

    /**
     * Flytter slangen i retning alt efter dir.
     * Tilføjer hovedets gamle punkt til kroppen.
     */
    public void move(char dir) {
        body.add(new Point(head));
        // Switch-case for hver mulig direction (up, down, left, right)
        switch (dir) {
            case 'U':
                snakeDir = 0;
                head.translate(0, -1);
                if (borderControl() && (!ignoreBorders && !noBorder) || obstacle.getObstacles().contains(head)) {
                    head.translate(0, 1);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(head.getX(), gy - 1);
                }
                break;

            case 'D':
                snakeDir = 180;
                head.translate(0, 1);
                if (borderControl() && (!ignoreBorders && !noBorder) || obstacle.getObstacles().contains(head)) {
                    head.translate(0, -1);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(head.getX(), 0);
                }
                break;

            case 'L':
                snakeDir = 270;
                head.translate(-1, 0);
                if (borderControl() && (!ignoreBorders && !noBorder) || obstacle.getObstacles().contains(head)) {
                    head.translate(1, 0);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(gx - 1, head.getY());
                }
                break;

            case 'R':
                snakeDir = 90;
                head.translate(1, 0);
                if (borderControl() && (!ignoreBorders && !noBorder) || obstacle.getObstacles().contains(head)) {
                    head.translate(-1, 0);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(0, head.getY());
                }
                break;
        }

        // Slangen skal være større end fire for at kunne spise sig selv
        if (body.size() > 4 && hitSelf() && !body.get(0).equals(head)) {
            dead = true;
        }
    }

    /**
     * Returnerer den vinkel slangens krop skal placeres i enten horisontalt eller vertikalt
     * @param bodyCount hvilket nummer på slangens krop der bestemmes
     * @return vinkel på kroppen
     */
    public int getBodyDir(int bodyCount) {
        if (bodyCount == 0) {
            return 0;
        } else if (body.get(bodyCount).getY() == body.get(bodyCount - 1).getY()) {
            return 270;
        } else {
            return 0;
        }
    }

    /**
     * Tjekker om slangens hovede deler
     * position med et æble. Fjerner enden
     * af halen, hvis de deler position.
     */
    public boolean ateApple(Apple apple) {
        if (!head.equals(apple.getPos())) {
            body.remove(0);
        }
        return head.equals(apple.getPos());
    }

    /**
     * Returnerer true hvis slangens hoved er uden for et af spillets kanter
     */
    private boolean borderControl() {
        return head.getX() < 0 || head.getY() < 0 || head.getX() >= gx || head.getY() >= gy;
    }

    /**
     * Returnerer true hvis kroppen indeholder hovedets punkt
     */
    public boolean hitSelf() {
        return body.contains(head);
    }

    public ArrayList<Point> getBody() {
        return new ArrayList<>(body);
    }

    public Point getHead() {
        return new Point(head);
    }

    public Color getHeadColor() {
        return headColor;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public boolean getDead() {
        return dead;
    }

    public void ignoreBorders(boolean ign) {
        ignoreBorders = ign;
    }

    public ImageView getImageViewHead() {
        return imageHead;
    }

    public ImageView getImageViewBody() {
        return imageBody;
    }

    public ImageView getImageViewTurn() {
        return imageTurn;
    }

    public int getSnakeDir() {
        return snakeDir;
    }

    public ImageView getImageViewTail() {
        return imageTail;
    }
}
