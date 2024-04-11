import java.rmi.*;
import java.util.*;

interface FabricaGastos extends Remote {
    void registrarGasto(String nombreFichero, String nombre, String concepto, String precio) throws RemoteException;
}
