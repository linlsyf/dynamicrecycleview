package com.core.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lxw
 */
public class CommonNetThreadPool extends ThreadProxy {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT < 2 ? 3 : (CPU_COUNT + 1);
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE * 2 - 1;
    private static final int KEEP_ALIVE = 60;
    protected BaseThreadPool executor;

    public BaseThreadPool getExecutor() {
        if (executor == null) {
            synchronized (CommonNetThreadPool.class) {
                if (executor == null) {
                    executor = new BaseThreadPool(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<Runnable>(128),
                            new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return executor;
    }
}
