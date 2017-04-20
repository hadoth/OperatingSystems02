package utils;

/**
 * Created by Karol Pokomeda on 2017-03-14.
 */
public interface Observable {
    void addObserver(Observer observer);
    boolean deleteObserver(Observer observer);
}
