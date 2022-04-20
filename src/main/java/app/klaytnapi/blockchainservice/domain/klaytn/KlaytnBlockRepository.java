package app.klaytnapi.blockchainservice.domain.klaytn;

import java.util.List;

public interface KlaytnBlockRepository {

    KlaytnBlock save(KlaytnBlock klaytnBlock);

    List<KlaytnBlock> getBlock(long from, long to);

    List<KlaytnBlock> getBlockByTransactionCount(int count, long from, long to);

    List<KlaytnBlock> getBlockByTotalKlayValues(double totalKlayValues, long from, long to);
}