package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransactionRepository;
import java.util.List;
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

    @Override
    public List<KlaytnTransaction> getValueTransferTransaction(long from, long to) {
        return klaytnTransactionMongoRepository.findAllByValueGreaterThanAndTimestampBetween(0, from, to);
    }

    @Override
    public List<KlaytnTransaction> getValueTransferTransactionByAddress(String address, long from, long to) {
        List<KlaytnTransaction> list = klaytnTransactionMongoRepository.findAllByValueGreaterThanAndFromAndTimestampBetween(0, address, from, to);
        list.addAll(klaytnTransactionMongoRepository.findAllByValueGreaterThanAndToAndTimestampBetween(0, address, from, to));

        return list;
    }

    @Override
    public List<KlaytnTransaction> getTransactionByTxType(List<String> txType, long from, long to) {
        return klaytnTransactionMongoRepository.findAllByTxTypeInAndTimestampBetween(txType, from, to);
    }

}
