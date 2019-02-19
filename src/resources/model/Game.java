package resources.model;

import javafx.scene.paint.Color;

/**
 * Gemmer information om det nuværende snakespil.
 * Bl.a. banestørrelse og antal æbler spist.
 */
public class Game {
    private Color color = Color.web("feffe4");
    private int width;
    private int height;
    private int score;
    private int maxScore;
    private double multiplier;
    private int scorePoints;
    private double speed;
    private boolean powerUp;
    private boolean noBorder;

    /**
     * @param x Spillets bredde
     * @param y Spillets højde
     */
    public Game(int x, int y, boolean powerUp, char difficulty) {
        if (difficulty == 'E') {
            speed = 8.5;
            noBorder = true;
        } else if (difficulty == 'N') {
            speed = 8.5;
            noBorder = false;
        } else if (difficulty == 'H') {
            speed = 13;
            noBorder = false;
        }
        this.powerUp = powerUp;
        width = x;
        height = y;
        maxScore = x * y - 2;
        this.powerUp = powerUp;
        if (height > 50) {
            multiplier = 10;
        } else {
            multiplier = 20;
        }
    }

    /**
     * returnerer true hvis score er lig den
     * maksimale score muligt.
     */
    public boolean gameWon() {
        return score == maxScore;
    }

    /**
     * Inkrementerer score.
     */
    public void incScore() {
        score++;
    }

    public void decMaxScore(int num) {
        this.maxScore -= num;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void resetScore() {
        score = 0;
        scorePoints = 0;
    }

    public Color getColor() {
        return color;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void addPoints(int n) {
        scorePoints += n;
    }

    public int getScorePoints() {
        return scorePoints;
    }

    public boolean isPowerUp() {
        return powerUp;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isNoBorder() {
        return noBorder;
    }
}
