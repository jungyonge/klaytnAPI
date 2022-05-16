package app.klaytnapi.batchjob.klaytn;

import app.klaytnapi.blockchainservice.application.klaytn.KlaytnConsumer;
import app.klaytnapi.blockchainservice.application.klaytn.KlaytnProducer;
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

//    @Transactional
//    @Scheduled(fixedDelay = 1000)
//    public void producerJob() {
//        klaytnProducer.producer();
//    }
//
//    @Transactional
//    @Async
//    @Scheduled(fixedRate = 1)
//    public void consumerJob() {
//        klaytnConsumer.consumer();
//    }
}
