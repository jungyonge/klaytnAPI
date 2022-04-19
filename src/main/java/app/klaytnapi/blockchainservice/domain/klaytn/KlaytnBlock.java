package app.klaytnapi.blockchainservice.domain.klaytn;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@Document(collection = "klaytnBlock")
public class KlaytnBlock {

    @Id
    private String _id;
    private String blockHash;
    private long blockNumber;
    private int transactionCount;
    private int totalKlayValues;
    private long timestamp;

    private KlaytnBlock(String blockHash, long blockNumber, int transactionCount,
            int totalKlayValues, long timestamp) {
        this.setBlockHash(blockHash);
        this.setBlockNumber(blockNumber);
        this.setTransactionCount(transactionCount);
        this.setTotalKlayValue(totalKlayValues);
        this.setTimestamp(timestamp);
    }

    public static KlaytnBlock create(String blockHash, long blockNumber, int transactionCount,
            int totalKlayValues, long timestamp) {
        return new KlaytnBlock(blockHash, blockNumber, transactionCount, totalKlayValues,
                timestamp);
    }

    private void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    private void setBlockNumber(long blockNumber) {
        this.blockNumber = blockNumber;
    }

    private void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    private void setTotalKlayValue(int totalKlayValues) {
        this.totalKlayValues = totalKlayValues;
    }

    private void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
