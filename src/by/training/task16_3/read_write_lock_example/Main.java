package by.training.task16_3.read_write_lock_example;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vladislav on 13.07.16.
 */
public class Main {
    private static final int WRITERS_NUMBER = 5;
    public static void main(String[] args) throws InterruptedException {
        List<Writer> writers = new LinkedList<>();
        Buffer buffer = new Buffer();
        Reader reader = new Reader(buffer);

        for(int i=0; i<WRITERS_NUMBER; i++){//создаем писателей
            Writer writer = new Writer(buffer);
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
