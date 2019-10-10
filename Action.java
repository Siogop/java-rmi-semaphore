package semaphore;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Action {

    private Action() {}

    public static void main(String[] args) {

        String host = null;
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Semaphore stub = (Semaphore) registry.lookup("Semaphore");

            switch(args[0]) {
                case "P":
                    System.out.println("Trying semaphore: " + args[1] + "\n");
                    stub.P(Integer.valueOf(args[1]));
                    System.out.println("Done\n");
                    break;
                case "V":
                    System.out.println("Releasing semaphore: " + args[1] + "\n");
                    stub.V(Integer.valueOf(args[1]));
                    System.out.println("Done\n");
                    break;
            }
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
