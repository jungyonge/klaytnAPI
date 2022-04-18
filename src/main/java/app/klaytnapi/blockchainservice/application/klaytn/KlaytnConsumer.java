package app.klaytnapi.blockchainservice.application.klaytn;

import java.util.concurrent.ArrayBlockingQueue;

public class KlaytnConsumer implements Runnable {

    ArrayBlockingQueue<String> obj;

    public KlaytnConsumer(ArrayBlockingQueue<String> obj) {
        // accept an ArrayBlockingQueue object from
        // constructor
        this.obj = obj;
    }

    @Override
    public void run() {

        try {
            obj.put("1");
            System.out.println("Produced ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
