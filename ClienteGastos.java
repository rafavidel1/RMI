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
            srv.registrarGasto(nombreFichero, nombre, concepto, precio);
            
        } catch (Exception e) {
            System.err.println("Excepción en ClienteGastos:");
            e.printStackTrace();
        }
    }
}
