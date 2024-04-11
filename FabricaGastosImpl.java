import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class FabricaGastosImpl extends UnicastRemoteObject implements FabricaGastos {
    private List<Gastos> listaGastos;
    private ServicioGastos servicioGastos;

    FabricaGastosImpl() throws RemoteException {
        listaGastos = new LinkedList<>();
        // Aquí no necesitamos pasar argumentos al constructor del ServicioGastosImpl
        servicioGastos = new ServicioGastosImpl();
    }

    public synchronized void registrarGasto(String nombreFichero, String nombre, String concepto, String precio) throws RemoteException {
        Gastos g = new Gastos(nombreFichero, nombre, concepto, precio);
        listaGastos.add(g);
        servicioGastos.registrarGasto(g);
    }
}
