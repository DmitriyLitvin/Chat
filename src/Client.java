import java.io.*;
import java.net.Socket;

public abstract class Client implements Sendable {
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedReader in;
    private BufferedWriter out;

    {
        try {
            clientSocket = new Socket("localhost", 8080);
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {

        }
    }

    public void send() {
        try {
            try {
                System.out.println("Вы что-то хотели сказать? Введите это здесь:");

                while (true) {
                    new Reader(reader, out).start();
                    new Writer(in).start();
                }
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
