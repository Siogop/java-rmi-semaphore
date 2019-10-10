package semaphore;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Semaphore extends Remote {
    Boolean P(int i) throws RemoteException;
    Boolean V(int i) throws RemoteException;
}
