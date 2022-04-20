package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockStatus;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnQueue;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnService;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class KlaytnProducer {

    private final KlaytnService klaytnPublicNodeService;
    private final KlaytnBlockParsingHandler klaytnBlockParsingHandler;

    long recentlyBlockNumber = 0;
    long currentParsingBlockNumber = 0;

    public KlaytnProducer(
            @Qualifier("klaytnPublicNodeServiceImpl") KlaytnService klaytnPublicNodeService,
            KlaytnBlockParsingHandler klaytnBlockParsingHandler) {

        this.klaytnPublicNodeService = klaytnPublicNodeService;
        this.klaytnBlockParsingHandler = klaytnBlockParsingHandler;
    }

    @Transactional
    public void producer() {

        recentlyBlockNumber = Long.parseLong(klaytnPublicNodeService.getBlockNumber().get("result").toString().replaceFirst("^0x",""),16);
        klaytnBlockParsingHandler.updateKlaytnBlockNumber(KlaytnBlockStatus.RECENTLY_BLOCK_NUMBER, recentlyBlockNumber);
        currentParsingBlockNumber = klaytnBlockParsingHandler.getKlaytnBlockNumber(KlaytnBlockStatus.CURRENT_PARSING_BLOCK_NUMBER) + 1;

        try {
            while (true){
                Map klaytnBlock = (Map) klaytnPublicNodeService.getBlockByNumber("0x" + Long.toHexString(currentParsingBlockNumber)).get("result");
                KlaytnQueue.queue.put(klaytnBlock);

                if(recentlyBlockNumber == currentParsingBlockNumber){
                    break;
                }
                currentParsingBlockNumber++;
            }

        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

    }
}
