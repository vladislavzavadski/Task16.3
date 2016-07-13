package by.training.task16_3.cyclic_barier_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by vladislav on 12.07.16.
 */
public class MiniBus implements Runnable {
    private List<Passenger> passengers = new ArrayList<>();
    private CyclicBarrier cyclicBarrier;
    private int busSize;

    public MiniBus(int busSize){
        this.busSize = busSize;
        cyclicBarrier = new CyclicBarrier(busSize, this);//создаем барьер, который запустит метод run данного объекта в новом потоке.
    }

    @Override
    public void run() {
        System.out.println("Маршрутка уехала. И увезла "+passengers.size()+" пассажиров");
    }

    public void sitDown(Passenger passenger) throws BrokenBarrierException, InterruptedException {
        synchronized (passenger){
            passengers.add(passenger);//добавляем пассажира в список пассажиров маршрутки.
            System.out.println("Пассажир "+Thread.currentThread().getName()+" сел в маршрутку. В машине "+(busSize-passengers.size())+ "свободных мест.");
        }

        cyclicBarrier.await(); //ждём пока в маршрутку не сядет busSize пассажиров
    }

}
