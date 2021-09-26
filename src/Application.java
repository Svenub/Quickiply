import Controller.Controller;
import Model.Model;
import View.MainGUI;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;



public class Application {

    private static Model model;

    static {
        try {
            model = new Model();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static MainGUI mainGUI;

    static {
        try {
            mainGUI = new MainGUI(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Controller controller = new Controller(model, mainGUI);
    private static Scanner sc = new Scanner(System.in);




    public static void main(String[] args) {
        model.getPublisher().addObserver(mainGUI);
        model.getPublisher().addObserver(controller);



    }



}
