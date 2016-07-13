package by.training.task16_3.read_write_lock_example;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Main {
    private static final int WRITERS_NUMBER = 5;
    public static void main(String[] args) throws InterruptedException {
        List<String> buffer = new ArrayList<>();
        List<Writer> writers = new LinkedList<>();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Reader reader = new Reader(readWriteLock, buffer);

        for(int i=0; i<WRITERS_NUMBER; i++){
            Writer writer = new Writer(buffer, readWriteLock);
            writers.add(writer);
            Thread thread = new Thread(writer);
            thread.setName("Writer-"+(i+1));
            thread.start();
        }

        new Thread(reader).start();
        Thread.sleep(10000);
        Iterator<Writer> iterator = writers.iterator();
        while (iterator.hasNext()){
            iterator.next().stop();
            iterator.remove();
        }

        reader.stop();


    }
}
