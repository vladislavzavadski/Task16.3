package by.training.task16_3.condition_example;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Reader implements Runnable {
    private Lock lock;
    private Condition bufferIsEmpty;
    private List<String> buffer;
    private volatile boolean isRunning = true;

    public Reader(Lock lock, Condition bufferIsEmpty, List<String> buffer) {
        this.lock = lock;
        this.bufferIsEmpty = bufferIsEmpty;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                lock.lock(); //заходим в критическую секцию
                if (buffer.isEmpty()) {//если буффер пуст
                    System.out.println("Буффер оказался пуст, ждём пока писатель не отправит сообщение...");
                    bufferIsEmpty.await();//позволяем потоку-писателю войти в критическую секцию, при этом
                    // поток-читатель ждет, пока писатель не положит что-нибудь в буффер.

                }
                System.out.println("Читатель получил сообщение:");
                Iterator<String> iterator = buffer.iterator();

                while (iterator.hasNext()) {
                    System.out.println(iterator.next());//извлекаем все сообщения из буффера
                    iterator.remove();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {

                lock.unlock();//выходим из критической секции, позволяя писателю записать данные в буффер.
            }
        }
    }

    public void stop(){
        isRunning = false;
    }
}
