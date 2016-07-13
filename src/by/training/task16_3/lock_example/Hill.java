package by.training.task16_3.lock_example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Hill {
    private Lock lock = new ReentrantLock();

    public boolean comeDown(Child child) throws InterruptedException {
        System.out.println("Ребенок "+child.getName()+" хочет съехать с горки.");
        if(lock.tryLock(100, TimeUnit.MILLISECONDS)){//каждый из детей ждёт 100 миллисекунд, чтобы спуститься с горки
            System.out.println("Ребенок "+child.getName()+" едет с горки.");
            Thread.sleep(50);//едем с горки
            lock.unlock();//спустились с горки, и даём возможность кому-то еще спуститься с горки.
            return true;
        }else {

            return false;
        }
    }
}
