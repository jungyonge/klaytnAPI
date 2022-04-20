package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class KlaytnBlockRepositoryImpl implements KlaytnBlockRepository {

    private final KlaytnBlockMongoRepository klaytnBlockMongoRepository;

    @Override
    public KlaytnBlock save(KlaytnBlock klaytnBlock) {
        return klaytnBlockMongoRepository.save(klaytnBlock);
    }

    @Override
    public List<KlaytnBlock> getBlockByTransactionCount(int count, long from, long to) {
        return klaytnBlockMongoRepository.getBlockByTransactionCountGreaterThanEqualAndTimestampBetween(count, from, to);
    }

    @Override
    public List<KlaytnBlock> getBlockByTotalKlayValues(double totalKlayValues, long from, long to) {
        return klaytnBlockMongoRepository.getBlockByTotalKlayValuesGreaterThanEqualAndTimestampBetween(totalKlayValues, from, to);
    }


}
