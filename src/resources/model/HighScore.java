package resources.model;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Al det tekniske til modellen HighScore, bliver lavet i denne klasse
 */
public class HighScore {
    private String path = "HighScore.txt";

    private boolean newHighScore = false;
    private String[][] totalArray = new String[10][2];

    public HighScore() {
            for (int i = 0; i < 10; i++) {
                totalArray[i][1] = "0";
            }
            for (int i = 0; i < 10; i++) {
                totalArray[i][0] = "AAAAA";
            }
    }

    /**
     * Denne metode læser tekstfilen med alle highscores.
     */
    public void readHighScoreFromFile() {
        File file = new File("HighScore.txt");

        int id = 0;

        try {
            if (file.createNewFile()) {

            } else {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) { // tjekker om der er en ny linje
                    String next = sc.nextLine(); // scanner næste linje
                    if (id % 2 == 0) {
                        totalArray[id / 2][0] = next;
                    } else {
                        totalArray[id / 2][1] = next;
                    }
                    id++;
                }
                sc.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sortArray();
    }

    /**
     * Tilføjer scoren til arrayet med de andre scores
     *
     * @param userName
     */
    public void addScore(Game game, String userName) {
        sortArray();
        if (game.getScorePoints() > Integer.parseInt(totalArray[9][1])) { //TODO flyt til et bedre sted
            newHighScore = true;
            String newScore = Integer.toString(game.getScorePoints());
            totalArray[9][0] = userName;
            totalArray[9][1] = newScore;
        }
        sortArray();
        try {
            writeNewHighScore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funktionen sammenligner scoren som står i arrayet,
     * og sorterer ud fra dette så navnet matcher scoren
     * efter den er sorteren i stigende rækkefølge
     */
    public void sortArray() {
        Arrays.sort(totalArray, (first, second) -> Integer.valueOf(second[1]).compareTo(Integer.valueOf(first[1])));

    }

    /**
     * Skriver den nye highscore til en fil
     *
     * @throws IOException
     */
    private void writeNewHighScore() throws IOException {
        File file = new File(path).getAbsoluteFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < totalArray.length; i++) {
            writer.write(String.valueOf(totalArray[i][0]));
            writer.newLine();
            writer.write(String.valueOf(totalArray[i][1]));
            if (i != totalArray.length - 1) writer.newLine();
        }
        writer.close();
    }

    public String[][] getTotalArray() {
        return totalArray;
    }
}

