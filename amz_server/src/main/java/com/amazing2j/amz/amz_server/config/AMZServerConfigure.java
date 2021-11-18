package com.amazing2j.amz.amz_server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMZServerConfigure {

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
