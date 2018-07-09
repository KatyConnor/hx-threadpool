package hx.thread.executor.pool;


import java.util.concurrent.*;

/**
 * 线程次够着参数管理类
 * @Author mingliang
 * @Date 2018-01-12 11:43
 */
public class ThreadPoolManageEntity {

    private String threadName;
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private TimeUnit unit;
    private BlockingQueue<Runnable> workQueue;
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler handler;

    public ThreadPoolManageEntity(String threadName,int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                  TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        check(corePoolSize,maximumPoolSize);
        this.threadName = checkThreadName(threadName);
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime == 0?60:keepAliveTime;
        this.unit = unit == null?TimeUnit.SECONDS:unit;
        this.workQueue = workQueue == null?new LinkedBlockingDeque<Runnable>(100):workQueue;
    }

    public ThreadPoolManageEntity(String threadName,int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        check(corePoolSize,maximumPoolSize);
        this.threadName = checkThreadName(threadName);
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime == 0?60:keepAliveTime;
        this.unit = unit == null?TimeUnit.SECONDS:unit;
        this.workQueue = workQueue == null?new LinkedBlockingDeque<Runnable>(100):workQueue;
        this.threadFactory = threadFactory == null?Executors.defaultThreadFactory():threadFactory;
    }

    public ThreadPoolManageEntity(String threadName,int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        check(corePoolSize,maximumPoolSize);
        this.threadName = checkThreadName(threadName);
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime == 0?60:keepAliveTime;
        this.unit = unit == null?TimeUnit.SECONDS:unit;
        this.workQueue = workQueue == null?new LinkedBlockingDeque<Runnable>(100):workQueue;
        this.handler = handler == null?new RefuseThreadPoolHandler():handler;
    }

    public ThreadPoolManageEntity(String threadName,int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        check(corePoolSize,maximumPoolSize);
        this.threadName = checkThreadName(threadName);
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime == 0?60:keepAliveTime;
        this.unit = unit == null?TimeUnit.SECONDS:unit;
        this.workQueue = workQueue == null?new LinkedBlockingDeque<Runnable>(100):workQueue;
        this.threadFactory = threadFactory == null?Executors.defaultThreadFactory():threadFactory;
        this.handler = handler == null?new RefuseThreadPoolHandler():handler;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    public BlockingQueue<Runnable> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public RejectedExecutionHandler getHandler() {
        return handler;
    }

    public void setHandler(RejectedExecutionHandler handler) {
        this.handler = handler;
    }

    private void check(int corePoolSize, int maximumPoolSize){
        if (corePoolSize == 0 || maximumPoolSize == 0){
            throw new RuntimeException(String.format("corePoolSize=[%s],maximumPoolSize=[%s] 不能为空！",
                    corePoolSize,maximumPoolSize));
        }
    }


    private String checkThreadName(String threadName){
        if (null == threadName || threadName.isEmpty()){

        }
        return threadName;
    }
}
