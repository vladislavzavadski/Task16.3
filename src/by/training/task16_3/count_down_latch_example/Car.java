package by.training.task16_3.count_down_latch_example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by vladislav on 12.07.16.
 */
public class Car implements Runnable {
    private CountDownLatch start;
    private CountDownLatch finish;
    private String name;
    private static List<Car> places = new ArrayList<>();

    public Car(CountDownLatch start, CountDownLatch finish, String name){
        this.start = start;
        this.finish = finish;
        this.name = name;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            start.await();//машины ожидают команды старт
            Thread.sleep((random.nextInt(10)+1)*1000); //случайное время едут до финиша
            finish.countDown(); // пересекают финишную черту
            synchronized (places){
                places.add(this); //машина добавляется в список финишировавших
            }
        } catch (InterruptedException e) {
            System.out.println(name+" выбыл из гонки.");
            e.printStackTrace();
        }
    }

    public static List<Car> getPlaces(){
        return places;
    }

    public String getName() {
        return name;
    }
}
