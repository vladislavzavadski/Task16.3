package by.training.task16_3.semaphore_example;

import java.util.concurrent.Semaphore;

/**
 * Created by vladislav on 12.07.16.
 */
public class Kitchen {
    private Semaphore semaphore;
    private int burnerNumber;
    public Kitchen (int burnerNumber){
        this.burnerNumber = burnerNumber;
        semaphore = new Semaphore(burnerNumber);
    }

    public void cook(int dishesNumber) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" хочет приготовить "+dishesNumber+" блюда.");
        semaphore.acquire(dishesNumber);//если студенту недостаточно свободных комфорок, чтобы приготовить свои блюда, то ждет пока не освободится нужное количество комфорок
        System.out.println(Thread.currentThread().getName()+" готовит "+dishesNumber+" блюда.");//если есть свободные комфорки, то студент готовит
        Thread.sleep(3000);
        semaphore.release(dishesNumber);//когда студент закончил готовить, освобождает все занятые им комфорки.
        System.out.println(Thread.currentThread().getName()+" закончил готовить.");
    }

    public int getBurnerNumber(){
        return burnerNumber;
    }
}
