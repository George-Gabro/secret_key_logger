package model;

public class SshUser {

    private final String name;
    private final String password;

    private final int port;

    public SshUser(String name, String password, int port) {
        this.name = name;
        this.password = password;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
