package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.Block;
import app.klaytnapi.blockchainservice.domain.klaytn.MessageBlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class KlaytnProducer implements Runnable {

    ArrayBlockingQueue<String> obj;

    public KlaytnProducer(ArrayBlockingQueue<String> obj) {
        // accept an ArrayBlockingQueue object from
        // constructor
        this.obj = obj;
    }

    @Override
    public void run() {

        try {
            MessageBlockingQueue.queue.put();

            출처: https://aljjabaegi.tistory.com/536 [알짜배기 프로그래머]
            obj.put("1");
            System.out.println("Produced ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
