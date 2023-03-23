package SFTP;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author     ：a123145
 * @date       ：Created in 2023/3/10 11:55
 * @description：SFTP的main函数
 * @modified By：`
 * @version:    1.0
 */

public class ToolMain {

    public static void main(){
        ReentrantLock lock = new ReentrantLock(); //非公平锁
        String[] tableArray = {"A","B","C","D","E"};
        lock.lock();
        try{

        }catch (Exception e){
            // ignored
        }finally {
            lock.unlock();
        }

    }
}
