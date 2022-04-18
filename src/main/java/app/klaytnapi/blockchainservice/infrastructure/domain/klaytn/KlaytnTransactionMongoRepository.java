package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KlaytnTransactionMongoRepository extends MongoRepository<KlaytnTransaction, Long> {


}