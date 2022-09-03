import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class SendReceive {
    protected Socket socket;
    protected int port;
    protected String name;
    public Callable<Void> send = new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            sendLoop();
            return null;
        }
    };
    public Callable<Void> receive = new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            receiveLoop();
            return null;
        }
    };
    protected void sendLoop() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String msg;
        while(!socket.isClosed()) {
            msg = scanner.nextLine();
            send(msg);
        }
    }

    protected void send(String msg) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(name + ": " + msg);
        printWriter.flush();
    }

    protected void receiveLoop() throws IOException {
        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(input);
        String msg;
        while(!socket.isClosed()) {
            msg = bf.readLine();
            System.out.println(msg);
            if(msg.equals("exit")) {
                send("exit");
                socket.close();
            }
        }
    }
}
