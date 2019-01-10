import java.rmi.*;
public interface TemperaturaListener extends java.rmi.Remote
{
  public void temperaturaChanged(double temperatura) throws RemoteException;
}
