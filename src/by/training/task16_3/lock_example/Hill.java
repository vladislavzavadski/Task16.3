package by.training.task16_3.lock_example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Hill {
    private Lock lock = new ReentrantLock();

    public void comeDown(Child child) throws InterruptedException {
       System.out.println("Ребенок "+child.getName()+" едет с горки.");
       Thread.sleep(50);//едем с горки
    }

    public Lock getLock(){
        return lock;
    }
}
