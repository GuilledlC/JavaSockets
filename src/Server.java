import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server extends SendReceive {
    protected ServerSocket serverSocket;
    public Server() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What port do you wish to host out of?");
        port = scanner.nextInt();
        System.out.println("What do you wish to call yourself?");
        name = scanner.next();
        System.out.println();

        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
    }
}
