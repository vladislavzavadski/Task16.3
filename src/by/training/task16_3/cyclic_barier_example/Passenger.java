package by.training.task16_3.cyclic_barier_example;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

/**
 * Created by vladislav on 12.07.16.
 */
public class Passenger implements Runnable {
    private MiniBus miniBus;

    public Passenger(MiniBus miniBus) {
        this.miniBus = miniBus;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            Thread.sleep((random.nextInt(10)+1)*1000);
            miniBus.sitDown(this);//пассажиры через случайный промежуток времени садятся в маршрутку.
        } catch (InterruptedException|BrokenBarrierException e) {
            System.out.println("Произошла ошибка и пассажир уничтожен");
            e.printStackTrace();
        }
    }
}
