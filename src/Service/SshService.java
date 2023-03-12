package Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import Model.SshUser;

public class SshService {

    private final SshUser user;

    private ChannelSftp sftp;

    public SshService(SshUser user) {
        this.user = user;
    }

    public void connect() {
        try {
            Session session = this.user.makeSession();
            session.connect();
            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
        } catch (JSchException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isConnected(){
        return this.user.isConnected();
    }

    public boolean sftp(String path, String file){
        try {
            this.sftp.put(path,file);
            return true;
        } catch (SftpException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}