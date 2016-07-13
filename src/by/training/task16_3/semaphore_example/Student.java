package by.training.task16_3.semaphore_example;

import java.util.Random;

/**
 * Created by vladislav on 12.07.16.
 */
public class Student implements Runnable {
    private Kitchen kitchen;

    public Student(Kitchen kitchen){
        this.kitchen = kitchen;
    }

    @Override
    public void run() {
        Random random = new Random();
        for(int i=0; i<2; i++){
            try {
                int dishNumber = random.nextInt(kitchen.getBurnerNumber())+1;//студенту захотелось приготовить несколько блюд
                kitchen.cook(dishNumber);//студент идёт на кухню
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
