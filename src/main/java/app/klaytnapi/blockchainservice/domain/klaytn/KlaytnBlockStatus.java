package app.klaytnapi.blockchainservice.domain.klaytn;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KlaytnBlockStatus {

    RECENTLY_BLOCK_NUMBER("RECENTLY_BLOCK_NUMBER", "최신 블록 넘버"),
    CURRENT_PARSING_BLOCK_NUMBER("CURRENT_PARSING_BLOCK_NUMBER", "현재 파싱 블록 넘버"),

    ;

    private final String value;
    private final String desc;
}
