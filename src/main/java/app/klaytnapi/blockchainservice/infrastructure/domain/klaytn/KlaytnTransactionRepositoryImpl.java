package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class KlaytnTransactionRepositoryImpl implements KlaytnTransactionRepository {

    private final KlaytnTransactionMongoRepository klaytnTransactionMongoRepository;

    @Override
    public KlaytnTransaction save(KlaytnTransaction klaytnTransaction) {
        return klaytnTransactionMongoRepository.save(klaytnTransaction);
    }
}
