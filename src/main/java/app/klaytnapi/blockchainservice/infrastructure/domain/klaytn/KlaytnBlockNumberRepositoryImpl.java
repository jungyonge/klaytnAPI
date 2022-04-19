package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockNumber;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockNumberRepository;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class KlaytnBlockNumberRepositoryImpl implements KlaytnBlockNumberRepository {

    private KlaytnBlockNumberMongoRepository klaytnBlockNumberMongoRepository;

    @Override
    public KlaytnBlockNumber save(KlaytnBlockNumber klaytnBlockNumber) {
        return klaytnBlockNumberMongoRepository.save(klaytnBlockNumber);
    }

    @Override
    public KlaytnBlockNumber getKlaytnBlockNumberByStatus(KlaytnBlockStatus status) {
        return klaytnBlockNumberMongoRepository.getKlaytnBlockNumberByStatus(status);
    }
}
