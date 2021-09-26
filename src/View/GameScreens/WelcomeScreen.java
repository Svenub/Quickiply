package View.GameScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WelcomeScreen extends JPanel {


    private JTextField playerTextField;
    private String welcomeString = "Please enter your name: ";
    private String nonValidPlayerName = "Non valid playername";
    private String playerName;
    private JButton submitButton;


    public WelcomeScreen() {
        setLayout(new GridLayout(3, 1));
        initWelcomePanel();
        setSubmitHoveringMess();


    }

    private void initWelcomePanel() {
        JLabel wLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        wLabel.setPreferredSize(new Dimension(100, 50));
        wLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        add(wLabel);


        JPanel submitPanel = new JPanel();
        submitButton = new JButton("Submit");
        add(submitPanel);
        setPreferredSize(new Dimension(400, 100));
        playerTextField = new JTextField(welcomeString);
        playerTextField.setPreferredSize(new Dimension(250, 50));
        submitPanel.setLayout(new FlowLayout());
        submitPanel.add(playerTextField);
        submitPanel.add(submitButton);

    }

    public boolean validPlayerName(){
        return !playerTextField.getText().equals(welcomeString) &&
                !playerTextField.getText().equals(nonValidPlayerName);
    }




    public void setSubmitButton(ActionListener e) {
        submitButton.addActionListener(e1 -> setWarningMessage());
        submitButton.addActionListener(e);


    }

    public void setPlayerNameFromTextField(){
        playerName = playerTextField.getText();

    }

    private void setWarningMessage(){
        if(!validPlayerName()){
            playerTextField.setText(nonValidPlayerName);

        } else{
            setPlayerNameFromTextField();
        }


    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public String getPlayerName() {
        return playerName;
    }

    public JTextField getPlayerTextField() {
        return playerTextField;
    }

    public void setSubmitHoveringMess(){
        playerTextField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playerTextField.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (playerTextField.getText().equals(welcomeString)) {
                    playerTextField.setText("Bu!");
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (playerTextField.getText().equals("Bu!") || playerTextField.getText().equals("")) {
                    playerTextField.setText(welcomeString);
                }

            }
        });
    }
}
