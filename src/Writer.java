import java.io.BufferedReader;
import java.io.IOException;

public class Writer extends Thread {
    private BufferedReader in;

    public Writer(BufferedReader in) {
        this.in = in;
    }

    public void run() {
        try {
            String serverWord = in.readLine();
            System.out.println(serverWord);
        } catch (IOException e) {

        }
    }
}
