package app.klaytnapi.blockchainservice.domain.klaytn;

import java.util.Map;

public interface KlaytnService {

    Map getBlockNumber();

    Map getBlockByNumber(String blockNumber);

    KlaytnTransaction getTransactionByHash(String transactionHash);
}
