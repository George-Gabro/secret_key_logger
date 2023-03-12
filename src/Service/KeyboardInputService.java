package Service;

import Exceptions.InvalidFileException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class KeyboardInputService implements NativeKeyListener {

    private final File file;
    private final BufferedWriter bufferedWriter;

    public KeyboardInputService(String fileName) throws InvalidFileException {
        this.file = new File(fileName);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            throw new InvalidFileException();
        }
        bufferedWriter = new BufferedWriter(fileWriter);
    }

    public String getFilePath() {
        return this.file.getAbsolutePath();
    }

    public String getFileName(){
        return this.file.getName();
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        try {
            this.bufferedWriter.write(nativeKeyEvent.getKeyChar());
            this.bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
