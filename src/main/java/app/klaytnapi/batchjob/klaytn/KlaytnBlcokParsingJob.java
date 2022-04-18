package app.klaytnapi.batchjob.klaytn;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class KlaytnBlcokParsingJob {

    @Scheduled(fixedDelay = 600000)
    @Transactional(rollbackFor = Exception.class)
    public void producerJob() {
    }


    @Scheduled(fixedDelay = 600000)
    @Transactional(rollbackFor = Exception.class)
    public void consumerJob() {
        // todo - 오픈씨 컬렉션 이름 가져오기
    }
}
