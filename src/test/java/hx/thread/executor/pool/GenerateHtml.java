package hx.thread.executor.pool;


import hx.thread.executor.event.ThreadPoolEvent;

/**
 * @Author mingliang
 * @Date 2017-12-29 16:23
 */
public class GenerateHtml extends ThreadPoolEvent<String> {

    public GenerateHtml(String s) {
        super(s);
    }

    @Override
    public void executeEvent() {
        System.out.println(super.getT());
    }
}
