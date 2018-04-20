package com.core.threadpool;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lxw
 */
public class BaseThreadPool extends ThreadPoolExecutor {
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public BaseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public BaseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public BaseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public BaseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * 任务结束后回调
     */
    @Override
    protected void afterExecute(final Runnable r, Throwable t) {
        super.afterExecute(r, t);
        //当前正在运行的数量为1 表示当前正在停止的任务，同时队列中没有任务，表示所有任务下载完毕
        final boolean isAllTaskEnd = getActiveCount() == 1 && getQueue().size() == 0;
        if (taskEndListenerList != null && taskEndListenerList.size() > 0) {
            for (final OnTaskEndListener listener : taskEndListenerList) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onTaskEnd(r);
                        if (isAllTaskEnd) {
                            listener.onAllTaskEnd();
                        }
                    }
                });
            }
        }
    }

    private List<OnTaskEndListener> taskEndListenerList;

    public void addOnTaskEndListener(OnTaskEndListener taskEndListener) {
        if (taskEndListenerList == null) taskEndListenerList = new ArrayList<>();
        taskEndListenerList.add(taskEndListener);
    }

    public void removeOnTaskEndListener(OnTaskEndListener taskEndListener) {
        if(taskEndListenerList==null||taskEndListenerList.isEmpty()){
            return;
        }
        taskEndListenerList.remove(taskEndListener);
    }
    public void clearAllTaskEndListener(){
        if(taskEndListenerList==null||taskEndListenerList.isEmpty()){
            return;
        }
        taskEndListenerList.clear();
    }

    public interface OnTaskEndListener {
        void onTaskEnd(Runnable r);

        void onAllTaskEnd();
    }


}
