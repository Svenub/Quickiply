package View;


import Model.Model;
import Model.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainGUI extends JPanel implements Observer {

    private JFrame gui;
    private GameScreenPanel gameScreenPanel;
    private StatusPanel statusPanel;
    private Model model;
    private HighScorePanel highScorePanel;
    private JPanel topPanel;

    private BufferedImage headImage;
    private ImageIcon headIcon = new ImageIcon("src/Utilities/quickiplyIcon.jpg");


    public MainGUI(Model model) throws IOException {
        this.model = model;
        gui = new JFrame();

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Quickiply");
        gui.setLayout(new BorderLayout());
        gui.setPreferredSize(new Dimension(700, 500));
        initAllPanelsForMainView();
        gui.pack();
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
        gui.setIconImage(headIcon.getImage());
    }

    private void loadImage() throws IOException {
        headImage = ImageIO.read(new File("src/Utilities/quickiply.jpg"));

    }


    private void initTopPanel() throws IOException {
        loadImage();
        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createRaisedBevelBorder());


        topPanel.setPreferredSize(new Dimension(100, 100));
        topPanel.setBackground(new Color(1, 117, 96));
        topPanel.setFont(new Font("Serif", Font.PLAIN, 50));
        topPanel.setForeground(Color.white);

        JLabel imageLAbel = new JLabel(new ImageIcon(headImage));
        topPanel.add(imageLAbel);

        gui.add(topPanel, BorderLayout.NORTH);


    }

    private void initBottomPanel() {
        JPanel bottomPanel = new JPanel();
        JLabel bottomLabel = new JLabel("Made by: Sven Kellgren", SwingConstants.RIGHT);

        bottomPanel.add(bottomLabel);
        bottomPanel.setBackground(new Color(11, 26, 26));

        bottomLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        bottomLabel.setForeground(Color.white);
        gui.add(bottomPanel, BorderLayout.SOUTH);
        gui.setFocusable(true);
    }

    private void initLeftPanel() {

        statusPanel = new StatusPanel();
        gui.add(statusPanel, BorderLayout.WEST);
        statusPanel.setPreferredSize(new Dimension(150, 50));


    }

    private void iniRightPanel() {

        highScorePanel = new HighScorePanel(model);

        gui.add(highScorePanel, BorderLayout.EAST);
        highScorePanel.setBackground(new Color(1, 117, 96));
    }

    private void initCenterPanel() {
        gameScreenPanel = new GameScreenPanel(model);
        gui.add(gameScreenPanel, BorderLayout.CENTER);

    }

    private void initAllPanelsForMainView() throws IOException {
        initTopPanel();
        initBottomPanel();
        initLeftPanel();
        iniRightPanel();
        initCenterPanel();


    }

    public GameScreenPanel getGamePanel() {
        return gameScreenPanel;
    }

    @Override
    public void notifyOb() {
        gui.validate();
        gui.repaint();

        setQuestionLabel(model.getCurrent_mathQ().getQuestion());
        setTimerLabel(model.getTime());
        setProgressInfo(String.valueOf(model.getRemainingQuestions()), String.valueOf(model.amountOfRightAnswer()));

    }

    @Override
    public void eventUpdate() {
        infoStatus(model.getInfo());
        highScorePanel.readHighScore();
    }

    private void setQuestionLabel(String question) {
        if (model.isCountingDown()) {
            gameScreenPanel.getQuestionScreen().setQuestionLabel(model.getCountdown());

        } else if (model.isStopwatchRunning()) {
            gameScreenPanel.getQuestionScreen().setQuestionLabel(question);
        }
    }

    private void setTimerLabel(String time) {
        if (model.isStopwatchRunning())
            gameScreenPanel.getQuestionScreen().setTimerLabel(time);
    }

    private void setProgressInfo(String questionsLeft, String correctAnswer) {
        statusPanel.setQuestionsLeft("Questions left: " + questionsLeft);
        statusPanel.setCorrectAnswers("Correct answers: " + correctAnswer);

    }

    private void infoStatus(String info) {
        statusPanel.setStatusLabel(info);
    }


}
