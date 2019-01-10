import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.lang.Thread;

public class BombillaRMIServant extends UnicastRemoteObject implements BombillaRMI, Runnable
{
  private Vector<TemperaturaListener> listaListeners = new Vector<TemperaturaListener>();
  private Vector<EncendidoListener> listaListeners2 = new Vector<EncendidoListener>();

    private static final long serialVersionUID = 2;
    private boolean luzOn;
    private volatile double temperatura;

    private double consumption;
    private double temperature;

    // Constructor.
    public BombillaRMIServant() throws RemoteException {
        setBombilla(false); // Asignar valor por defecto = off
        setTemperatura(2800);
    }

    public void run() {
        Random r = new Random();
        for (;;) {
            try { // Dormirse durante un número aleatorio de milisegundos.
               int duration = r.nextInt() % 10000 + 2000;
               if (duration < 0) duration = duration * -1; // Si es negativo, invertirlo.
               Thread.sleep(duration);
            } catch (InterruptedException ie) { }
            // Obtener un númbero aleatorioa para comprobar si la temperatura sube o baja.
            int num = r.nextInt();
            System.out.println (" Changing Temperature: " + num);
            if ( num < 0) {
              temperatura += 100;
            }
            else {
              temperatura -= 100;
            }

            notificarListeners(); // Notificar a todos los listeners registrados
        }
    }

    private void notificarListeners() {
// Noficiar a cada listener de la lista de listener registrados.
        for (Enumeration e = listaListeners.elements(); e.hasMoreElements(); )
        {
            TemperaturaListener listener = (TemperaturaListener) e.nextElement();
        // Notificar al listener, si es posible.
        try {
         listener.temperaturaChanged(this.temperatura);
        }
        catch (RemoteException re) {
         System.out.println (" Listener not accessible, removing listener -" + listener);
         // Listener no accesible -> Eliminar el listener de la lista.
         listaListeners.remove( listener );
        }
        }
  }


    public void addTemperaturaListener ( TemperaturaListener listener ) throws RemoteException {
        System.out.println ("adding listener -" + listener);
        listaListeners.add (listener);
    }

    public void removeTemperaturaListener ( TemperaturaListener listener ) throws RemoteException {
        System.out.println ("removing listener -" + listener);
        listaListeners.remove (listener);
    }

    public void addEncendidoListener ( EncendidoListener listener ) throws RemoteException {
        System.out.println ("adding listener -" + listener);
        listaListeners2.add (listener);
    }

    public void removeEncendidoListener ( EncendidoListener listener ) throws RemoteException {
        System.out.println ("removing listener -" + listener);
        listaListeners2.remove (listener);
    }

    // Método remoto -> Enciende la Bombilla.
    public void on() throws RemoteException {

      setBombilla(true); // Encender Bombilla.
      notifyChangeInStatus(true);
    }

    // Método remoto -> Apagar la Bombilla.
    public void off() throws RemoteException {
        setBombilla(false);
        notifyChangeInStatus(false);
    }

    public void notifyChangeInStatus(boolean status){

      for (Enumeration e = listaListeners2.elements(); e.hasMoreElements(); )
      {
          EncendidoListener listener = (EncendidoListener) e.nextElement();
      // Notificar al listener, si es posible.
      try {
       listener.statusChanged(status);
      }
      catch (RemoteException re) {
       System.out.println (" Listener not accessible, removing listener -" + listener);
       // Listener no accesible -> Eliminar el listener de la lista.
       listaListeners2.remove( listener );
      }
      }
    }

    // Método remoto -> Devuelve el estado de la Bombilla.
    public boolean isOn() throws RemoteException {
        return getBombilla();
    }
    // Métodos locales:
    public void setBombilla(boolean valor) {
        luzOn = valor;
    }

    // Métodos locales:
    public void setTemperatura(double temp) {
        temperatura = temp;
    }

    public boolean getBombilla() {
        return(luzOn);
    }

    public double checkConsumption() throws RemoteException{
      return consumption;
    }
    public double checkTemperature() throws RemoteException{
      return temperature;
    }
    public void setConsumption(double consumption) throws RemoteException{
      this.consumption = consumption;
    }
    public void setTemperature(double temperature) throws RemoteException{
      this.temperature = temperature;
    }

    public boolean getStatus() throws RemoteException{
      return this.luzOn;
    }

    public double getTemperatura() throws RemoteException{
      return this.temperatura;
    }

}
