package app.klaytnapi.blockchainservice.domain.klaytn;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "klaytnTransaction")
public class KlaytnTransaction {

    @Id
    private String _id;
    private String txHash;
    private String txType;
    private long blockNumber;
    private double value;
    private long timestamp;

    private KlaytnTransaction(String txHash, String txType, double value, long blockNumber,
            long timestamp) {
        this.setTxHash(txHash);
        this.setTxType(txType);
        this.setTimestamp(timestamp);
        this.setBlockNumber(blockNumber);
        this.setValue(value);
    }

    public static KlaytnTransaction create(String txHash, String txType, double value,
            long blockNumber, long timestamp) {

        return new KlaytnTransaction(txHash, txType, value, blockNumber, timestamp);
    }

    private void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    private void setTxType(String txType) {
        this.txType = txType;
    }

    private void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private void setValue(double value) {
        this.value = value;
    }

    private void setBlockNumber(long blockNumber) {
        this.blockNumber = blockNumber;
    }
}
