package by.training.task16_3.condition_example;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Reader implements Runnable {
    private Buffer buffer;
    private volatile boolean isRunning = true;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (isRunning) {
            Lock lock = null;
            try {
                lock = buffer.getLock();
                lock.lock(); //заходим в критическую секцию
                if (buffer.isEmpty()) {//если буффер пуст
                    Condition condition = buffer.getCondition();
                    System.out.println("Буффер оказался пуст, ждём пока писатель не отправит сообщение...");
                    condition.await();//позволяем потоку-писателю войти в критическую секцию, при этом
                    // поток-читатель ждет, пока писатель не положит что-нибудь в буффер.

                }
                List<String> messages = buffer.getMessages();
                System.out.println("Читатель получил сообщение:");
                Iterator<String> iterator = messages.iterator();

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
