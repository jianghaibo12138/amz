package com.amazing2j.amz.amz_client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMZClientConfigure {

    @Value("${server.address}")
    private String ServerAddress;

    @Value("${server.port}")
    private Integer ServerPort;

    public String getServerAddress() {
        return ServerAddress;
    }

    public Integer getServerPort() {
        return ServerPort;
    }
}
