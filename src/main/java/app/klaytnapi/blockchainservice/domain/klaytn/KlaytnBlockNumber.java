package app.klaytnapi.blockchainservice.domain.klaytn;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "klaytnBlockNumber")
public class KlaytnBlockNumber {

    @Id
    private String _id;
    private KlaytnBlockStatus status;
    private long blockNumber;

    private KlaytnBlockNumber(KlaytnBlockStatus status, long blockNumber){
        this.setStatus(status);
        this.setBlockNumber(blockNumber);
    }

    public static KlaytnBlockNumber create(KlaytnBlockStatus status, long blockNumber){
        return new KlaytnBlockNumber(status, blockNumber);
    }

    public void updateBlockNumber(long blockNumber){
        this.setBlockNumber(blockNumber);
    }

    private void setStatus(KlaytnBlockStatus status) {
        this.status = status;
    }

    private void setBlockNumber(long blockNumber) {
        this.blockNumber = blockNumber;
    }
}
