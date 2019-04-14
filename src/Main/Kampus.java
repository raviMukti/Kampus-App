/*
 * Project yang saya buat menggunakan JavaFX
 * Ravi Mukti Hartadi - 17111247
 * TIF - CID B
 */
package Main;



import Database.DBConfig;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author raviMukti at Aftwork
 */
public class Kampus extends Application {
    
    @Override
    public void start(Stage stage) throws IOException{
        //Mengambil alamat file FXML di package View
        Parent root = FXMLLoader.load(getClass().getResource("/View/Kampus.FXML"));
        
        Scene scene = new Scene(root);
        //Set judul form
        stage.setTitle("Kampus Lite - Bio");
        //Mencegah Default Tombol X
        stage.setOnCloseRequest((event) -> {
            event.consume();
        });
        //Membuat tidak bisa dimaximize
        stage.setResizable(false);
        stage.setScene(scene);
        //Menampilkan form Bio
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
