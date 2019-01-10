import java.rmi.*;
import java.rmi.server.*;
public class BombillaRMIServer {
  public static void main(String args[]) {
    System.out.println("Cargando Servicio RMI");

    try {
    // Crear el servicio.
      BombillaRMIServant servicioBombilla = new BombillaRMIServant();
      // Imprimir la ubicación del servicio.
      RemoteRef location = servicioBombilla.getRef();
      System.out.println(location.remoteToString());
      // Comprobar si se ha expecificado un registro (arg[0])
      String registry = "localhost"; if (args.length >= 1) registry = args[0];
      // Crear la URL del registro.
      String registro ="rmi://" + registry + "/BombillaRMI";
      // Registrar el servicio
      Naming.rebind(registro, servicioBombilla);


      //Invocar servidor rmiregistry
      LocateRegistry.createRegistry (1099);


    //Thread thread = new Thread (servicioBombilla);
      //thread.start();
    }
      catch (RemoteException re) {
      System.err.println("Remote Error - " + re);
    }
      catch (Exception e) {
      System.err.println("Error - " + e);
    }
  }
}
