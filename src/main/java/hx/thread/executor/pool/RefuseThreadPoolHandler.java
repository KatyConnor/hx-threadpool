package hx.thread.executor.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 拒绝策略
 * @Author mingliang
 * @Date 2017-12-29 14:33
 */
public class RefuseThreadPoolHandler implements RejectedExecutionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefuseThreadPoolHandler.class);

    public RefuseThreadPoolHandler() {
    }

    /**
     * 这里讲线程重新放回队列queue中
     * @param r
     * @param executor
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            LOGGER.info("当前线程重新放回queue！threadName = {},threadId = {}",
                    Thread.currentThread().getName(),Thread.currentThread().getId());
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            LOGGER.info("当前线程重新放回queue失败！exception = {}",e);
        }
    }
}
