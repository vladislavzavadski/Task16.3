package by.training.task16_3.count_down_latch_example;


import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by vladislav on 12.07.16.
 */
public class Race {
    private CountDownLatch start;
    private CountDownLatch finish;
    private List<Car> carList;

    public Race(CountDownLatch start, CountDownLatch finish, List<Car> carList){
        this.start = start;
        this.finish = finish;
        this.carList = carList;
    }


    public void startRace() throws InterruptedException {
        System.out.println("Машины подъезжают к старту.");
        carList.forEach((car -> new Thread(car).start()));//создаем и запускаем потоки машин. (но все они ждут команды старт)
        System.out.println("Все машины готовы. Гонка начнется через...");
        for (int i=3; i>0; i--){
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("Старт!!!");
        start.countDown();//объявляем о старте гонки, (все потоки машин продолжили выполнение).
        finish.await();//Ожидаем пока все машины не приедут к финишу.
        System.out.println("Гонка завершена. Результаты:");
        List<Car> places = Car.getPlaces();
        int i=1;
        for(Car car:places){
            System.out.println(i+") "+ car.getName()); //выводим результаты на экран.
            i++;
        }
    }
}
