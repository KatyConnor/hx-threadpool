package hx.thread.executor.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 线程池的配置管理
 * @Author mingliang
 * @Date 2017-12-29 11:23
 */
public class ThreadPoolManageExecutor{

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolManageExecutor.class);

    private static volatile Map<String,Executor> poolSize = new LinkedHashMap<>();
    private static ThreadPoolManageExecutor threadPoolManageExecutor;

    /**
     * 获取线程池
     * @param threadName
     * @return
     */
    public ThreadPoolExecutor getThreadPoolExecutor(String threadName){
        return (ThreadPoolExecutor) poolSize.get(threadName);
    }

    /**
     * 获取单利对象
     * @return
     */
    public static ThreadPoolManageExecutor getNewInstance(){
        threadPoolManageExecutor = ThreadPoolFactory.NEW_INSTANCE;
        return threadPoolManageExecutor;
    }

    public void addThreadPoolExcutor(ThreadPoolManageEntity entity){
        initThreadPool(entity);
    }


    /**
     * <p>
     *    初使化线程池，这是JDK5.0中自带的线程池，这里的参数依次代表：  
     *    核心线程数(最小活动线程数)  
     *    最大线程数及并发数【这个要注意，如果你的实际发大于该数，则有些请求这个时候虽然被接收，但是去得不到处理，这个数据一定得根据实际情况而设定，如我这里设值为20，实际模拟并发50，如循环一次，或者是二次并发，总会有20个不能够处理，如果设为25，就有15得不到处理，如果设为50则全部可以被处理，这个可以折磨了我好几天】  
     *    线程池维护线程所允许的空闲时间  
     *    线程池维护线程所允许的空闲时间的单位  
     *    线程池所使用的缓冲队列  
     *    线程池对拒绝任务的处理策略(通常是抛出异常)
     * </p>
     *
     * 初始化一个线程池
     * @param entity
     */
    private void initThreadPool(ThreadPoolManageEntity entity){
        LOGGER.info("初始化线程池！threadPoolName = {}",entity.getThreadName());
        synchronized (this){
            if (poolSize.containsKey(entity.getThreadName())){
                LOGGER.info("线程池 threadPoolName={} 已经存在！",entity.getThreadName());
                return;
            }

            ThreadPoolExecutor threadPool = null;
            if (null == entity.getThreadFactory() && null == entity.getHandler()){
                threadPool = getThreadPoolExecutor(entity.getCorePoolSize(),entity.getMaximumPoolSize(),
                        entity.getKeepAliveTime(),entity.getUnit(),entity.getWorkQueue());
            }else if (null == entity.getHandler()){
                threadPool = getThreadPoolExecutor(entity.getCorePoolSize(),entity.getMaximumPoolSize(),
                        entity.getKeepAliveTime(),entity.getUnit(),entity.getWorkQueue(),entity.getThreadFactory());
            } else if (null == entity.getThreadFactory()) {
                threadPool = getThreadPoolExecutor(entity.getCorePoolSize(),entity.getMaximumPoolSize(),
                        entity.getKeepAliveTime(),entity.getUnit(),entity.getWorkQueue(),entity.getHandler());
            }else {
                threadPool = getThreadPoolExecutor(entity.getCorePoolSize(),entity.getMaximumPoolSize(),
                        entity.getKeepAliveTime(),entity.getUnit(),entity.getWorkQueue(),
                        entity.getThreadFactory(),entity.getHandler());
            }
            poolSize.put(entity.getThreadName(),threadPool);
        }
    }


    /**
     *
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param threadFactory
     * @param handler
     * @return
     */
    private ThreadPoolExecutor getThreadPoolExecutor(int corePoolSize,
                                                     int maximumPoolSize,
                                                     long keepAliveTime,
                                                     TimeUnit unit,
                                                     BlockingQueue<Runnable> workQueue,
                                                     ThreadFactory threadFactory,
                                                     RejectedExecutionHandler handler){
        return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,
                workQueue,threadFactory,handler);
    }

    /**
     *
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param handler
     * @return
     */
    private ThreadPoolExecutor getThreadPoolExecutor(int corePoolSize,
                                                     int maximumPoolSize,
                                                     long keepAliveTime,
                                                     TimeUnit unit,
                                                     BlockingQueue<Runnable> workQueue,
                                                     RejectedExecutionHandler handler){
        return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,
                workQueue,handler);
    }

    /**
     *
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @param threadFactory
     * @return
     */
    private ThreadPoolExecutor getThreadPoolExecutor(int corePoolSize,
                                                     int maximumPoolSize,
                                                     long keepAliveTime,
                                                     TimeUnit unit,
                                                     BlockingQueue<Runnable> workQueue,
                                                     ThreadFactory threadFactory){
        return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,
                workQueue,threadFactory);
    }

    /**
     *
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param workQueue
     * @return
     */
    private ThreadPoolExecutor getThreadPoolExecutor(int corePoolSize,
                                                     int maximumPoolSize,
                                                     long keepAliveTime,
                                                     TimeUnit unit,
                                                     BlockingQueue<Runnable> workQueue){
        return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,
                workQueue);
    }

    private static final class ThreadPoolFactory{
        private static final ThreadPoolManageExecutor NEW_INSTANCE = new ThreadPoolManageExecutor();
    }
}
