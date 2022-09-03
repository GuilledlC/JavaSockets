import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends SendReceive {
    protected String ip;
    public Client() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What IP do you wish to connect to?" +
                " (\"localhost\" for own IP)");
        ip = scanner.nextLine();
        System.out.println("What port do you wish to connect to?");
        port = scanner.nextInt();
        System.out.println("What do you wish to call yourself?");
        name = scanner.next();
        System.out.println();

        socket = new Socket(ip, port);
    }
}
