package demo;

import java.util.concurrent.locks.Lock;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/11 00:17
 * @description：单线程 循环方式
 * @modified By：`
 * @version: 1.0
 */

public class ToolMain {
    public static void main(){
        //滚动线程，里面含有阻塞队列
        PollingThread pollingThread = new PollingThread();
        pollingThread.start();
        int i=1;
        while(true)
        {
            PollingThread.queue.offer(new Message("新消息"+i));
            i++;
            //有消息入队后激活轮询线程
            synchronized (Lock.class)
            {
                Lock.class.notify();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
