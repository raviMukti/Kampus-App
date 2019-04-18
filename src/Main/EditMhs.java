
package Main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author raviMukti at Aftwork
 */
public class EditMhs extends Application{
    
    @Override
    public void start(Stage editStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/EditMahasiswa.FXML"));
        
        Scene scene = new Scene(root);
        editStage.setTitle("Edit Mahasiswa");
        editStage.setScene(scene);
        editStage.show();
    }
    
    public static void main(String[] args) {
           launch(args);
    }
}
