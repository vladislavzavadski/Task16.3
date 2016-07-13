package by.training.task16_3.read_write_lock_example;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Reader implements Runnable {
    private ReadWriteLock readWriteLock;
    private List<String> buffer;
    private volatile boolean isRunning = true;

    public Reader(ReadWriteLock readWriteLock, List<String> buffer) {
        this.readWriteLock = readWriteLock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                Thread.sleep(200);
                readWriteLock.readLock().lock();//берем блокировку (в качестве читателя) и входим в КС
                System.out.println("Читатель начинает читать сообщения: ");
                Iterator<String> iterator = buffer.iterator();
                while (iterator.hasNext()){
                    System.out.println(iterator.next()); //извлекаем
                    iterator.remove(); // и удаляем из буффера все сообщения, которые накопились со времени последнего чтения
                }
                readWriteLock.readLock().unlock();//отдаем блокировку и выходим из КС, тем самым позволяя писателям писать в буфер
                System.out.println("Читатель закончил чтение. ");
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
