package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.MessageBlockingQueue;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KlaytnConsumer implements Runnable {


    @Override
    public void run() {

        while (true){
            Map transaction = null;
            try {
                if(!MessageBlockingQueue.queue.isEmpty()){
                    transaction = MessageBlockingQueue.queue.poll(1, TimeUnit.SECONDS);
                    System.out.println("Transaction : " + transaction.get("hash") );
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
