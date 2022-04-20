package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KlaytnBlockMongoRepository extends MongoRepository<KlaytnBlock, Long> {

    List<KlaytnBlock> getKlaytnBlockByTimestampBetween(long from, long to);

    List<KlaytnBlock> getKlaytnBlockByTransactionCountGreaterThanEqualAndTimestampBetween(int transactionCount, long from, long to);

    List<KlaytnBlock> getKlaytnBlockByTotalKlayValuesGreaterThanEqualAndTimestampBetween(double totalKlayValues, long from, long to);

}
