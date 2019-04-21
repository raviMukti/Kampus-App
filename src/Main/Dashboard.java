
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
public class Dashboard extends Application{
    
    @Override
    public void start(Stage dashboardStage) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Dashboard.FXML"));
        Scene scene = new Scene(root);
        dashboardStage.setTitle("Kampus Lite - Dashboard");
        dashboardStage.setScene(scene);
        dashboardStage.setResizable(false);
        dashboardStage.setOnCloseRequest((event) -> {
            event.consume();
        });
        dashboardStage.show();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
