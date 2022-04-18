package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransactionRepository;
import app.klaytnapi.blockchainservice.domain.klaytn.MessageBlockingQueue;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class KlaytnConsumer implements Runnable {

    private final KlaytnTransactionRepository klaytnTransactionRepository;

    @Override
    public void run() {

        while (true){
            Map transaction = null;
            try {
                if(!MessageBlockingQueue.queue.isEmpty()){
                    transaction = MessageBlockingQueue.queue.poll(1, TimeUnit.SECONDS);
                    KlaytnTransaction klaytnTransaction = KlaytnTransaction.create(transaction.get("hash").toString(), transaction.get("type").toString(),
                            (Integer) transaction.get("timestamp"));
                    klaytnTransactionRepository.save(klaytnTransaction);
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
