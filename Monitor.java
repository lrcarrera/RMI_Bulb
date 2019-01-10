import java.rmi.*;
import java.rmi.server.*;
public class Monitor extends UnicastRemoteObject implements TemperaturaListener, EncendidoListener {
    private static BombillaRMI bombilla; // Objeto remoto.
    // Constructor por defecto para el objeto remoto (Monitor).
    public Monitor() throws RemoteException { // Sin código
    }

    public static void main(String args[]) {
        try {
             String registry = "localhost";
             if (args.length >=1) { registry = args[0]; }
             String registration = "rmi://" + registry + "/BombillaRMIv2";
             // Localizar el servicio en el registro y obtener el servicio remoto.
             Remote remoteService = Naming.lookup ( registration );
             BombillaRMI bombilla = (BombillaRMI) remoteService;

             double reading = bombilla.getTemperatura();
             System.out.println ("Temperatura Original: " + reading);
             // Crear un monitor y registrarlo como listener del objeto remoto.
             boolean status = bombilla.getStatus();
             System.out.println ("Status Original: " + status);
             // Crear un monitor y registrarlo como listener del objeto remoto.

             Monitor monitor = new Monitor();
             bombilla.addTemperaturaListener(monitor);
             bombilla.addEncendidoListener(monitor);


        } catch (NotBoundException nbe) {
            System.out.println ("No sensors available");
        } catch (RemoteException re) {
            System.out.println ("RMI Error - " + re);
        } catch (Exception e) {
            System.out.println ("Error - " + e);
        }
  }
  public void temperaturaChanged (double temperature ) throws RemoteException {
        System.out.println ("Evento Cambio Temperature: " + temperature);
        System.out.println ("Bombilla Encendida: " + bombilla.isOn());
  }

  public void statusChanged(boolean status) throws RemoteException {
        System.out.println ("Estado Cambio en Bombilla: " + status);
  }
}
