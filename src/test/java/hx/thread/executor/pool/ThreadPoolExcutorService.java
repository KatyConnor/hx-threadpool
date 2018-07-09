package hx.thread.executor.pool;

import hx.thread.executor.factory.ThreadPoolFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Author mingliang
 * @Date 2018-01-17 17:31
 */
@Component
public class ThreadPoolExcutorService {


    private static Map<String,ThreadPoolManageEntity> entityMap;

    static {
        entityMap = new LinkedHashMap<>();
        entityMap.put("test",new ThreadPoolManageEntity("test",50,100,60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(100),new RefuseThreadPoolHandler()));
        entityMap.put("test2",new ThreadPoolManageEntity("test2",50,100,60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(100),new RefuseThreadPoolHandler()));
    }

    public void initThreadPool(){
        ThreadPoolFactory.addThreadPool(entityMap.get("test"));
        ThreadPoolFactory.addThreadPool(entityMap.get("test2"));
    }
}
