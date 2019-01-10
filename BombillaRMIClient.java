import java.rmi.*;
public class BombillaRMIClient extends RemoteObject{


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
      } catch (NotBoundException nbe) {
          System.err.println("No existe el servicio de bombilla en el registro!");
      } catch (RemoteException re) {
          System.err.println("Error Remoto - " + re);
      } catch (Exception e) {
          System.err.println("Error - " + e);
      }
    }
}
