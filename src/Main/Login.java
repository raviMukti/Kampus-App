
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
public class Login extends Application{
    
    @Override
    public void start(Stage loginStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        
        Scene scene = new Scene(root);
        loginStage.setTitle("Kampus Lite Login");
        loginStage.setScene(scene);
        loginStage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
