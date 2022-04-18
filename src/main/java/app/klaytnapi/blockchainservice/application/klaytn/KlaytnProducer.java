package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.Block;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnService;
import app.klaytnapi.blockchainservice.domain.klaytn.MessageBlockingQueue;
import app.klaytnapi.blockchainservice.domain.klaytn.Transaction;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;

public class KlaytnProducer implements Runnable {

    private final KlaytnService klaytnPublicNodeService;


    public KlaytnProducer(
            @Qualifier("klaytnPublicNodeServiceImpl")KlaytnService klaytnPublicNodeService) {

        this.klaytnPublicNodeService = klaytnPublicNodeService;
    }

    @Override
    public void run() {

        int lastedBlockNumber = 1;
        int currentBlockNumber = 10;
        try {

            while (lastedBlockNumber >= currentBlockNumber){

                Block block = klaytnPublicNodeService.getBlockByNumber("0x" + Integer.toHexString(lastedBlockNumber));
                ArrayList<Map> transactions = (ArrayList<Map>) block.getResult().get("transactions");
                if(!transactions.isEmpty()){
                    for(Map transaction : transactions){
                        MessageBlockingQueue.queue.put(transaction);
                    }
                }
                lastedBlockNumber++;
            }

            System.out.println("Produced ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
