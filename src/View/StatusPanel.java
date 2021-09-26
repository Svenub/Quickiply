package View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class StatusPanel extends JPanel {

    private JLabel mainLabel;
    private JPanel infoPanel;
    private JLabel statusLabel;

    private JPanel answerPanel;
    private JLabel questionsLeft;
    private JLabel correctAnswers;

    public StatusPanel() {
        setLayout(new GridLayout(3,1));
        setBackground(new Color(1, 117, 96));

        mainLabel = new JLabel("Status:", SwingConstants.CENTER);
        mainLabel.setBackground(new Color(1, 117, 96));
        mainLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        mainLabel.setOpaque(true);
        add(mainLabel);


        initPanels();
        initLabels();
    }

    private void initPanels() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(new Color(20, 117, 96));
        infoPanel.setPreferredSize(new Dimension(50,100));
        infoPanel.setBorder(BorderFactory.createLoweredBevelBorder());

        statusLabel = new JLabel("", SwingConstants.CENTER);
        answerPanel = new JPanel();
        statusLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        answerPanel.setLayout(new FlowLayout());
        answerPanel.setBackground(new Color(1, 117, 96));


        add(infoPanel);
        add(answerPanel);

        infoPanel.add(statusLabel, BorderLayout.CENTER);

    }

    private void initLabels(){
        questionsLeft = new JLabel("Answers left:");
        correctAnswers = new JLabel("Questions left: ");
        questionsLeft.setFont(new Font("Serif", Font.PLAIN, 15));
        correctAnswers.setFont(new Font("Serif", Font.PLAIN, 15));

        answerPanel.add(questionsLeft);
        answerPanel.add(correctAnswers);
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel.setText(statusLabel);
    }

    public void setQuestionsLeft(String questionsLeft) {
        this.questionsLeft.setText(questionsLeft);
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers.setText(correctAnswers);
    }
}
