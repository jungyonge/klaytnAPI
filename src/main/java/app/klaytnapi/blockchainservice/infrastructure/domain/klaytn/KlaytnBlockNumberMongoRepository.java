package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockNumber;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KlaytnBlockNumberMongoRepository extends MongoRepository<KlaytnBlockNumber, Long> {

    KlaytnBlockNumber getKlaytnBlockNumberByStatus(KlaytnBlockStatus status);
}
