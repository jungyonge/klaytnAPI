package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.Block;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnService;
import app.klaytnapi.blockchainservice.domain.klaytn.MessageBlockingQueue;
import app.klaytnapi.blockchainservice.domain.klaytn.Transaction;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class KlaytnProducer implements Runnable {

    private final KlaytnService klaytnPublicNodeService;

    int lastedBlockNumber = 1;
    int currentBlockNumber = 100000;

    public KlaytnProducer(
            @Qualifier("klaytnPublicNodeServiceImpl")KlaytnService klaytnPublicNodeService) {

        this.klaytnPublicNodeService = klaytnPublicNodeService;
    }

    @Override
    public void run() {


        try {

            while (true){

                System.out.println("blocknumber: " + lastedBlockNumber);
                Block block = klaytnPublicNodeService.getBlockByNumber("0x" + Integer.toHexString(lastedBlockNumber));
                ArrayList<Map> transactions = (ArrayList<Map>) block.getResult().get("transactions");
                if(!transactions.isEmpty()){
                    for(Map transaction : transactions){
                        System.out.println(transaction);
                        MessageBlockingQueue.queue.put(transaction);
                    }
                }
                if(lastedBlockNumber == currentBlockNumber){
                    break;
                }
                lastedBlockNumber++;
            }

            System.out.println("Produced ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
