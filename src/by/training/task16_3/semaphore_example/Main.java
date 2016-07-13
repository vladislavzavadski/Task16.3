package by.training.task16_3.semaphore_example;


/**
 * Created by vladislav on 12.07.16.
 */
public class Main {
    private static final int BURNER_NUMBER = 4;
    public static void main(String[] args){

        Kitchen kitchen = new Kitchen(BURNER_NUMBER);

        for(int i=0; i<20; i++){
            Thread thread = new Thread(new Student(kitchen));
            thread.setName("Студент-"+(i+1));
            thread.start();
        }
    }
}
