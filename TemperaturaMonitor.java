import java.rmi.*;
import java.rmi.server.*;
public class TemperaturaMonitor extends UnicastRemoteObject implements TemperaturaListener {
    private static BombillaRMI bombilla; // Objeto remoto.
    // Constructor por defecto para el objeto remoto (TemperaturaMonitor).
    public TemperaturaMonitor() throws RemoteException { // Sin código
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
             TemperaturaMonitor monitor = new TemperaturaMonitor();
             bombilla.addTemperaturaListener(monitor);
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
}
