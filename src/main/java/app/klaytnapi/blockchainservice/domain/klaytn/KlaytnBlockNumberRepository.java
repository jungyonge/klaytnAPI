package app.klaytnapi.blockchainservice.domain.klaytn;

public interface KlaytnBlockNumberRepository {

    KlaytnBlockNumber save(KlaytnBlockNumber klaytnBlockNumber);

    KlaytnBlockNumber getKlaytnBlockNumberByStatus(KlaytnBlockStatus status);

}
