package by.training.task16_3.condition_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vladislav on 13.07.16.
 */
/*
* Задача читатель-писатель
* Здесь разделяемым ресурсом является общий буффер.*/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<String> buffer = new ArrayList<>();

        Writer writer = new Writer(lock, condition, buffer);
        Reader reader = new Reader(lock, condition, buffer);

        new Thread(writer).start();
        new Thread(reader).start();

        Thread.sleep(20000);

        reader.stop();
        writer.stop();

    }
}
