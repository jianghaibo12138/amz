package com.amazing2j.amz.amz_client.utils;

import com.amazing2j.amz.amz_server.utils.NetUtil;
import com.amazing2j.amz.amz_server.utils.Snowflake;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;

@Component
public class TXIdGenerator {
    private long workerId = 0;  //第几号机器
    private final long datacenterId = 1;  //第几号机房

    private final Snowflake snowflake = new Snowflake(workerId, datacenterId);

    @PostConstruct  //构造后开始执行，加载初始化工作
    public void init() throws UnknownHostException {
        //获取本机的ip地址编码
        workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = new Snowflake(workerId, datacenterId);
        return snowflake.nextId();
    }
}
