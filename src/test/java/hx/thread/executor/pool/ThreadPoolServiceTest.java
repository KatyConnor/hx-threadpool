package hx.thread.executor.pool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author mingliang
 * @Date 2017-12-29 21:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ThreadPoolServiceTest {

    @Test
    public void testPool(){
        ThreadPoolService threadPoolService = ThreadPoolService.getThreadPoolService();
        ThreadPoolExcutorService service = new ThreadPoolExcutorService();
        service.initThreadPool();
        for ( int i = 0; i< 2000; i++ ){
            final int j = i;
            new Thread(() ->{
                GenerateHtml generateHtml = null;
                if (j % 2 == 0){
                    generateHtml = new GenerateHtml("线程池 test2 开始执行事件！"+j);
                    generateHtml.setThreadName("test2");
                }else {
                    generateHtml = new GenerateHtml("线程池 test 开始执行事件！"+j);
                    generateHtml.setThreadName("test");
                }
                threadPoolService.execute(generateHtml);
            }).start();
        }

        while (threadPoolService.getCount() < 2000){
        }
        System.out.println("count = "+threadPoolService.getCount());
    }
}
