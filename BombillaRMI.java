import java.rmi.*;

public interface BombillaRMI extends java.rmi.Remote
{
  public void on() throws RemoteException;
  public void off() throws RemoteException;
  public boolean isOn() throws RemoteException;

  /*Añadir nuevos métodos remotos para consultar y asignar el consumo de la
  bombilla y su temperatura. */
  public double checkConsumption() throws RemoteException;
  public void setConsumption(double consumption) throws RemoteException;
  public double checkTemperature() throws RemoteException;
  public void setTemperature(double temperature) throws RemoteException;

  // Métodos remotos para soportar los listeners y las callbacks de Temperatura
  public double getTemperatura() throws RemoteException;
  public void addTemperaturaListener ( TemperaturaListener listener ) throws RemoteException;
  public void removeTemperaturaListener ( TemperaturaListener listener )throws RemoteException;

  public boolean getStatus() throws RemoteException;
  public void addEncendidoListener ( EncendidoListener listener ) throws RemoteException;
  public void removeEncendidoListener ( EncendidoListener listener )throws RemoteException;

}
