import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendReceive {
    protected static Socket socket;
    protected static void sendLoop() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String msg;
        while(!socket.isClosed()) {
            msg = scanner.next();
            send(msg);
        }
    }

    protected static void send(String msg) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println(msg);
        printWriter.flush();
    }

    protected static void receiveLoop(String recipient) throws IOException {
        InputStreamReader input = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(input);
        String msg;
        while(!socket.isClosed()) {
            msg = bf.readLine();
            System.out.println(recipient + ": " + msg);
            if(msg.equals("exit")) {
                send("exit");
                socket.close();
            }
        }
    }
}
