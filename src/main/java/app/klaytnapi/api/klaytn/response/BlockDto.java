package app.klaytnapi.api.klaytn.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlockDto {

    private String _id;
    private String blockHash;
    private long blockNumber;
    private int transactionCount;
    private double totalKlayValues;
    private long timestamp;

}
