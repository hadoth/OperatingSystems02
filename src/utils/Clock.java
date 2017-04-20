package utils;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-03-23.
 */
public class Clock implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();
    private int time;
    private boolean isActive;

    public Clock(){
        this.time = 0;
        this.observerList.clear();
        this.isActive = false;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    @Override
    public boolean deleteObserver(Observer observer) {
        return this.observerList.remove(observer);
    }

    public void start(){
        this.isActive = true;
        while (this.isActive){
            for (int i = 0; i < this.observerList.size(); i++){
                this.observerList.get(i).update(time);
            }
            this.time++;
        }
    }

    public void stop(){
        this.isActive = false;
    }

    public void reset(){
        this.stop();
        this.time = 0;
    }

    public boolean isActive(){
        return this.isActive;
    }

    public int getTime() {
        return this.time;
    }
}
