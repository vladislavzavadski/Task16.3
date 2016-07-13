package by.training.task16_3.lock_example;

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
                if (!hill.comeDown(this)){
                    System.out.println("Ребенок "+name+" не дождался освобождения горки и ушел домой.");
                    return;
                }else {
                    System.out.println("Ребенок "+name+" спустился с горки.");
                    Thread.sleep(75);//время на подъём на горку.
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
