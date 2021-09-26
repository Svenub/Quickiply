package View.GameScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOverScreen extends JPanel {

    private final JLabel congratsLabel = new JLabel("Congratulations!",SwingConstants.CENTER);
    private final JTextArea statistics = new JTextArea();
    private JButton tryagainButton = new JButton("Please try again!");
    private int questions = 0;
    private String time = "";
    private String score = "";
    private double speed = 0;



    public GameOverScreen(){

        this.setLayout(new BorderLayout());
        add(congratsLabel, BorderLayout.NORTH);
        add(statistics, BorderLayout.CENTER);

        add(tryagainButton, BorderLayout.SOUTH);
        initLabels();


    }

    private void initLabels(){
        congratsLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        congratsLabel.setBorder(BorderFactory.createRaisedBevelBorder());


        tryagainButton.setFont(new Font("Serif", Font.PLAIN, 20));
        tryagainButton.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void initStatistics(int questions, String time){
        statistics.setLineWrap(true);
        statistics.setWrapStyleWord(true);
        statistics.setFont(new Font("Comic Sans",Font.ITALIC,20));
        statistics.setEditable(false);
        double speed = questions / getTotalSecondsFromTime(time);
        double score = Math.round(questions*speed*100);
        statistics.setText("You manage to finish " + questions + " question(s)" + " and your time was: " + time + "! \n" +
                "That equals the speed of " + speed  +" questions/second! \n \n" +
                "You got the score: " + score);
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
    public void setTryagainButton(ActionListener e){
        tryagainButton.addActionListener(e);
    }


    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
