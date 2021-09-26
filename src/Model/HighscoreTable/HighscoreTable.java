package Model.HighscoreTable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HighscoreTable {


    private File highscores;
    private String filePath = "Highscores.txt";
    private String identifier = "-";
    private ArrayList<String> rawHighscoreInfo;
    private ArrayList<Score> highscoreList;


    public HighscoreTable() throws IOException {
        createHighscoreFile();
        highscores = loadHighscoreFile();
        rawHighscoreInfo = new ArrayList<>();
        rawHighscoreInfo = readFileIntoList();

        highscoreList = new ArrayList<>();
        convertRawToScoreList();
    }

    private void createHighscoreFile() {
        try {
            File highscores = new File("Highscores.txt");
            if (highscores.createNewFile()) {
                filePath = highscores.getAbsolutePath();
                System.out.println("File created: " + highscores.getName());
                System.out.println("At: " + highscores.getAbsolutePath());
            } else {
                System.out.println("Loading HighScores");
                filePath = highscores.getAbsolutePath();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private Score convert(String string) {

        // 1.Soleen - 6500
        Scanner scanner = new Scanner(string).useDelimiter("");
        String placing = scanner.next();
        scanner.next();
        StringBuilder name = new StringBuilder();
        while (!scanner.hasNext("-")) {
            name.append(scanner.next());
        }
        name.delete(name.length() - 1, name.length());

        StringBuilder score = new StringBuilder();
        scanner.next();
        scanner.next();
        while (scanner.hasNext()) {
            String data = scanner.next();
            {
                score.append(data);
            }
        }


        return new Score(name.toString(), Integer.parseInt(placing), Double.parseDouble(score.toString()));

    }

    private void convertRawToScoreList() {
        for (String score : rawHighscoreInfo) {
            highscoreList.add(convert(score));
        }

    }

    private ArrayList<String> readFileIntoList() throws FileNotFoundException {
        Scanner s = new Scanner(highscores);
        while (s.hasNext()) {
            rawHighscoreInfo.add(s.nextLine());
        }
        s.close();

        return rawHighscoreInfo;
    }

    public void listToFile() throws IOException {
        FileWriter writer = new FileWriter("Highscores.txt");

        for (Score score : highscoreList) {
            writer.append(score.toString()).append("\n");
        }
        writer.close();
    }

    public void updatePlacing(int startIndex) {
        for (int i = startIndex + 1; i < highscoreList.size(); i++) {
            Score score = highscoreList.get(i);
            score.setPlacing(score.getPlacing() + 1);
        }
    }

    public void addScore(Score score) {
        if (highscoreList.isEmpty()) {
            score.setPlacing(1);
            highscoreList.add(score);
        } else {
            int beforeSize = highscoreList.size();
            int maxHighscoreSize = 10;

            if (beforeSize < maxHighscoreSize) {
                for (int i = 0; i < highscoreList.size(); i++) {
                    if (score.getScore() >= highscoreList.get(i).getScore()) {
                        score.setPlacing(highscoreList.get(i).getPlacing());
                        highscoreList.add(i, score);
                        updatePlacing(i);
                        break;
                    }
                }
            } else {
                for (int i = 0; i < highscoreList.size(); i++) {
                    if (score.getScore() >= highscoreList.get(i).getScore()) {
                        score.setPlacing(highscoreList.get(i).getPlacing());
                        highscoreList.add(i, score);
                        updatePlacing(i);
                        highscoreList.remove(highscoreList.size() - 1);
                        break;
                    }
                }
            }
            int afterSize = highscoreList.size();
            if (beforeSize == afterSize && beforeSize != maxHighscoreSize) {
                int lastPlacing = highscoreList.get(beforeSize - 1).getPlacing();
                score.setPlacing(lastPlacing + 1);
                highscoreList.add(score);
            }
        }

    }

    public File getHighscoreFile() {
        return highscores;
    }

    private File loadHighscoreFile() {
        return new File(filePath);
    }

    public ArrayList<Score> getHighscoreList() {
        return highscoreList;
    }

    public static void main(String[] args) throws IOException {
        HighscoreTable highscoreTableFile = new HighscoreTable();
        //   System.out.println(highscoreTableFile.getPlacing(18000.76));
        //  RandomAccessFile randomAccessFile = new RandomAccessFile(highscoreTableFile.getHighscoreFile(), "rw");
        //  FileWriter w = new FileWriter("test.txt", true);
        //  w.append("Fuck you ",4,8);
        //  w.close();
        // System.out.println(highscoreTableFile.getIndexFromHighscore(1900));
        // System.out.println(highscoreTableFile.writeScore("Rumpa", 3000));
        //  highscoreTableFile.addScoreToFile("Soleen", 4000);
        // highscoreTableFile.addScoreToFile("Sven", 2000);
        //  highscoreTableFile.addScoreToFile("Robert", 1000);
        // highscoreTableFile.addScoreToFile("Rumpa", 2500);
        Score score = new Score("Rumpa", 3000);
        Score score1 = new Score("prutt", 300);
        Score score2 = new Score("fis", 3500);
        Score score3 = new Score("Soleen", 6500);
        Score score4 = new Score("Grym", 10000);
        Score score5 = new Score("Dåölig", 10);

        Score score7 = new Score("nyyy", 4000);

        highscoreTableFile.addScore(score);
        highscoreTableFile.addScore(score1);
        highscoreTableFile.addScore(score2);
        highscoreTableFile.addScore(score3);
        highscoreTableFile.addScore(score4);
        highscoreTableFile.addScore(score5);

        //   highscoreTableFile.addScore(score7);
        System.out.println(highscoreTableFile.highscoreList);

        highscoreTableFile.listToFile();
    }

}


