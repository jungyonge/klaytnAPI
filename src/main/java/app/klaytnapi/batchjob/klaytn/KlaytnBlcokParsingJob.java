package app.klaytnapi.batchjob.klaytn;

import app.klaytnapi.blockchainservice.application.klaytn.KlaytnConsumer;
import app.klaytnapi.blockchainservice.application.klaytn.KlaytnProducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KlaytnBlcokParsingJob {

    private final Runnable klaytnProducer;
    private final Runnable klaytnConsumer;

    public KlaytnBlcokParsingJob(
            @Qualifier("klaytnProducer") Runnable klaytnProducer,
            @Qualifier("klaytnConsumer") Runnable klaytnConsumer) {
        this.klaytnProducer = klaytnProducer;
        this.klaytnConsumer = klaytnConsumer;
    }

    @Scheduled(fixedDelay = 1000)
    public void producerJob() {
        new Thread(klaytnProducer).start();

    }


    @Scheduled(fixedDelay = 1000)
    public void consumerJob() {
        new Thread(klaytnConsumer).start();
    }
}
