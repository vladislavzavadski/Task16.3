package by.training.task16_3.condition_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vladislav on 14.07.16.
 */
public class Buffer {
    private List<String> messages = new ArrayList<>();
    private Lock lock = new ReentrantLock();//создаем общую блокировку для читателя и писателя
    private Condition condition = lock.newCondition();
    
    public void addMessage(String message){
        messages.add(message);
    }
    
    public Lock getLock(){
        return lock;
    }
    
    public List<String> getMessages(){
        return messages;
    }

    public Condition getCondition(){return condition;}

    public boolean isEmpty(){
        return messages.isEmpty();
    }
}
