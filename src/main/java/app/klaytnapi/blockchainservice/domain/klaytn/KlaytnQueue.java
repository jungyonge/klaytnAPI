package app.klaytnapi.blockchainservice.domain.klaytn;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class KlaytnQueue {

    public static ArrayBlockingQueue<Map> queue = new ArrayBlockingQueue<>(300);

}
