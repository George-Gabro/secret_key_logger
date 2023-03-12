package Model;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.Properties;

public class SshUser {

    private final String name;
    private final String password;

    private final String host;
    private final int port;

    private Session session;

    public SshUser(String name, String host,String password, int port) {
        this.name = name;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public Session makeSession() throws JSchException {
        JSch jSch = new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        Session session = jSch.getSession(name, host);
        session.setPassword(password);
        session.setPort(port);
        session.setConfig(config);
        this.session = session;
        return session;
    }

    public boolean isConnected() {
        if(session == null) return false;
        return session.isConnected();
    }
}
