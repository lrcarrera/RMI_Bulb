import java.rmi.*;
public interface EncendidoListener extends java.rmi.Remote
{
  public void statusChanged(boolean status) throws RemoteException;
}
