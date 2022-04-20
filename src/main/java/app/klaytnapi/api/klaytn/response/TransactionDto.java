package app.klaytnapi.api.klaytn.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String _id;
    private String txHash;
    private String txType;
    private long blockNumber;
    private double value;
    private long timestamp;

}
