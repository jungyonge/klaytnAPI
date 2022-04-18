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
    private int timestamp;

    private KlaytnTransaction(String txHash, String txType, int timestamp) {
        this.setTxHash(txHash);
        this.setTxType(txType);
        this.setTimestamp(timestamp);
    }

    public static KlaytnTransaction create(String txHash, String txType, int timestamp) {

        return new KlaytnTransaction(txHash, txType, timestamp);
    }

    private void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    private void setTxType(String txType) {
        this.txType = txType;
    }

    private void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
