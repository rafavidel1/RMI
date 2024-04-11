import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.text.SimpleDateFormat;
import java.util.*;

class ServicioGastosImpl extends UnicastRemoteObject implements ServicioGastos {
    private PrintWriter fd;
    private Map<String, Double> sumaTotalPorNombre; // Mapa para asociar el nombre con la suma total de los gastos
    private List<Gastos> listaGastos; // Lista para almacenar todos los gastos

    public ServicioGastosImpl() throws RemoteException {
        super();
        sumaTotalPorNombre = new HashMap<>(); // Inicializamos el mapa de suma total por nombre
        listaGastos = new ArrayList<>(); // Inicializamos la lista de gastos
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

            // Construir el mensaje completo con fecha y hora
            String mensajeCompleto = fechaActual + " | " + g.toString();

            // Escribir en el fichero
            fd.println(mensajeCompleto);
            fd.flush();

            // También imprimir en la consola del servidor
            System.out.println(mensajeCompleto);

            // Actualizar la suma total de los gastos para el nombre asociado
            String nombre = g.obtenerNombre();
            double precioGasto = Double.parseDouble(g.obtenerPrecio());
            double sumaTotal = sumaTotalPorNombre.getOrDefault(nombre, 0.0);
            sumaTotal += precioGasto;
            sumaTotalPorNombre.put(nombre, sumaTotal);

            // Agregar el gasto a la lista de gastos
            listaGastos.add(g);

        } catch (IOException e) {
            // Manejar errores de entrada/salida
            throw new RemoteException("Error al escribir en el fichero", e);
        } catch (NumberFormatException e) {
            // Manejar errores de formato de precio
            throw new RemoteException("Formato de precio inválido", e);
        }
    }

    @Override
    public List<Gastos> obtenerGastosPorNombre(String nombre) throws RemoteException {
        List<Gastos> gastosPorNombre = new ArrayList<>();
        for (Gastos gasto : listaGastos) {
            if (gasto.obtenerNombre().equals(nombre)) {
                gastosPorNombre.add(gasto);
            }
        }
        return gastosPorNombre;
    }

    @Override
    public double obtenerSumaTotalGastosPorNombre(String nombre) throws RemoteException {
        return sumaTotalPorNombre.getOrDefault(nombre, 0.0);
    }
}
