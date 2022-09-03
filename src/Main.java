import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you wish to start a server (1)" +
                " or connect to one (2)?");
        int choice = scanner.nextInt();

        SendReceive program;

        if(choice == 1)
            program = new Server();
        else
            program = new Client();

        ArrayList<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
        taskList.add(program.send);
        taskList.add(program.receive);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            executorService.invokeAll(taskList);
        } catch(InterruptedException ie) {
            
        }
    }
}
