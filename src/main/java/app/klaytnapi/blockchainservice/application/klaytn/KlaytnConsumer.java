package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnQueue;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klaytn.caver.utils.Utils;
import com.klaytn.caver.utils.Utils.KlayUnit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.utils.Numeric;

@Service
@AllArgsConstructor
@Slf4j
public class KlaytnConsumer {

    private final KlaytnTransactionRepository klaytnTransactionRepository;
    private final KlaytnBlockParsingHandler klaytnBlockParsingHandler;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper modelMapper;

    @Transactional
    public void consumer() {
        while (true) {
            Map block = null;
            try {
                if (!KlaytnQueue.queue.isEmpty()) {
                    block = KlaytnQueue.queue.poll(1, TimeUnit.SECONDS);

//                    log.info(Thread.currentThread().getName() + " : "+ String.valueOf(KlaytnQueue.queue.size()));

                    long timestamp = Long.parseLong(
                            block.get("timestamp").toString().replaceFirst("^0x", ""), 16);
                    long blockNumber = Long.parseLong(
                            block.get("number").toString().replaceFirst("^0x", ""), 16);
                    double totalKlayValues = 0;

                    ArrayList<Map> transactions = (ArrayList<Map>) block.get("transactions");

                    for (Map transaction : transactions) {
                        double value = 0;
                        if(transaction.get("value") != null){
                            value = Double.parseDouble(Utils.convertFromPeb(new BigDecimal(
                                    Numeric.toBigInt(transaction.get("value").toString())), KlayUnit.KLAY));                        }

                        String to = null;
                        if(transaction.get("to") != null){
                            to = transaction.get("to").toString();
                        }
                        KlaytnTransaction klaytnTransaction = KlaytnTransaction.create(
                                transaction.get("hash").toString(),
                                transaction.get("type").toString(),
                                value, blockNumber, timestamp,
                                transaction.get("from").toString(),
                                to);
                        klaytnBlockParsingHandler.insertKlaytnTransaction(klaytnTransaction);

                        totalKlayValues = totalKlayValues + value;

                    }

                    KlaytnBlock klaytnBlock = KlaytnBlock.create(String.valueOf(block.get("hash")),
                            blockNumber, transactions.size(), totalKlayValues, timestamp);
                    klaytnBlockParsingHandler.insertKlaytnBlock(klaytnBlock, blockNumber);

                } else {
                    break;
                }
//                Thread.sleep(100);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

        }
    }
}
