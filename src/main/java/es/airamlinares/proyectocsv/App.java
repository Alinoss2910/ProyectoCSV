package es.airamlinares.proyectocsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    static String provinciaActual = "";
    static int cont = 0;
    static String valorCombo;
    @Override
    public void start(Stage stage) {
        VBox paneRoot = new VBox(20);
        paneRoot.setAlignment(Pos.TOP_CENTER);
        var scene = new Scene(paneRoot, 640, 480);
        stage.setScene(scene);
        stage.show();
        
        leerFichero(paneRoot);
        guardar(paneRoot);
    }

    public static void main(String[] args) {
        launch();
    }
    
    static public void deplegable(VBox paneRoot, ArrayList<String> lista) {
        //COMBOBOX
        // Crear un ComboBox con el contenido de la lista
        ComboBox<String> comboBox = new ComboBox(FXCollections.observableList(lista));
        comboBox.setPromptText("Selecciona Provincia");
        paneRoot.getChildren().add(comboBox);

        // Añadir un label en el que se mostrará el elemento seleccionado
        Label seleccionado = new Label();
        Label esp = new Label();
        paneRoot.getChildren().add(seleccionado);
        paneRoot.getChildren().add(esp);

        // Cuando el usuario seleccione algo del ComboBox, se mostrará en el Label
        comboBox.setOnAction((t) -> {
            valorCombo = comboBox.getValue();
            Calculos calc = new Calculos();
            calc.media();
            seleccionado.setText("Media de paro en " + valorCombo + ": " + calc.mediaProv);
            esp.setText("Media de paro en España por cada ciudad/pueblo: " + calc.mediaEsp);
        });
    }
    private void leerFichero(VBox paneRoot) {
        String nombreFichero = "ParoEspaña.csv";
        // Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            // Crear un ArrayList con el contenido que se desee para el ComboBox
            ArrayList<String> lista = new ArrayList();
            // Crear un objeto BufferedReader al que se le pasa 
            //   un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(nombreFichero));
            // Leer la primera línea, guardando en un String
            String texto = br.readLine();
            // Repetir mientras no se llegue al final del fichero
            while(texto != null) {
                Dato dato = new Dato();
                String[] valores = texto.split(";");
                dato.setProvincia(valores[3]);
                if(cont <= 19) {
                    if(!provinciaActual.equals(dato.getProvincia()) && !dato.getProvincia().equals("Comunidad Autónoma")) {
                        lista.add(provinciaActual);
                        provinciaActual = dato.getProvincia();
                        cont++;
                    }
                }
                // Leer la siguiente línea
                texto = br.readLine();
            }
            lista.remove(0);
            deplegable(paneRoot, lista);
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
    }
    private void guardar(VBox paneRoot) {
        Button guardar = new Button("Guardar");
        paneRoot.getChildren().add(guardar);
        guardar.setOnAction(t -> { 
            Exportacion.exportarCSV();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos Guardados");
            alert.setHeaderText("Se han guardado exitosamente en el fichero");
            alert.setContentText("Se han escrito los datos correctamente de su seleccion" );
            alert.showAndWait();
        });
        
    }
}
