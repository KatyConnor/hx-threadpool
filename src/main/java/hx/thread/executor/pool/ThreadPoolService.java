package hx.thread.executor.pool;

import hx.thread.executor.event.ThreadPoolEvent;
import hx.thread.executor.factory.ThreadPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 将线程交给ThreadPoolExecutor管理
 * @Author mingliang
 * @Date 2017-12-29 11:25
 */
@Component
public class ThreadPoolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolService.class);
//    private static volatile int count = 0;

    private static AtomicInteger count = new AtomicInteger();
    // 加锁的方式一
    private static ReentrantLock lock = new ReentrantLock();
    // 方式二，采用java.util.concurrent.atomic 包下面提供的一些原子操作类
    // 如 int 可以使用 AtomicInteger
    private static ThreadPoolService threadPoolService;

    public void execute(final ThreadPoolEvent event){
        if (event == null){
            LOGGER.info("发布事件 event={} 不能为空！",event);
            return;
        }

        if (StringUtils.isEmpty(event.getThreadName())){
            throw new RuntimeException("执行的线程池名称不能为空！");
        }

        ThreadPoolExecutor executor = ThreadPoolFactory.getThreadPool(event.getThreadName());

        executor.execute(() ->{
            event.executeEvent();
            count.addAndGet(1);
        });
    }

    public int getCount() {
        return count.get();
    }

    public static ThreadPoolService getThreadPoolService(){
        threadPoolService = Single.NEWSTANCE;
        return threadPoolService;
    }

    private static final class Single{
        private static final ThreadPoolService NEWSTANCE = new ThreadPoolService();
    }
}
