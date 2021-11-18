package com.amazing2j.amz.amz_server.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtil {
    public static String getLocalhostStr() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }

    public static long ipv4ToLong(String ipv4) {
        return Integer.toUnsignedLong(ip2Int(ipv4));
    }

    public static int ip2Int(String ip) {
        int n = 0;
        String[] split = ip.split("\\.");
        for (int i = 0; i < split.length; i++) {
            String s = split[split.length - i - 1];
            int x = Integer.parseInt(s) << i * 8;
            n |= x;
        }
        return n;
    }

    public static String long2Ip(long ip) {
        return int2Ip((int) ip);
    }

    public static String int2Ip(int ip) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int l = ip >> (3 - i) * 8 & 0xFF;
            sb.append(l).append(".");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
