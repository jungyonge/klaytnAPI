package app.klaytnapi.blockchainservice.infrastructure.domain.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnBlock;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnService;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class KlaytnPublicNodeServiceImpl implements KlaytnService {
    private final String publicNode = "https://api.baobab.klaytn.net:8651";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate;

    public KlaytnPublicNodeServiceImpl(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public Map getBlockNumber() {
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> params = new HashMap<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        params.put("jsonrpc", "2.0");
        params.put("method", "klay_blockNumber");
        params.put("params", Arrays.asList());
        params.put("id", "83");

        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(params, headers);

        return restTemplate.postForObject(publicNode, request, Map.class);
    }

    public KlaytnBlock getBlockByNumber(String blockNumber){
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> params = new HashMap<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        params.put("jsonrpc", "2.0");
        params.put("method", "klay_getBlockByNumber");
        params.put("params", Arrays.asList(blockNumber, true));
        params.put("id", "1");

        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(params, headers);

        return restTemplate.postForObject(publicNode, request, KlaytnBlock.class);
    }

    public KlaytnTransaction getTransactionByHash(String transactionHash){
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> params = new HashMap<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        params.put("jsonrpc", "2.0");
        params.put("method", "klay_getTransactionByHash");
        params.put("params", Arrays.asList(transactionHash));
        params.put("id", "1");

        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(params, headers);

        return restTemplate.postForObject(publicNode, request, KlaytnTransaction.class);
    }
}
