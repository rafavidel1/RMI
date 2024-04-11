import java.rmi.*;

interface ServicioGastos extends Remote {
    void registrarGasto(Gastos g) throws RemoteException; 
    Gastos obtenerGasto() throws RemoteException;
}
