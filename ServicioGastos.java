import java.rmi.*;
import java.util.*;

interface ServicioGastos extends Remote {
    void registrarGasto(Gastos g) throws RemoteException; 
    List<Gastos> obtenerGastosPorNombre(String nombre) throws RemoteException; // Método para obtener gastos por nombre
    double obtenerSumaTotalGastosPorNombre(String nombre) throws RemoteException; // Método para obtener la suma total de gastos por nombre
}
