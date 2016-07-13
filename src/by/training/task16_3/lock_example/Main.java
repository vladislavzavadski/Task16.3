package by.training.task16_3.lock_example;

/**
 * Created by vladislav on 13.07.16.
 */
/*
* Задача. Ребята зимой катаются на горке.
* В один момент с горки может съезжать не более 1-го
* человека.*/
public class Main {
    private static final int CHILD_NUMBER = 30;
    public static void main(String[] args){
        Hill hill = new Hill();
        for(int i=0; i<CHILD_NUMBER; i++){
            new Thread(new Child("Ребенок-"+(i+1), hill)).start();
        }
    }
}
