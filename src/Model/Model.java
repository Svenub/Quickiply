package Model;

import Model.HighscoreTable.HighscoreTable;
import Model.HighscoreTable.Score;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

public class Model {


    private Multiplier multiplier;
    private Publisher publisher;
    private Stopwatch stopwatch;
    private Player currentPlayer = new Player();
    private List<MathQ> allQuestion;
    private List<MathQ> correctMathQList;
    private List<MathQ> wrongMathQList;
    private int totalQuestions;
    private boolean gameOver;
    private boolean invalidInput;
    private int countdown = 0;
    private TimerListener timerListener = new TimerListener();
    private Timer startGameTimer = new Timer(0, timerListener);

    private HighscoreTable highscoreTable = new HighscoreTable();


    private MathQ current_mathQ;


    public Model() throws IOException {

        multiplier = new Multiplier();
        publisher = new Publisher();

        stopwatch = new Stopwatch();
        correctMathQList = new ArrayList<>();
        wrongMathQList = new ArrayList<>();
        allQuestion = new ArrayList<>();
        createHighscore();

    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
                publisher.notifyObserver();


        }
    }

    public void startGame()  {

        startGameTimer.start();
        gameOver = false;

    }

    public void pauseGame(){
        startGameTimer.stop();
    }

    public void initiateAmountOfQuestions(int amountOfQuestions) {
        totalQuestions = amountOfQuestions;
        if (stopwatch.isRunning() && allQuestion.isEmpty()) {
            for (int i = 0; i < amountOfQuestions; i++) {
                MathQ q = generateQ();
                allQuestion.add(q);

            }
            current_mathQ = allQuestion.get(0);
        }


    }

    public void gameRunning(int answer) throws IOException {
        Random rand = new Random();
        try{
            answerCurrentMathQ(answer);
        } catch (NumberFormatException ignored){

        }

        if (current_mathQ.isCorrectAnswered()) {
            publisher.eventUpdate();
            correctMathQList.add(current_mathQ);
            allQuestion.remove(current_mathQ);
            if(allQuestion.isEmpty()){
                weHaveAWinner();

            } else{
                int nextQuestion = rand.nextInt(allQuestion.size());
                current_mathQ = allQuestion.get(nextQuestion);
            }

        } else {
            int nextQuestion = rand.nextInt(allQuestion.size());
            publisher.eventUpdate();
            current_mathQ = allQuestion.get(nextQuestion);
        }

    }

    private void weHaveAWinner() throws IOException {
        if (correctMathQList.size() == totalQuestions) {
            gameOver = true;
            stopwatch.stop();
            currentPlayer.setScore(getTotalQuestions());
            pauseGame();
            double speed = totalQuestions / getTotalSecondsFromTime(stopwatch.getTime());
            double calculatedScore = totalQuestions*speed*100;
            Score score = new Score(currentPlayer.getName(),Math.round(calculatedScore));
            highscoreTable.addScore(score);
            highscoreTable.listToFile();
            publisher.eventUpdate();
            publisher.notifyObserver();
        }
    }

    public MathQ getCurrent_mathQ() {
        return current_mathQ;
    }

    public void answerCurrentMathQ(int answer) {
        current_mathQ.answerQuestion(answer);
    }

    public MathQ generateQ() {
        MathQ q = multiplier.generateQuestion();
        return q;

    }

    public void createHighscore() {
        try {
            File highscores = new File("Highscores.txt");
            if (highscores.createNewFile()) {
                System.out.println("File created: " + highscores.getName());
                System.out.println("At: " + highscores.getAbsolutePath());
            } else {
                System.out.println("Loading HighScores");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void startTimerWithCountDown(int countdown) {
        stopwatch.startWithCountdown(countdown);
    }

    public String getTime() {
        return stopwatch.getTime();
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public boolean isStopwatchRunning() {
        try {
            return stopwatch.isRunning();
        } catch (NullPointerException e) {
            return false;
        }

    }

    public void setPlayerName(String name){
        currentPlayer.setName(name);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void gameReset(){
        gameOver = false;
        allQuestion.clear();
        correctMathQList.clear();
        stopwatch.reset();
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getRemainingQuestions(){
        return allQuestion.size();
    }

    public int amountOfRightAnswer() {
        int counter = 0;
        for (MathQ question : correctMathQList) {
            if (question.isCorrectAnswered()) {
                counter++;
            }
        }
        return counter;
    }

    public int amountOfWrongAnswer() {
        int counter = 0;
        for (MathQ question : wrongMathQList) {
            if (question.isCorrectAnswered()) {
                counter++;
            }
        }
        return counter;
    }

    public void setInvalidInput(boolean invalidInput) {
        this.invalidInput = invalidInput;
    }

    public boolean isCountingDown(){
        return stopwatch.isCountingDown();
    }

    public String getCountdown(){
        return String.valueOf(stopwatch.getCountDown());
    }

    public String getInfo(){
        if (invalidInput){
            invalidInput = false;
            return "Only numbers!";
        } else if(current_mathQ.isCorrectAnswered()){
            return "Correct!";
        }
        else {
            return "Not correct!";
        }
    }

    private static double getTotalSecondsFromMinutes(String string){
        int seconds = 0;
        seconds += Integer.parseInt(String.valueOf(string.charAt(0))) *10;
        seconds += Integer.parseInt(String.valueOf(string.charAt(1)));
        return seconds*60;

    }
    private static double getTotalSecondsFromTime(String string){
        int seconds = 0;
        seconds += getTotalSecondsFromMinutes(string);
        seconds += Integer.parseInt(String.valueOf(string.charAt(3))) *10;
        seconds += Integer.parseInt(String.valueOf(string.charAt(4)));
        return seconds;

    }

    public ArrayList <Score> getHighscoreList(){
        return highscoreTable.getHighscoreList();
    }

}
