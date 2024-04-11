import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServicioGastosImpl extends UnicastRemoteObject implements ServicioGastos {
    private PrintWriter fd;
    private String nombreFichero;

    public ServicioGastosImpl() throws RemoteException {
        super();
    }

    @Override
    public void registrarGasto(Gastos g) throws RemoteException {
        try {
            // Obtener el nombre del fichero del objeto Gastos
            String nombreFichero = g.obtenerNombreFichero();
            // Crear o abrir el fichero para escritura
            fd = new PrintWriter(new FileWriter(nombreFichero, true), true);

            // Obtener la fecha actual
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
            String fechaActual = sdf.format(new Date());

            // Construir el mensaje completo
            String mensajeCompleto = fechaActual + " | " + g.toString();

            // Escribir en el fichero
            fd.println(mensajeCompleto);
            fd.flush();

            // También imprimir en la consola del servidor
            System.out.println(mensajeCompleto);
        } catch (IOException e) {
            // Manejar errores de entrada/salida
            throw new RemoteException("Error al escribir en el fichero", e);
        }
    }

    @Override
    public Gastos obtenerGasto() throws RemoteException {
        // Este método podría implementarse para obtener información de gastos
        // anteriores si es necesario, pero por ahora lo dejaremos vacío
        return null;
    }
}
