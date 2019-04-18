
package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author raviMukti at Aftwork
 */
public class AddMhs extends Application{
    
    @Override
    public void start(Stage addStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddMahasiswa.FXML"));
        
        Scene scene = new Scene(root);
        addStage.setTitle("Add Mahasiswa");
        addStage.setScene(scene);
        addStage.show();
    }
    
    public static void main(String[] args) {
           launch(args);
    }
}
