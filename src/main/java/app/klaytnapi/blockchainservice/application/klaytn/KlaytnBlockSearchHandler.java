package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.BlockchainDomainValidationMessage;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockRepository;
import app.klaytnapi.support.domain.DomainValidationException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KlaytnBlockSearchHandler {

    private final KlaytnBlockRepository klaytnBlockRepository;

    public List<KlaytnBlock> getBlock(long from, long to, Double totalKlayValues, Integer transactionCount) {

        List<KlaytnBlock> result;
        if (totalKlayValues > 0) {
            result = klaytnBlockRepository.getBlockByTotalKlayValues(totalKlayValues, from, to);
        } else {
            result = klaytnBlockRepository.getBlockByTransactionCount(transactionCount, from, to);
        }

        if (result == null) {
            throw new DomainValidationException(
                    BlockchainDomainValidationMessage.ERROR_NO_TRANSACTION);
        }

        return result;
    }
}
