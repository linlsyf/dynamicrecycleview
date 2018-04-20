package com.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 创建者：zw
 * 修改时间：2015-3-2 上午11:08:14
 * 作用：线程池辅助类，整个应用程序就只有一个线程池去管理线程。
 * 可以设置核心线程数、最大线程数、额外线程空状态生存时间，阻塞队列长度来优化线程池。
 */
public class ThreadPoolUtils {
    
    private ThreadPoolUtils(){
        
    }
    
    /**线程池核心线程数*/
    private static int CORE_POOL_SIZE = 5;
    
    /**线程池最大线程数*/
    private static int MAX_POOL_SIZE = 100;
    
    /**额外线程空状态生存时间*/
    private static int KEEP_ALIVE_TIME = 10000;
    
    /**阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程。*/
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
    
    /**线程工厂*/
    private static ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "myThreadPool thread:" + integer.getAndIncrement());
        }
    };
    
    //线程池
    private static ThreadPoolExecutor threadPool;
    
    
    /**
     * 从线程池中抽取线程，执行指定的Runnable对象
     * @param runnable
     */
    public static void execute(Runnable runnable){
    	if(threadPool==null){
    		threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,MAX_POOL_SIZE, KEEP_ALIVE_TIME,TimeUnit.SECONDS, workQueue,threadFactory);
    	}
        threadPool.execute(runnable);
    }

    /**普通线程池(处理网络请求或者耗时比较短但频繁的操作)*/
    private static ThreadPoolExecutor commonExecutor;

    private static final int COMMON_CORE_POOL_SIZE = 5;

    private static final int COMMON_MAX_POOL_SIZE = 5;

    private static final int COMMON_KEEP_ALIVE_TIME = 0;

    private static void initCommonThreadPoolProxy(){
        if(commonExecutor == null){
            synchronized (ThreadPoolUtils.class){
                if(commonExecutor == null){
                    BlockingQueue<Runnable> workQuene = new LinkedBlockingQueue<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                    commonExecutor = new ThreadPoolExecutor(COMMON_CORE_POOL_SIZE, COMMON_MAX_POOL_SIZE, COMMON_KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQuene, threadFactory, handler);
                }
            }
        }
    }

    /**
     * 核心线程为5，最大线程为5，队列(无界)，线程存活时间是为0秒
     * @param runnable Runnable对象
     */
    public static void executeCommonPool(Runnable runnable){
        initCommonThreadPoolProxy();
        commonExecutor.execute(runnable);
    }

    public static void removeCommonPool(Runnable runnable){
        initCommonThreadPoolProxy();
        commonExecutor.remove(runnable);
    }

    /**
     * 小视频处理线程池
     */
    private static ThreadPoolExecutor videoExecutor;

    private static final int VIDEO_CORE_POOL_SIZE = 8;

    private static final int VIDEO_MAX_POOL_SIZE = 8;

    private static final int VIDEO_KEEP_ALIVE_TIME = 0;

    private static void initVideoThreadPoolProxy(){
        if(videoExecutor == null){
            synchronized (ThreadPoolUtils.class){
                if(videoExecutor == null){
                    BlockingQueue<Runnable> workQuene = new LinkedBlockingQueue<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                    videoExecutor = new ThreadPoolExecutor(VIDEO_CORE_POOL_SIZE, VIDEO_MAX_POOL_SIZE, VIDEO_KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQuene, threadFactory, handler);
                }
            }
        }
    }

    /**
     * 核心线程为10，最大线程为10，队列(无界)，线程存活时间是为0秒(非针对VideoPlayItemView的操作请不要使用该线程池)
     * @param runnable Runnable对象
     */
    public static void executeVideoPool(Runnable runnable){
        initVideoThreadPoolProxy();
        videoExecutor.execute(runnable);
    }

    public static void removeVideoPool(Runnable runnable){
        initVideoThreadPoolProxy();
        videoExecutor.remove(runnable);
    }
}