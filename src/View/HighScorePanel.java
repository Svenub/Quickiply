package View;

import Model.HighscoreTable.Score;
import Model.Model;

import javax.swing.*;
import java.awt.*;

public class HighScorePanel extends JPanel {

    private JLabel topLabel;
    private JTextArea textArea;
    private Model model;

    public HighScorePanel(Model model){
        this.model = model;
        topLabel = new JLabel("HIGHSCORES:");
        textArea = new JTextArea();

        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Comic Sans",Font.ITALIC,15));
        textArea.setEditable(false);
        textArea.setOpaque(false);
        topLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        add(topLabel);
        add(textArea);
        setPreferredSize(new Dimension(200,50));
        readHighScore();
    }


    public void readHighScore(){
        StringBuilder highscore = new StringBuilder();
        for (Score score : model.getHighscoreList()){
           highscore.append(score.toString()).append("\n");
        }
        textArea.setText(highscore.toString());


    }

}
