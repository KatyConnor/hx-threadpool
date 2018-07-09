package hx.thread.executor.factory;

import hx.thread.executor.pool.ThreadPoolManageEntity;
import hx.thread.executor.pool.ThreadPoolManageExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的工厂类
 * @Author mingliang
 * @Date 2017-12-29 20:45
 */
public class ThreadPoolFactory {

    private static ThreadPoolManageExecutor threadPoolManageExecutor = ThreadPoolManageExecutor.getNewInstance();

    /**
     * 获取一个线程池
     * @param threadPoolName
     * @return
     */
    public static ThreadPoolExecutor getThreadPool(String threadPoolName){
        return threadPoolManageExecutor.getThreadPoolExecutor(threadPoolName);
    }

    /**
     * 添加一个新歌新的线程池
     * @param entity
     */
    public static void addThreadPool(ThreadPoolManageEntity entity){
        threadPoolManageExecutor.addThreadPoolExcutor(entity);
    }
}
