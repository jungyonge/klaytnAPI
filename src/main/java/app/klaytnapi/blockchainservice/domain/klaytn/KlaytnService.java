package app.klaytnapi.blockchainservice.domain.klaytn;

public interface KlaytnService {

    Block getBlockByNumber(String blockNumber);

    Transaction getTransactionByHash(String transactionHash);
}
