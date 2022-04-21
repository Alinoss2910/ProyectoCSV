package es.airamlinares.proyectocsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox paneRoot = new VBox(20);
        paneRoot.setAlignment(Pos.TOP_CENTER);
        var scene = new Scene(paneRoot, 640, 480);
        stage.setScene(scene);
        stage.show();
        
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
                String[] valores = texto.split(";");
                String provincia = valores[4];
                System.out.println(provincia);
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
        desplegable(paneRoot);
    }

    public static void main(String[] args) {
        launch();
    }

    
    private void desplegable(VBox paneRoot) {
        
        
        
        //COMBOBOX
        // Crear un ArrayList con el contenido que se desee para el ComboBox
        ArrayList<String> lista = new ArrayList();
        lista.add("Uno");
        lista.add("Dos");
        lista.add("Tres");
        lista.add("Cuatro");
        lista.add("Cinco");

        // Crear un ComboBox con el contenido de la lista
        ComboBox<String> comboBox = new ComboBox(FXCollections.observableList(lista));
        comboBox.setPromptText("Selecciona Provincia");
        paneRoot.getChildren().add(comboBox);

        // Añadir un label en el que se mostrará el elemento seleccionado
        Label seleccionado = new Label();
        paneRoot.getChildren().add(seleccionado);

            // Cuando el usuario seleccione algo del ComboBox, se mostrará en el Label
        comboBox.setOnAction((t) -> {
            seleccionado.setText(comboBox.getValue());
        });
    }
}
