package by.training.task16_3.condition_example;


/**
 * Created by vladislav on 13.07.16.
 */
/*
* Задача читатель-писатель
* Здесь разделяемым ресурсом является общий буффер.*/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();

        Writer writer = new Writer(buffer);
        Reader reader = new Reader(buffer);

        new Thread(writer).start();
        new Thread(reader).start();

        Thread.sleep(20000);

        reader.stop();
        Thread.sleep(1000);//ожидаем 1 секунду, на случай, если в момент остановки читатель находится в ожидании сообщения от писателя,
        //чтоб за эту секунду писатель успел что-нибудь отправить
        writer.stop();

    }
}
