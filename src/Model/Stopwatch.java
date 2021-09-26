package Model;

import javax.swing.*;

public class Stopwatch {


    private int delay = 1000;
    private int elapsedTime = 0;
    private int miliseconds = 0;
    private int seconds = 0;
    private int minutes = 0;
    private int countDown = 0;



    private Timer stopwatch = new Timer(delay, e -> {

        if(isCountingDown()){
            countDown --;
        } else{
            elapsedTime += 1000;
            // miliseconds = elapsedTime*4 % 99;
            seconds = (elapsedTime / 1000) % 60;
            minutes = (elapsedTime/60000) % 60;

        }

    });


    public int getElapsedTime() {
        return elapsedTime;
    }

    public void stop(){
        stopwatch.stop();

    }

    public void start(){
        stopwatch.start();
    }

    public void startWithCountdown(int countDown){
        this.countDown = countDown;
        stopwatch.start();
    }

    public void reset(){
        seconds = 0;
        minutes = 0;
        elapsedTime = 0;
    }

    public boolean isRunning(){
        return stopwatch.isRunning();
    }

    public String getTime(){
        // return String.format("%02d",minutes) + ":" + String.format("%02d",seconds) + ":" + String.format("%02d",miliseconds)
        return String.format("%02d",minutes) + ":" + String.format("%02d",seconds);
    }

    public int getCountDown() {
        return countDown;
    }

    public boolean isCountingDown(){
        return countDown != 0;
    }
}
