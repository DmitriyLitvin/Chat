import java.io.*;
import java.net.Socket;

public abstract class Client implements Sendable {
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedReader in;
    private BufferedWriter out;

    private class Writer extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    String serverWord = in.readLine();
                    if (serverWord.equals("stop")) {
                        Client.this.downService();
                        break;
                    }
                    System.out.println(serverWord);
                } catch (IOException e) {
                    downService();
                }
            }
        }
    }

    private class Reader extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    String word = reader.readLine();

                    if (word.equals("stop")) {
                        out.write("stop" + "\n");
                        Client.this.downService();
                        break;
                    } else {
                        out.write(word + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                    downService();
                }
            }
        }
    }

    public void send() {
        try {
            System.out.println("Вы что-то хотели сказать? Введите это здесь:");

            clientSocket = new Socket("localhost", 8080);
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            new Reader().start();
            new Writer().start();
        } catch (IOException e) {
            downService();
        }
    }


    private void downService() {
        try {
            if (!clientSocket.isClosed()) {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {

        }
    }
}
