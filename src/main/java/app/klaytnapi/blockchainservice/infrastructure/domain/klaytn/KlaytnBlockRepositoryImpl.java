package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlockRepository;
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
}
