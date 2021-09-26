package View.GameScreens;

import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class QuestionScreen extends JPanel implements KeyListener {

    private JButton enterAnswer = new JButton("Enter/press Enterkey");


    private JLabel questionLabel;
    private JTextField answerField;
    private String answer;
    private JLabel timerLabel;
    private Model model;

    public QuestionScreen(Model model){
        this.model = model;
        setLayout(new BorderLayout());
        initQuestionScreen();
    }

    private void initQuestionScreen() {
        questionLabel = new JLabel();
        timerLabel = new JLabel();

        answerField = new JTextField();
        answerField.requestFocusInWindow();
        answerField.addKeyListener(this);
        answerField.setFont(new Font("Serif", Font.PLAIN, 27));

        add(questionLabel, BorderLayout.NORTH);
        add(answerField, BorderLayout.CENTER);
        add(enterAnswer, BorderLayout.EAST);
        add(timerLabel, BorderLayout.SOUTH);

        setLabelLayout();
    }

    private void setLabelLayout() {
        timerLabel.setPreferredSize(new Dimension(200, 100));
        timerLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        questionLabel.setPreferredSize(new Dimension(200, 100));
        questionLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        questionLabel.setHorizontalAlignment(JLabel.CENTER);


        answerField.setSize(new Dimension(50, 50));
    }

    public void setEnterAnswer(ActionListener e1) {
        enterAnswer.addActionListener(e1);
        enterAnswer.addActionListener(e2 -> {
            String newA = answerField.getText();
            setAnswer(newA);
            answerField.setText("");
        });


    }

    private void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {          // FULHACK
        if (e.getKeyCode() == 10) {
            String newA = answerField.getText();
            setAnswer(newA);
            try
            {
                model.gameRunning(Integer.parseInt(newA));
            } catch (NumberFormatException | IOException ignored){
                model.setInvalidInput(true);
                model.getPublisher().eventUpdate();
            }

            answerField.setText("");

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setQuestionLabel(String questionLabel) {
        this.questionLabel.setText(questionLabel);
    }


    public void setTimerLabel(String timerLabel) {
        this.timerLabel.setText(timerLabel);
    }
}
