import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends SendReceive {
    private static ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {

        serverSocket = new ServerSocket(4444);
        socket = serverSocket.accept();
        System.out.println(serverSocket.getLocalSocketAddress().toString());

        Callable<Void> send = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                sendLoop();
                return null;
            }
        };

        Callable<Void> receive = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                receiveLoop("Client");
                return null;
            }
        };

        ArrayList<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
        taskList.add(send);
        taskList.add(receive);

        //create a pool executor with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            executor.invokeAll(taskList);
        } catch (InterruptedException ie)
        {
            //do something if you care about interruption;
        }
    }
}
