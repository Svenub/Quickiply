package View.GameScreens;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewGameScreen extends JPanel {


    private JButton startGameButton;
    private JButton changeNameButton;
    private JLabel welcomeNameLabel;
    private JSlider numberSlider;
    private JLabel sliderLabel;
    private WelcomeScreen welcomeScreen;


    public NewGameScreen(WelcomeScreen welcomeScreen) {
        this.welcomeScreen = welcomeScreen;
        setLayout(new GridLayout(4, 1));
        initStartMenuPanel();
    }

    private void initStartMenuPanel() {
        welcomeNameLabel = new JLabel("", SwingConstants.CENTER);
        welcomeNameLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        welcomeNameLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        welcomeNameLabel.setOpaque(true);

        add(welcomeNameLabel);

        welcomeScreen.getSubmitButton().addActionListener(e -> {
            setWelcomeNameLabel(welcomeScreen.getPlayerName());
            welcomeNameLabel.setText("Hello " + welcomeScreen.getPlayerName() + "!");

        });


        JTextArea gameRules = new JTextArea();
        gameRules.setLineWrap(true);
        gameRules.setWrapStyleWord(true);
        gameRules.setFont(new Font("Comic Sans", Font.ITALIC, 15));
        gameRules.setEditable(false);
        gameRules.setText("The rules are simple: \n  - Choose how many questions you want to calculate \n " +
                "- Do it as fast as possible! \n Are you ready? Let's go!");


        JPanel gameOptionsPanel = new JPanel();
        gameOptionsPanel.setLayout(new GridLayout(3, 1));
        add(gameRules);
        numberSlider = new JSlider(1, 100, 50);
        numberSlider.setPaintTicks(true);
        numberSlider.setPaintLabels(true);
        numberSlider.setMajorTickSpacing(10);
        numberSlider.addChangeListener(this::stateChanged);

        sliderLabel = new JLabel(numberSlider.getValue() + " questions", SwingConstants.CENTER);
        sliderLabel.setFont(new Font("Comic Sans", Font.ITALIC, 15));
        sliderLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        add(numberSlider);
        gameOptionsPanel.add(sliderLabel);

        startGameButton = new JButton("New Game");
        gameOptionsPanel.add(startGameButton);
        changeNameButton = new JButton("Change name");
        gameOptionsPanel.add(changeNameButton);

        add(gameOptionsPanel);


    }

    private void stateChanged(ChangeEvent e) {
        sliderLabel.setText((numberSlider.getValue() + " questions"));
    }

    public void setStartGameButton(ActionListener e) {
        startGameButton.addActionListener(e);
    }

    public void setChangeNameButton(ActionListener e){
        changeNameButton.addActionListener(e);
    }

    private void setWelcomeNameLabel(String welcomeNameLabel) {
        this.welcomeNameLabel.setText(welcomeNameLabel);
    }

    public int getAmountOfQuestions() {
        return numberSlider.getValue();
    }
}
