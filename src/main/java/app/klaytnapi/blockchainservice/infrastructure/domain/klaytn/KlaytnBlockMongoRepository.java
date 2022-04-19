package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KlaytnBlockMongoRepository extends MongoRepository<KlaytnBlock, Long> {

}
