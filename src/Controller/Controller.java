package Controller;

import Model.*;
import View.MainGUI;

import java.io.IOException;


public class Controller implements Observer {

    private Model model;
    private MainGUI mainGUI;


    public Controller(Model model, MainGUI mainGUI) {
        this.model = model;
        this.mainGUI = mainGUI;
        actionListenerSetups();

    }

    private void startGame() {
        model.startGame();
    }

    private void pauseGame() {
        model.pauseGame();
    }

    private void startNewGame(int amountOfQuestions) {
        model.startTimerWithCountDown(3);
        model.initiateAmountOfQuestions(amountOfQuestions);

    }

    private void reset() {
        model.gameReset();
    }

    private void answerCurrentQuestion(String answer) {
        try {
            model.gameRunning(Integer.parseInt(answer));
        } catch (NumberFormatException | IOException ignored) {

        }


    }

    private void actionListenerSetups() {
        actionListenerGameOverScreen();
        actionListenerNewGameScreen();
        actionListenerQuestionScreen();
        actionListenerWelcomeScreen();

    }

    private void actionListenerGameOverScreen() {
        mainGUI.getGamePanel().getGameOverScreen().setTryagainButton(e -> Controller.this.reset());
        mainGUI.getGamePanel().getGameOverScreen().setTryagainButton(e ->
                mainGUI.getGamePanel().getCardLayout().show(mainGUI.getGamePanel(), "20")
        );
    }

    private void actionListenerNewGameScreen() {

        mainGUI.getGamePanel().getNewGameScreen().setStartGameButton(e ->
                initNewGame());

        mainGUI.getGamePanel().getNewGameScreen().setChangeNameButton(e ->
                mainGUI.getGamePanel().getCardLayout().show(mainGUI.getGamePanel(), "10"));

    }

    private void actionListenerQuestionScreen() {
        mainGUI.getGamePanel().getQuestionScreen().setEnterAnswer(e -> Controller.this.answerCurrentQuestion(
                (mainGUI.getGamePanel().getQuestionScreen().getAnswer())));
    }

    private void actionListenerWelcomeScreen() {
        mainGUI.getGamePanel().getWelcomeScreen().setSubmitButton(e -> checkWelcomeName());

    }

    private void initNewGame() {
        mainGUI.getGamePanel().getCardLayout().show(mainGUI.getGamePanel(), "30");

        startGame();
        startNewGame(mainGUI.getGamePanel().getNewGameScreen().getAmountOfQuestions());
    }

    private void checkWelcomeName() {
        boolean validName = mainGUI.getGamePanel().getWelcomeScreen().validPlayerName();
        if (validName) {
            String name = mainGUI.getGamePanel().getWelcomeScreen().getPlayerTextField().getText();
            model.setPlayerName(name);
            mainGUI.getGamePanel().getCardLayout().show(mainGUI.getGamePanel(), "20");
        }

    }

    private void initiateGameOverScreen() {
        if (model.isGameOver()) {
            int totalAmountOfQuestions = model.getTotalQuestions();
            String time = model.getTime();

            mainGUI.getGamePanel().getGameOverScreen().initStatistics(totalAmountOfQuestions, time);
            mainGUI.getGamePanel().getCardLayout().show(mainGUI.getGamePanel(), "40");
        }

    }


    @Override
    public void notifyOb() {
        initiateGameOverScreen();
    }

    @Override
    public void eventUpdate() {

    }
}
