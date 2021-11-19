package com.amazing2j.amz.amz_server.service.trs_manager;

import java.util.concurrent.*;

public class TCCThreadPoolExecutor {
    public static ExecutorService tccExecutorPool(int minThreads, int maxThreads) {
        return new ThreadPoolExecutor(minThreads, maxThreads, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }
}
