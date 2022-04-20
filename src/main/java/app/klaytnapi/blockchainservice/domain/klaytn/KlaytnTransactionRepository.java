package app.klaytnapi.blockchainservice.domain.klaytn;

import java.util.List;

public interface KlaytnTransactionRepository {

    KlaytnTransaction save(KlaytnTransaction klaytnTransaction);

    List<KlaytnTransaction> getValueTransferTransaction(long from, long to);

    List<KlaytnTransaction> getValueTransferTransactionByAddress(String address, long from, long to);

    List<KlaytnTransaction> getTransactionByTxType(List<String> txType, long from, long to);

    }
