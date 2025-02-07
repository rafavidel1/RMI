import java.rmi.*;
import java.rmi.server.*;

class ServidorGastos  {
    static public void main (String args[]) {
       if (args.length!=1) {
            System.err.println("Uso: ServidorGastos numPuertoRegistro");
            return;
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            FabricaGastosImpl srv = new FabricaGastosImpl();
            Naming.rebind("rmi://localhost:" + args[0] + "/Gastos", srv);
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en ServidorGastos:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
