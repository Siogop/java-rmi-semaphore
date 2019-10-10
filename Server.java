package semaphore;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Semaphore {

    public Server() {}

    int count = 1;

    public synchronized Boolean P(int i) {
        
        while (this.count < i) {
            try {
                this.wait();
            } catch(InterruptedException e) {}
        }

        this.count -= i;

        return true;
    }
    public synchronized Boolean V(int i) {

        this.count += i;
        this.notifyAll();

        return true;
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            Semaphore stub = (Semaphore) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Semaphore", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
