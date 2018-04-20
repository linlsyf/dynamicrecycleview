package com.core.threadpool;

/**
 * Created by lxw
 * 需要保证单例，不然多个对象就会产生多个线程池，不是我们想要的结果。
 */
public abstract class ThreadProxy {
    /**
     * 保证一个threadPool 一个executor
     *
     * @return
     */
    public abstract BaseThreadPool getExecutor();

    /**
     * 第一次get之前设置
     *默认doNothing
     * @param mCorePoolSize
     */
    public void setCorePoolSize(int mCorePoolSize) {

    }

    public void execute(Runnable runnable) {
        if (runnable != null) {
            getExecutor().execute(runnable);
        }
    }

    public void remove(Runnable runnable) {
        if (runnable != null) {
            getExecutor().remove(runnable);
        }
    }

    public void addOnTaskEndListener(BaseThreadPool.OnTaskEndListener onTaskEndListener) {
        if (onTaskEndListener != null) {
            getExecutor().addOnTaskEndListener(onTaskEndListener);
        } else {
            getExecutor().clearAllTaskEndListener();
        }
    }
    public void removeOnTaskEndListener(BaseThreadPool.OnTaskEndListener onTaskEndListener){
        if(onTaskEndListener==null){
            return;
        }
        getExecutor().removeOnTaskEndListener(onTaskEndListener);
    }
}
