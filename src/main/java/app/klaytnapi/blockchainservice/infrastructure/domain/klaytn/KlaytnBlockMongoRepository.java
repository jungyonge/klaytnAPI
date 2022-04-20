package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KlaytnBlockMongoRepository extends MongoRepository<KlaytnBlock, Long> {

    List<KlaytnBlock> getBlockByTransactionCountGreaterThanEqualAndTimestampBetween(int transactionCount, long from, long to);

    List<KlaytnBlock> getBlockByTotalKlayValuesGreaterThanEqualAndTimestampBetween(double totalKlayValues, long from, long to);

}
