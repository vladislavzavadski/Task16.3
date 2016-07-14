package by.training.task16_3.condition_example;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Writer implements Runnable {
    private Buffer buffer;
    private volatile boolean isRunning = true;

    public Writer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int i=1;
        while (isRunning) {
            try {
                Lock lock = buffer.getLock();//берем блокировку буффера
                Condition condition = buffer.getCondition();
                lock.lock();//входим в критическую секцию
                String message = "Message-"+i++;
                buffer.addMessage(message);//записываем сообщение в буффер.
                condition.signal();//оповещаем читателя, что он может прочитать данные
                System.out.println("Писатель отправил сообщение: "+message);
                lock.unlock();//выходим изкритической секции.
                Thread.sleep(1000);
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
