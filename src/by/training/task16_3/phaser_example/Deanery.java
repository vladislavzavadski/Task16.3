package by.training.task16_3.phaser_example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * Created by vladislav on 12.07.16.
 */
/*
* Программы эмулирует обучение в университете.
* Студент может начать учится не обязательно с 1-го семестра (например он мог перевестись с другого вуза)
* Заканчивает учится тоже не обязательно в последнем семестре.*/
public class Deanery {//деканат
    private static final int STUDENTS_NUMBER = 30;
    private static final int TERMS_NUMBER = 10;
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(1);//создаем phaser с 1-м участником (деканат)
        Random random = new Random();
        List<Student> students = new ArrayList<>();

        for (int i=0; i<STUDENTS_NUMBER; i++){
            int startTerm = random.nextInt(TERMS_NUMBER)+1; //случайно выбираем семестр, в котором студент начнет учится.
            int endTerm = random.nextInt(TERMS_NUMBER+1-startTerm)+startTerm+1;   //выбираем семестр в начале которого студента закончит учится
            // (если студент отучился до конца, то это 11 семестр)
            students.add(new Student(phaser, "Студент-"+(i+1), startTerm, endTerm));
        }

        for(int i=1; i<11; i++){ //перечислям наши семестры (с 1-го по 10-ый)
            for(Student student : students){
                if(student.getStartTerm()==phaser.getPhase()+1){ //если в этом семестре студент зачислен
                    phaser.register();//жобавляем его как участника phaser
                    new Thread(student).start();//запускаем поток студента (начинаем его убучение)
                }
            }
            phaser.arriveAndAwaitAdvance(); //здесь деканат заканчивает семестр и ждет пока все суденты закончат семестр.
            Thread.sleep(1000);
        }
    }


}
