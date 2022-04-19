package app.klaytnapi.batchjob.klaytn;

import app.klaytnapi.blockchainservice.application.klaytn.KlaytnConsumer;
import app.klaytnapi.blockchainservice.application.klaytn.KlaytnProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KlaytnBlcokParsingJob {

    private final KlaytnProducer klaytnProducer;
    private final KlaytnConsumer klaytnConsumer;

    public KlaytnBlcokParsingJob(
            KlaytnProducer klaytnProducer,
            KlaytnConsumer klaytnConsumer) {

        this.klaytnProducer = klaytnProducer;
        this.klaytnConsumer = klaytnConsumer;
    }

    @Scheduled(fixedDelay = 1000)
    public void producerJob() {
        klaytnProducer.producer();
    }


    @Scheduled(fixedDelay = 1000)
    public void consumerJob() {
        klaytnConsumer.consumer();
    }
}
