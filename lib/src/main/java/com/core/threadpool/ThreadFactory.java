package com.core.threadpool;

/**
 * Created by lxw
 */
public class ThreadFactory {
    private static volatile ThreadProxy sFileLoadProxy;
    private static volatile ThreadProxy sCommonNetProxy;
    private static volatile ThreadProxy sComputationProxy;
    private static volatile ThreadProxy sSingleThreadProxy;
    private static volatile ThreadProxy sHeadPicThreadProxy;

    private ThreadFactory() {
    }

    /** file pool */
    private static ThreadProxy getFileLoadPool() {
        if (sFileLoadProxy == null) {
            synchronized (ThreadFactory.class) {
                if (sFileLoadProxy == null) {
                    sFileLoadProxy = new FileLoadThreadPool();
                }
            }
        }
        return sFileLoadProxy;
    }

    /** io pool */
    private static ThreadProxy getCommonNetProxy() {
        if (sCommonNetProxy == null) {
            synchronized (ThreadFactory.class) {
                if (sCommonNetProxy == null) {
                    sCommonNetProxy = new CommonNetThreadPool();
                }
            }
        }
        return sCommonNetProxy;
    }

    /** computation pool */
    private static ThreadProxy getComputationProxy() {
        if (sComputationProxy == null) {
            synchronized (ThreadFactory.class) {
                if (sComputationProxy == null) {
                    sComputationProxy = new ComputationThreadPool();
                }
            }
        }
        return sComputationProxy;
    }

    /** computation pool */
    private static ThreadProxy getSingleThreadProxy() {
        if (sSingleThreadProxy == null) {
            synchronized (ThreadFactory.class) {
                if (sSingleThreadProxy == null) {
                    sSingleThreadProxy = new SingleThreadPool();
                }
            }
        }
        return sSingleThreadProxy;
    }

    /** computation pool */
    private static ThreadProxy getHeadPicThreadProxy() {
        if (sHeadPicThreadProxy == null) {
            synchronized (ThreadFactory.class) {
                if (sHeadPicThreadProxy == null) {
                    sHeadPicThreadProxy = new HeadPicThreadPool();
                }
            }
        }
        return sHeadPicThreadProxy;
    }


    /** io 操作(数据库操作)，http ... */
    public static ThreadProxy io() {
        return getCommonNetProxy();
    }

    /*请求图片专用*/
    public static ThreadProxy headPic() {
        return getHeadPicThreadProxy();
    }

    /** fileDownload... */
    public static ThreadProxy file() {
        return getFileLoadPool();
    }

    /** 简单计算用到的线程池... */
    public static ThreadProxy computation() {
        return getComputationProxy();
    }

    /** 只有一条线程的线程池... */
    public static ThreadProxy single() {
        return getSingleThreadProxy();
    }

}
