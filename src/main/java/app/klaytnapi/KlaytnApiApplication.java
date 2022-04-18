package app.klaytnapi;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@EnableEncryptableProperties
@EntityScan
@SpringBootApplication
public class KlaytnApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KlaytnApiApplication.class, args);
    }

}
