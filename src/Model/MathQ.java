package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;

public class MathQ {

    private String question;
    private int correctAnswer;
    private Random rand = new Random();

    private boolean isCorrectAnswered = false;


    public MathQ(String op){
        generateQ(op);
    }

    public void answerQuestion(int answer){
        if (answer == correctAnswer){
            isCorrectAnswered = true;
        }

    }

    public void generateQ(String op){

        int firstNumber = rand.nextInt(9)+2;
        int secondNumber = rand.nextInt(9)+2;
        switch (op) {
            case "*" -> {
                setQuestion(firstNumber + "×" + secondNumber);
                setAnswer(firstNumber * secondNumber);
            }
            case "+" -> {
                setQuestion(firstNumber + op + secondNumber);
                setAnswer(firstNumber + secondNumber);
            }
            case "-" -> {
                setQuestion(firstNumber + op + secondNumber);
                setAnswer(firstNumber - secondNumber);
            }
        }
    }


    private void setQuestion(String question) {
        this.question = question;
    }

    private void setAnswer(int answer) {
        this.correctAnswer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswered() {
        return isCorrectAnswered;
    }


}
