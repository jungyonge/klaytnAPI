package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.MessageBlockingQueue;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class KlaytnConsumer implements Runnable {


    public KlaytnConsumer() {
    }

    @Override
    public void run() {

        while (MessageBlockingQueue.queue.isEmpty()){
            Map transaction = null;
            try {
                transaction = MessageBlockingQueue.queue.poll(1, TimeUnit.SECONDS);
                System.out.println("Transaction : " + transaction.get("hash") );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
