package by.training.task16_3.read_write_lock_example;

import java.util.concurrent.locks.ReadWriteLock;

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
        while (isRunning){
            ReadWriteLock readWriteLock = buffer.getLock();//берем блокировку буффера
            readWriteLock.writeLock().lock();//заходим в критическую секцию
            System.out.println(Thread.currentThread().getName()+" начинает писать в буффер");
            buffer.addMessage(Thread.currentThread().getName()+" Message-"+i++); //пишем в буффер
            readWriteLock.writeLock().unlock(); //выходим из критической секции, теперь любой другой писатель может писать в буффер, или же читатель прочитать из буффера.
            System.out.println(Thread.currentThread().getName()+" закончил писать в буффер");
            try {
                Thread.sleep(100);
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
