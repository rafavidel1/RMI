import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class FabricaGastosImpl extends UnicastRemoteObject implements FabricaGastos {
    private ServicioGastos servicioGastos;

    FabricaGastosImpl() throws RemoteException {
        servicioGastos = new ServicioGastosImpl(); // Crear instancia del servicio de gastos
    }

    public synchronized void registrarGasto(String nombreFichero, String nombre, String concepto, String precio) throws RemoteException {
        servicioGastos.registrarGasto(new Gastos(nombreFichero, nombre, concepto, precio));
    }

    public ServicioGastos obtenerServicioGastos() {
        return servicioGastos; // Devolver instancia del servicio de gastos
    }
}
