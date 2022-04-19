package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockNumber;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockNumberRepository;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockRepository;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockStatus;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class KlaytnBlockParsingHandler {

    private KlaytnBlockNumberRepository klaytnBlockNumberRepository;
    private KlaytnTransactionRepository klaytnTransactionRepository;
    private KlaytnBlockRepository klaytnBlockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateKlaytnBlockNumber(KlaytnBlockStatus status, long blockNumber){
        KlaytnBlockNumber klaytnBlockNumber = klaytnBlockNumberRepository.getKlaytnBlockNumberByStatus(status);
        klaytnBlockNumber.updateBlockNumber(blockNumber);
        klaytnBlockNumberRepository.save(klaytnBlockNumber);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createKlaytnBlockNumber(){
        KlaytnBlockNumber klaytnBlockNumber = KlaytnBlockNumber.create(KlaytnBlockStatus.CURRENT_PARSING_BLOCK_NUMBER, 0);
        klaytnBlockNumberRepository.save(klaytnBlockNumber);

        KlaytnBlockNumber klaytnBlockNumber1 = KlaytnBlockNumber.create(KlaytnBlockStatus.RECENTLY_BLOCK_NUMBER, 0);
        klaytnBlockNumberRepository.save(klaytnBlockNumber1);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public long getKlaytnBlockNumber(KlaytnBlockStatus status){
        KlaytnBlockNumber klaytnBlockNumber = klaytnBlockNumberRepository.getKlaytnBlockNumberByStatus(status);
        return klaytnBlockNumber.getBlockNumber();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertKlaytnTransaction(KlaytnTransaction klaytnTransaction){
        klaytnTransactionRepository.save(klaytnTransaction);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertKlaytnBlock(KlaytnBlock klaytnBlock){
        klaytnBlockRepository.save(klaytnBlock);
    }

}
