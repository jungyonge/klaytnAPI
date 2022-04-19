package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
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
    private final KlaytnBlockParsingHandler klaytnBlockParsingHandler;

    long recentlyBlockNumber = 0;
    long currentParsingBlockNumber = 0;

    public KlaytnProducer(
            @Qualifier("klaytnPublicNodeServiceImpl") KlaytnService klaytnPublicNodeService,
            KlaytnBlockNumberRepository klaytnBlockNumberRepository,
            KlaytnBlockParsingHandler klaytnBlockParsingHandler) {

        this.klaytnPublicNodeService = klaytnPublicNodeService;
        this.klaytnBlockNumberRepository = klaytnBlockNumberRepository;
        this.klaytnBlockParsingHandler = klaytnBlockParsingHandler;
    }

    @Override
    @Transactional
    public void run() {

        recentlyBlockNumber = Long.parseLong(klaytnPublicNodeService.getBlockNumber().get("result").toString().replaceFirst("^0x",""),16);

        klaytnBlockParsingHandler.updateKlaytnBlockNumber(KlaytnBlockStatus.RECENTLY_BLOCK_NUMBER, recentlyBlockNumber);

        currentParsingBlockNumber = klaytnBlockParsingHandler.getKlaytnBlockNumber(KlaytnBlockStatus.CURRENT_PARSING_BLOCK_NUMBER);

        try {
            while (true){
                System.out.println("blocknumber: " + currentParsingBlockNumber);
                KlaytnBlock klaytnBlock = klaytnPublicNodeService.getBlockByNumber("0x" + Long.toHexString(currentParsingBlockNumber));
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
                klaytnBlockParsingHandler.updateKlaytnBlockNumber(KlaytnBlockStatus.CURRENT_PARSING_BLOCK_NUMBER, currentParsingBlockNumber);
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
