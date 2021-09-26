package View;


import Model.Model;
import View.GameScreens.GameOverScreen;
import View.GameScreens.NewGameScreen;
import View.GameScreens.QuestionScreen;
import View.GameScreens.WelcomeScreen;

import javax.swing.*;
import java.awt.*;


public class GameScreenPanel extends JPanel{


    private WelcomeScreen welcomeScreen;
    private NewGameScreen newGameScreen;
    private QuestionScreen questionScreen;
    private GameOverScreen gameOverScreen;
    private CardLayout cardLayout = new CardLayout();;


    // FULHACK:
    private Model model;


    public GameScreenPanel(Model model) {
        this.model = model;
        setLayout(cardLayout);


        welcomeScreen = new WelcomeScreen();
        newGameScreen = new NewGameScreen(welcomeScreen);
        questionScreen = new QuestionScreen(model);
        gameOverScreen = new GameOverScreen();

        addAllPanels();
    }

    private void addAllPanels() {
        add(welcomeScreen, "10");
        add(newGameScreen, "20");
        add(questionScreen, "30");
        add(gameOverScreen, "40");
        cardLayout.show(this,"10");
    }


    public WelcomeScreen getWelcomeScreen() {
        return welcomeScreen;
    }

    public NewGameScreen getNewGameScreen() {
        return newGameScreen;
    }

    public QuestionScreen getQuestionScreen() {
        return questionScreen;
    }

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }


}
