package by.training.task16_3.condition_example;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Writer implements Runnable {
    private Lock lock;
    private Condition condition;
    private List<String> buffer;
    private volatile boolean isRunning = true;

    public Writer(Lock lock, Condition condition, List<String> buffer) {
        this.lock = lock;
        this.condition = condition;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int i=1;
        while (isRunning) {
            try {
                Thread.sleep(1000);
                lock.lock();//входим в критическую секцию
                String message = "Message-"+i++;
                buffer.add(message);//записываем сообщение в буффер.
                condition.signal();//оповещаем читателя, что он может прочитать данные
                System.out.println("Писатель отправил сообщение: "+message);
                lock.unlock();//выходим изкритической секции.
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

        }

    }

    public void stop(){
        isRunning = false;
    }
}
