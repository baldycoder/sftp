package SFTP.config;

/**
 * @author ：a123145
 * @date ：Created in 2023/3/13 14:06
 * @description：数据库配置信息
 * @modified By：`
 * @version: 1.0
 */

public class SftpConfig {

    private String hostName = "";

    private int port;

    private String username = "";

    private String password = "";

    private String localPath = "";

    private String remotePath = "";



    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }
}
