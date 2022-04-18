package app.klaytnapi.blockchainservice.infrastructure.domain.klaytnpublicnode;

import app.klaytnapi.blockchainservice.domain.klaytn.Block;
import app.klaytnapi.blockchainservice.domain.klaytn.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class KlaytnPublicNodeClient {
    private final String publicNode = "https://api.baobab.klaytn.net:8651";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate;

    public KlaytnPublicNodeClient(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public Block getBlockByNumber(String blockNumber){
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> params = new HashMap<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        params.put("jsonrpc", "2.0");
        params.put("method", "klay_getBlockByNumber");
        params.put("params", Arrays.asList(blockNumber, true));
        params.put("id", "1");

        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(params, headers);

        Block response = restTemplate.postForObject(publicNode, request, Block.class);

        return response;
    }

    public Transaction getTransactionByHash(String transactionHash){
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> params = new HashMap<>();

        headers.setContentType(MediaType.APPLICATION_JSON);
        params.put("jsonrpc", "2.0");
        params.put("method", "klay_getTransactionByHash");
        params.put("params", Arrays.asList(transactionHash));
        params.put("id", "1");

        HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(params, headers);

        Transaction response = restTemplate.postForObject(publicNode, request, Transaction.class);

        return response;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        KlaytnPublicNodeClient klaytnPublicNodeClient = new KlaytnPublicNodeClient(restTemplate);
        for(int i = 0 ; i < 100 ; i++){
            String blockNumber = "0x"+ Integer.toHexString(i);
            System.out.println(blockNumber);
            klaytnPublicNodeClient.getBlockByNumber(blockNumber);
//            klaytnPublicNodeClient.getTransactionByHash("0x4a527dacab0c8d5829ad060a11b19afa29215bd5475ca9672b6ca6fcff15ac47");
        }

    }
}
