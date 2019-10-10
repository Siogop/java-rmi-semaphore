package semaphore;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Semaphore stub = (Semaphore) registry.lookup("Semaphore");

            while(true) {
                System.out.println("Trying semaphore\n");
                stub.P(1);
                System.out.println("Entered critical section\n");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Realesing semaphore\n");
                stub.V(1);
                TimeUnit.SECONDS.sleep(1);
            }
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
