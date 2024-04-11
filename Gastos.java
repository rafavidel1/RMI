import java.io.*;

class Gastos implements Serializable {
     private String nombreFichero;
     private String nombre;
     private String concepto;
     private String precio;
     
     Gastos(String nomFich, String nom, String conc, String prec) {
         nombreFichero = nomFich;
         nombre = nom;
         concepto = conc;
         precio = prec;
     }

     public String obtenerNombreFichero() {
         return nombreFichero;
     }
     
     public String obtenerNombre() {
         return nombre;
     }

     public String obtenerConcepto() {
         return concepto;
     }
     
     public String obtenerPrecio() {
         return precio;
     }

     public String toString() {
         return nombre + " ha gastado " + precio + "$ en " + concepto;
     }
}
