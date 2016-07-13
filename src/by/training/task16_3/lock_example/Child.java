package by.training.task16_3.lock_example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by vladislav on 13.07.16.
 */
public class Child implements Runnable {

    private String name;
    private Hill hill;

    public Child(String name, Hill hill) {
        this.name = name;
        this.hill = hill;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {//каждый из детей хочет съехать с горки по 10 раз
                System.out.println("Ребенок "+name+" хочет съехать с горки.");
                Lock lock = hill.getLock();//получаем блокировку для горки
                if (lock.tryLock(100, TimeUnit.MILLISECONDS)){//пробуем получить доступ к горке в течение 100 миллисекунд, в противном случае ребенок идёт домой
                    hill.comeDown(this);//непосредственно едем с горки
                    System.out.println("Ребенок "+name+" спустился с горки.");
                    lock.unlock();//сообщаем об окончании спуска, теперь кто-то другой может съехать с горки.
                    Thread.sleep(75);//время на подъём на горку.
                }else {
                    System.out.println("Ребенок "+name+" не дождался освобождения горки и ушел домой.");
                    return;
                }

            }
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }

}
