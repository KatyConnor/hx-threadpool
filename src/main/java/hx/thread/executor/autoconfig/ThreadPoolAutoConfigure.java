package hx.thread.executor.autoconfig;

import hx.thread.executor.pool.ThreadPoolService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 自动装配
 *
 * @Author mingliang
 * @Date 2018-01-11 17:11
 */
@Configuration
@ConditionalOnClass(ThreadPoolService.class)
public class ThreadPoolAutoConfigure {

    @Bean
    @ConditionalOnClass(ThreadPoolService.class)
    public ThreadPoolService initThreadPoolService(){
        return new ThreadPoolService();
    }
}
