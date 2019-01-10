import java.rmi.*;

public interface BombillaRMI extends java.rmi.Remote
{

  public void on() throws RemoteException;
  public void off() throws RemoteException;
  public boolean isOn() throws RemoteException;

  // Métodos remotos para soportar los listeners y las callbacks de Temperatura
  public double getTemperatura() throws RemoteException;
  public void addTemperaturaListener ( TemperaturaListener listener ) throws RemoteException;
  public void removeTemperaturaListener ( TemperaturaListener listener )throws RemoteException;



}
