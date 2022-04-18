package app.klaytnapi.blockchainservice.domain.klaytn;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageBlockingQueue {

    public static BlockingQueue<Map> queue = new ArrayBlockingQueue<Map>(30);

}
