package es.airamlinares.proyectocsv;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Exportacion {
    public static void exportarCSV(){
        String nombreFichero = "media_paro.csv";
        String textoEncabezado = "Provincia,ParoProvincia,ParoEspaña  ";
        String textoDatos = App.valorCombo + "," + Calculos.mediaProv + "," + Calculos.mediaEsp;
        BufferedWriter w = null;
        
        try {
        //Crear un objeto BufferedWriter. Si ya existe el fichero, 
        //  se borra automáticamente su contenido anterior.
            w = new BufferedWriter(new FileWriter(nombreFichero));
        //Escribir en el fichero el texto con un salto de línea
            w.write(textoEncabezado + "\n");
            w.write(textoDatos + "\n");
        }
        // Comprobar si se ha producido algún error
        catch(Exception ex) {
            System.out.println("Error de escritura del fichero");
            ex.printStackTrace();
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(w != null)
                    w.close();
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
    }
}
