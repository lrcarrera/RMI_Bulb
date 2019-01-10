import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class BombillaRMIClient{


    public static void main(String args[])
    {
      System.out.println("Buscar el servicio BombillaRMI");
      try {
      // Comprobar si se ha especificado la direccion del servicio de registros
          String registry = "localhost";
          if (args.length >=1) registry = args[0];
          // Formatear la url del registro
          String registro ="rmi://" + registry + "/BombillaRMI";
          // Buscar el servicio en el registro.
          Remote servicioRemoto = Naming.lookup(registro);
          // Convertir a un interfaz BombillaRMI
          BombillaRMI servicioBombilla = (BombillaRMI) servicioRemoto;

          // Encender la bombilla
          System.out.println("Invocando servicioBombilla.on()"); servicioBombilla.on();
          // Mirar si el estado ha cambiado
          System.out.println("Estado bombilla: " + servicioBombilla.isOn() );
          // Ahorrar energia -> Apagar la bombilla
          System.out.println("Invocando servicioBombilla.off()");
          servicioBombilla.off();
          // Mirar si el estado ha cambiado
          System.out.println("Estado bombilla: " + servicioBombilla.isOn() );

          servicioBombilla.setTemperature(50.30);
          servicioBombilla.setConsumption(5000.00);

          System.out.println("Consumo bombilla: " + String.valueOf(servicioBombilla.checkConsumption()) );
          System.out.println("Temperature bombilla: " + String.valueOf(servicioBombilla.checkTemperature()));

      } catch (NotBoundException nbe) {
          System.err.println("No existe el servicio de bombilla en el registro!");
      } catch (RemoteException re) {
          System.err.println("Error Remoto - " + re);
      } catch (Exception e) {
          System.err.println("Error - " + e);
      }
    }
}
