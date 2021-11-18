package com.amazing2j.amz.amz_server.utils;

import org.junit.jupiter.api.Test;

class TXIdGeneratorTest {

    @Test
    void TestSnowflakeId() {
        long txId = new TXIdGenerator().snowflakeId();
        System.out.println(txId);
    }
}