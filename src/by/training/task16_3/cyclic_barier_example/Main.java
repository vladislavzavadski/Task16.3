package by.training.task16_3.cyclic_barier_example;


/**
 * Created by vladislav on 12.07.16.
 */
/*
* Задача маршрутка. Маршрутка не уедет пока наберется BUS_SIZE пассажиров*/
public class Main {
    private static final int BUS_SIZE = 20;

    public static void main(String[] args){
        MiniBus miniBus = new MiniBus(BUS_SIZE);

        for(int i=0; i<BUS_SIZE; i++){
            Thread thread = new Thread(new Passenger(miniBus)); //создаем пассажиров и даем им ссылку на маршрутку.
            thread.setName("Passenger-"+(i+1));
            thread.start();
        }
    }
}
