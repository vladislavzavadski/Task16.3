package by.training.task16_3.read_write_lock_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by vladislav on 14.07.16.
 */
public class Buffer {
    private List<String> messages = new ArrayList<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();//создаем общую блокировку для читателя и писателя
    
    public void addMessage(String message){
        messages.add(message);
    }
    
    public ReadWriteLock getLock(){
        return readWriteLock;
    }
    
    public List<String> getMessages(){
        return messages;
    }
}
