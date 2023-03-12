package App;

import Exceptions.CouldNotConnectToSshServerException;
import Service.SshService;
import Model.SshUser;
public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (CouldNotConnectToSshServerException e) {
            System.out.println("Invalid ssh credentials");
        }
    }

    public void run() throws CouldNotConnectToSshServerException {
        SshService sshService = new SshService(
                new SshUser(
                        "ubuntu",
                        "www.ghabro.nl",
                        "Jabha123@webserver",
                        22
                )
        );

        sshService.connect();
        if(!sshService.isConnected()) throw new CouldNotConnectToSshServerException();
        System.out.println("Successfully connected");
    }
}
