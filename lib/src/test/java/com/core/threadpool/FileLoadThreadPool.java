package com.core.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lxw
 */
class FileLoadThreadPool extends ThreadProxy {
    //用okHttp的话线程池的掌控权主要限制在okHttp的maxRequest下
    private static final int MAX_POOL_SIZE =5;
    private static final int MAXIMUM_QUEUE_SIZE = 64;
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit UNIT = TimeUnit.MINUTES;
    private int mCorePoolSize = 3;
    protected BaseThreadPool executor;
    public BaseThreadPool getExecutor() {
        if (executor == null) {
            synchronized (FileLoadThreadPool.class) {
                if (executor == null) {
                    executor = new BaseThreadPool(mCorePoolSize, MAX_POOL_SIZE, KEEP_ALIVE_TIME, UNIT,
                            new LinkedBlockingDeque<Runnable>(MAXIMUM_QUEUE_SIZE),
                            new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return executor;
    }

    @Override
    public void setCorePoolSize(int mCorePoolSize) {
        if (mCorePoolSize <= 0) mCorePoolSize = 1;
        if (mCorePoolSize > MAX_POOL_SIZE) mCorePoolSize = MAX_POOL_SIZE;
        this.mCorePoolSize = mCorePoolSize;
    }

}
