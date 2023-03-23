package demo;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/11 00:22
 * @description：
 * @modified By：`
 * @version: 1.0
 */

public class Message {
    private String content;
    public Message(String content)
    {
        this.content=content;
    }
    public void display(){
        System.out.println(content);
    }
}
