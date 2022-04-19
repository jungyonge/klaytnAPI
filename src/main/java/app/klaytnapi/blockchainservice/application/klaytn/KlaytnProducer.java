package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockNumber;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockNumberRepository;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockStatus;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnService;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransactionQueue;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KlaytnProducer implements Runnable {

    private final KlaytnService klaytnPublicNodeService;
    private final KlaytnBlockNumberRepository klaytnBlockNumberRepository;

    int recentlyBlockNumber = 0;
    int currentParsingBlockNumber = 0;

    public KlaytnProducer(
            @Qualifier("klaytnPublicNodeServiceImpl") KlaytnService klaytnPublicNodeService,
            KlaytnBlockNumberRepository klaytnBlockNumberRepository) {

        this.klaytnPublicNodeService = klaytnPublicNodeService;
        this.klaytnBlockNumberRepository = klaytnBlockNumberRepository;
    }

    @Override
    @Transactional
    public void run() {


        Map recentlyKlaytnBlockNumber = klaytnPublicNodeService.getBlockNumber();

        KlaytnBlockNumber klaytnBlockNumber = klaytnBlockNumberRepository.getKlaytnBlockNumberByStatus(KlaytnBlockStatus.CURRENT_PARSING_BLOCK_NUMBER);

        try {
            while (true){
                System.out.println("blocknumber: " + currentParsingBlockNumber);
                KlaytnBlock klaytnBlock = klaytnPublicNodeService.getBlockByNumber("0x" + Integer.toHexString(currentParsingBlockNumber));
                ArrayList<Map> transactions = (ArrayList<Map>) klaytnBlock.getResult().get("transactions");
                if(!transactions.isEmpty()){
                    int timestamp = Integer.parseInt(
                            klaytnBlock.getResult().get("timestamp").toString().replaceFirst("^0x",""),16);
                    for(Map transaction : transactions){
                        transaction.put("timestamp", timestamp);
                        System.out.println(transaction);
                        KlaytnTransactionQueue.queue.put(transaction);
                    }
                }
                if(recentlyBlockNumber == currentParsingBlockNumber){
                    break;
                }
                currentParsingBlockNumber++;
            }

            System.out.println("Produced ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
