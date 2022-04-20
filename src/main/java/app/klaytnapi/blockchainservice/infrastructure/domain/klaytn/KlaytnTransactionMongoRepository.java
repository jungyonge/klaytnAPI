package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KlaytnTransactionMongoRepository extends MongoRepository<KlaytnTransaction, Long> {

    List<KlaytnTransaction> findAllByValueGreaterThanAndTimestampBetween(double value, long from, long to);

    List<KlaytnTransaction> findAllByValueGreaterThanAndFromAndTimestampBetween(double value, String fromAddress, long from, long to);

    List<KlaytnTransaction> findAllByValueGreaterThanAndToAndTimestampBetween(double value, String toAddress, long from, long to);

    List<KlaytnTransaction> findAllByTxTypeInAndTimestampBetween(List<String> txType, long from, long to);

}