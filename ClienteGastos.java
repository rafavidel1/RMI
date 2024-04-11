import java.rmi.*;
import java.util.*;

public class ClienteGastos {
    static public void main(String args[]) {
        if (args.length != 3) {
            System.err.println("\u001B[31mUso: ClienteGastos <hostregistro> <numPuertoRegistro> <nombreFichero>\u001B[0m");
            return;
        }

        try {
            String url = "//" + args[0] + ":" + args[1] + "/Gastos";
            FabricaGastos srv = (FabricaGastos) Naming.lookup(url);

            // Obtener el nombre del fichero
            String nombreFichero = args[2];

            // Crear un objeto Scanner para leer la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            // Pedir al usuario su nombre
            String nombre = "";
            while (nombre.isEmpty()) {
                System.out.print("\u001B[34mDiga su nombre: \u001B[0m");
                nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.err.println("\u001B[31mPor favor, rellene el campo. Es obligatorio.\u001B[0m");
                }
            }

            boolean primeraVez = true;

            while (true) {
                if (primeraVez) {
                    System.out.println("\u001B[34mBIENVENIDO " + nombre + "!\nQUE DESEA HACER HOY\n--------------------------------------------------------------------------------------------------");
                    mostrarMenu();
                    primeraVez = false;
                } else {
                    System.out.println("\u001B[34m¿Qué otra cosa desea hacer?\n--------------------------------------------------------------------------------------------------");
                    mostrarMenu();
                }

                System.out.print("Elija una opción: ");
                String opcion = scanner.nextLine().trim();

                switch (opcion) {
                    case "1":
                        agregarGasto(scanner, srv, nombreFichero, nombre);
                        break;
                    case "2":
                        verGastos(scanner, srv, nombre);
                        break;
                    case "3":
                        System.out.println("\u001B[34m¡Hasta luego!\u001B[0m");
                        return;
                    default:
                        System.err.println("\u001B[31mOpción no válida. Por favor, seleccione 1, 2 o 3.\u001B[0m");
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("Excepción en ClienteGastos:");
            e.printStackTrace();
        }
    }

    static private void mostrarMenu() {
        System.out.println("[1]-->Añadir un nuevo gasto");
        System.out.println("[2]-->Ver todos mis gastos");
        System.out.println("[3]-->Salir");
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    static private void agregarGasto(Scanner scanner, FabricaGastos srv, String nombreFichero, String nombre) {
        try {
            clearScreen();
            // Pedir al usuario el gasto que ha tenido
            String precio = "";
            while (!precio.matches("\\d+")) {
                System.out.print("\u001B[34mDiga el gasto que ha tenido (Cuanto dinero): \u001B[0m");
                precio = scanner.nextLine().trim();
                if (!precio.matches("\\d+")) {
                    System.err.println("\u001B[31mPor favor, escriba el precio correctamente.\u001B[0m");
                }
            }

            // Pedir al usuario el concepto del gasto
            String concepto = "";
            while (concepto.isEmpty()) {
                System.out.print("\u001B[34mEstablezca un concepto: \u001B[0m");
                concepto = scanner.nextLine().trim();
                if (concepto.isEmpty()) {
                    System.err.println("\u001B[31mPor favor, rellene el campo. Es obligatorio.\u001B[0m");
                }
            }

            // Llamar al método registrarGasto con los datos del usuario
            ServicioGastos servicioGastos = srv.obtenerServicioGastos();
            servicioGastos.registrarGasto(new Gastos(nombreFichero, nombre, concepto, precio));

        } catch (Exception e) {
            System.err.println("Excepción al agregar gasto:");
            e.printStackTrace();
        }
    }
static private void verGastos(Scanner scanner, FabricaGastos srv, String nombre) {
    try {
        clearScreen();
        // Llamar al método para obtener todos los gastos
        ServicioGastos servicioGastos = srv.obtenerServicioGastos();
        List<Gastos> gastos = servicioGastos.obtenerGastosPorNombre(nombre);
        
        // Mostrar los gastos obtenidos en otro color
        System.out.println("\u001B[36mTodos tus gastos:\u001B[0m");
        for (Gastos gasto : gastos) {
            System.out.println(gasto);
        }

        // Obtener la suma total de los gastos de la persona
        double sumaTotalGastos = servicioGastos.obtenerSumaTotalGastosPorNombre(nombre);
        // Mostrar la suma total de los gastos
        System.out.println("\nLa suma total de tus gastos es: $" + sumaTotalGastos);

    } catch (Exception e) {
        System.err.println("Excepción al ver gastos:");
        e.printStackTrace();
    }
}


static private void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
}
}
