package com.core.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lxw
 */
public class HeadPicThreadPool extends ThreadProxy{
    protected BaseThreadPool executor;
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAXIMUM_POOL_SIZE = 10;
    private static final int KEEP_ALIVE = 60;

    public BaseThreadPool getExecutor() {
        if (executor == null) {
            synchronized (HeadPicThreadPool.class) {
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
