package by.training.task16_3.exchanger_example;

import java.util.concurrent.Exchanger;

/**
 * Created by vladislav on 12.07.16.
 */
/*
* 2 грузовика едут из 2-х разных пунктов в 2 разных пункта.
* Их пути пересекаются. В точке пересечения грузовики обмениваются грузами,
* и едут дальше до пункта назначения.*/
public class Main {

    public static void main(String[] args){
        Exchanger<String> exchanger = new Exchanger<>();//создаем объект, через который шрузовики будут обмениваться грузом
        new Thread(new Truck("Hello world", "Москва", "Вильнюс", exchanger)).start();
        new Thread(new Truck("Мама мыла раму", "Санкт-Питербург", "Варшава", exchanger)).start();
    }
}
