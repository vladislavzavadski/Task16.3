package by.training.task16_3.phaser_example;

import java.util.concurrent.Phaser;

/**
 * Created by vladislav on 13.07.16.
 */
public class Student implements Runnable {
    private Phaser phaser;
    private int startTerm;
    private int endTerm;
    private String name;

    public int getStartTerm() {
        return startTerm;
    }



    public Student(Phaser phaser, String name, int startTerm, int endTerm){
        this.phaser = phaser;
        this.name = name;
        this.startTerm = startTerm;
        this.endTerm = endTerm;
    }

    @Override
    public void run() {
        System.out.println("Студент " + name + " начал обучение. C "+startTerm+" семестра.");
        while (phaser.getPhase()+1!=endTerm){ //пока сейчас не тот семестр, в котором студента должны отчислить, студент учится
            phaser.arriveAndAwaitAdvance();//сообщает о том что он завершил семестр и можно идти дальше.
            //семестр для всех студентов заканчивается одновременно.
        }
        //наступил момент, и студента должны отчислить
        System.out.println("Студент "+name+" закончил учится. В конце "+(endTerm-1)+" семестра.");
        phaser.arriveAndDeregister(); //студент удаляется как участник phaser.
        // (То есть говорит, что его не нужно ждать в конце следующего семестра)
    }
}
