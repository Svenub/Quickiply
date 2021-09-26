package Model;

import java.util.ArrayList;
import java.util.List;

public class Publisher {


    private List<Observer> observers = new ArrayList<>();





    public void addObserver(Observer o) {
        observers.add(o);
    }
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    public List<Observer> getObservers() {
        return observers;
    }

    public void notifyObserver() {
        for (Observer o : observers) {
            o.notifyOb();
        }
    }
    public void eventUpdate(){
        for (Observer o : observers) {
            o.eventUpdate();
        }
    }
}