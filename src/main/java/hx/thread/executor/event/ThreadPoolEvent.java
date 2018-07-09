package hx.thread.executor.event;

/**
 * 事件基类
 * @Author mingliang
 * @Date 2017-12-29 11:27
 */
public abstract class ThreadPoolEvent<T> {

    private String threadName;

    private T t;

    public ThreadPoolEvent(T t){
        this.t = t;
    }

    public abstract void executeEvent();

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
