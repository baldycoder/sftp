package demo;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Lock;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/11 00:19
 * @description：滚动处理队列
 * @modified By：`
 * @version: 1.0
 */

public class PollingThread extends Thread implements Runnable {
    public static Queue<Message> queue = new LinkedTransferQueue<Message>();
    @Override
    public void run() {
        while (true) {
            while (!queue.isEmpty()) {
                queue.poll().display();
            }
            //把队列中的消息全部打印完之后让线程阻塞
            synchronized (Lock.class)
            {
                try {
                    Lock.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
