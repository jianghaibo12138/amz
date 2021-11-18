package com.amazing2j.amz.amz_server;

import com.amazing2j.amz.amz_server.service.trs_manager.TrsRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AMZServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AMZServerApplication.class, args);
        TrsRunner tr = new TrsRunner();
        tr.run();
    }

}
