package es.airamlinares.proyectocsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Calculos {
    int sumaParoProv;
    int contProv = 0;
    static float mediaProv;
    int contEsp = 0;
    int sumaParoEsp;
    int contLin = 0;
    static float mediaEsp;
    public void media() {
        String nombreFichero = "ParoEspaña.csv";
        // Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            // Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(nombreFichero));
            // Leer la primera línea, guardando en un String
            String texto = br.readLine();
            // Repetir mientras no se llegue al final del fichero
            while(texto != null) {
                contLin++;
                Dato dato = new Dato();
                String[] valores = texto.split(";");
                dato.setProvincia(valores[3]);
                if(contLin > 2) {
                    dato.setParoEsp(Integer.valueOf(valores[8]));
                    sumaParoEsp += dato.getParoEsp();
                    contEsp++;
                }
                if(App.valorCombo.equals(dato.getProvincia())) {
                    //System.out.println(valores[8]);
                    dato.setParo(Integer.valueOf(valores[8]));
                    sumaParoProv += dato.getParo();
                    contProv++;
                }
                // Leer la siguiente línea
                texto = br.readLine();
            }
        }
        // Captura de excepción por fichero no encontrado
        catch (FileNotFoundException ex) {
            System.out.println("Error: Fichero no encontrado");
            ex.printStackTrace();
        }
        // Captura de cualquier otra excepción
        catch(Exception ex) {
            System.out.println("Error de lectura del fichero");
            ex.printStackTrace();
        }
        // Asegurar el cierre del fichero en cualquier caso
        finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(br != null) {
                    br.close();
                }
            }
            catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }
        System.out.println(sumaParoProv);
        System.out.println(contProv);
        mediaProv = sumaParoProv/contProv;
        mediaEsp = sumaParoEsp/contEsp;
        System.out.println(mediaEsp);
    }
}
