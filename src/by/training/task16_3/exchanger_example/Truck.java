package by.training.task16_3.exchanger_example;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * Created by vladislav on 12.07.16.
 */
public class Truck implements Runnable {
    private Exchanger<String> exchanger;
    private String parcel;
    private String sourceAddress;
    private String destinationAddress;

    public Truck(String parcel, String sourceAddress, String destinationAddress, Exchanger<String> exchanger) {
        this.parcel = parcel;
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        try {
            Random random = new Random();
            System.out.println("Грузовик "+Thread.currentThread().getName()+" загруженный посылкой: " +
                    parcel+" отправился из пункта "+sourceAddress + " в пункт "+destinationAddress);
            Thread.sleep(1000*(random.nextInt(10)+1));//случайное время грузовик едет до точки перегрузки.
            System.out.println("Грузовик "+Thread.currentThread().getName()+" приехал в пункт перегрузки.");

            parcel = exchanger.exchange(parcel);//здесь поток останавливается, и ждёт пока другой поток вызовет метод exchange

            System.out.println("Грузовик "+Thread.currentThread().getName()+" загрузил новую посылку и продолжил путь.");
            Thread.sleep(1000*(random.nextInt(10)+1));//случайное время грузовик едет от точки перегрузки к точке назначения
            System.out.println("Грузовик "+Thread.currentThread().getName()+" приехал в пункт "+destinationAddress+" и привез посылку: "+parcel);
        } catch (InterruptedException e) {
            System.out.println("Что-то пошло не так и грузови "+Thread.currentThread().getName()+" был уничтожен.");
        }
    }
}
