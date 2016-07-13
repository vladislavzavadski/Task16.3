package by.training.task16_3.count_down_latch_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by vladislav on 12.07.16.
 */
/*
* Задача гонки.*/
public class Main {
    private static final int CARS_NUMBER = 20;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1); //данный объект создается для синхронизации старта машин.(все должны стартануть одновременно)
        CountDownLatch finish = new CountDownLatch(CARS_NUMBER);//данный объект для синхронизации финиша. Пока все машины не пересекут финишную черту, не выводим результаты гонки.
        List<Car> cars = new ArrayList<>();
        for(int i=0; i<CARS_NUMBER; i++){
            cars.add(new Car(start, finish, "Car-"+(i+1)));
        }
        new Race(start, finish, cars).startRace();//объявляем о начале гонки
    }
}
