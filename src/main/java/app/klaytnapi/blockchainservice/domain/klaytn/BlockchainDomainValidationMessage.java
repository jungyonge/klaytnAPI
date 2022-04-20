package app.klaytnapi.blockchainservice.domain.klaytn;


import app.klaytnapi.support.domain.ExplainableMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BlockchainDomainValidationMessage implements ExplainableMessage {

    ERROR_NO_TRANSACTION(2_0001, "트랜잭션이 없습니다."),
    ;

    private final int code;
    private final String message;
    private final HttpStatus status;

    BlockchainDomainValidationMessage(int code, String message) {
        this.code = code;
        this.message = message;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public int getStatus() {
        return status.value();
    }
}
