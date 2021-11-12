package com.amazing2j.amz.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TXIdGeneratorTest {

    @Test
    void TestSnowflakeId() {
        long txId = new TXIdGenerator().snowflakeId();
        System.out.println(txId);
    }
}