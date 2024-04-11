import java.rmi.*;
import java.util.*;

interface FabricaGastos extends Remote {
    void registrarGasto(String nombreFichero, String nombre, String concepto, String precio) throws RemoteException;
    ServicioGastos obtenerServicioGastos() throws RemoteException; // Método para obtener una instancia del servicio de gastos
}
