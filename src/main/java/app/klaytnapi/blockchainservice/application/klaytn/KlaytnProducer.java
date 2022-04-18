package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.Block;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnService;
import app.klaytnapi.blockchainservice.domain.klaytn.MessageBlockingQueue;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class KlaytnProducer implements Runnable {

    private final KlaytnService klaytnPublicNodeService;

    int lastedBlockNumber = 88719974;
    int currentBlockNumber = 88720810;

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
                    int timestamp = Integer.parseInt(block.getResult().get("timestamp").toString().replaceFirst("^0x",""),16);
                    for(Map transaction : transactions){
                        transaction.put("timestamp", timestamp);
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
