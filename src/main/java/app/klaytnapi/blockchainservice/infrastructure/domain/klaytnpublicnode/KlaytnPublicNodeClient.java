package app.klaytnapi.blockchainservice.infrastructure.domain.klaytnpublicnode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KlaytnPublicNodeClient {

    private HttpURLConnection connection;

    public KlaytnPublicNodeClient() {
        String apiURL = "https://api.baobab.klaytn.net:8651";
        URL url = null;
        try {
            url = new URL(apiURL);
            this.connection =(HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBlockByNumber(){
        String param = "{\"jsonrpc\":\"2.0\",\"method\":\"klay_getBlockByNumber\",\"params\":[\"0x541bdba\", true],\"id\":1}";
        String inputLine = null;
        StringBuffer outResult = new StringBuffer();

        try {
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            OutputStream os = connection.getOutputStream();
            os.write(param.getBytes("UTF-8"));
            os.flush();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));
            while ((inputLine = in.readLine()) != null) {
                outResult.append(inputLine);
            }

            System.out.println(outResult);
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        KlaytnPublicNodeClient klaytnPublicNodeClient = new KlaytnPublicNodeClient();
        klaytnPublicNodeClient.getBlockByNumber();
    }
}
