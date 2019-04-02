import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Reader extends Thread {
    private BufferedReader reader;
    private BufferedWriter out;

    public Reader(BufferedReader reader, BufferedWriter out) {
        this.reader = reader;
        this.out = out;
    }

    public void run() {
        try {
            String word = reader.readLine();
            out.write(word + "\n");
            out.flush();
        } catch (IOException e) {

        }

    }


}
