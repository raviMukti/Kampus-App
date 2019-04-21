
package Controller;

import DAO.KampusDAO;
import Database.DBConfig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;


public class LoginController implements Initializable {

    @FXML
    private JFXTextField fieldUser;
    @FXML
    private JFXPasswordField fieldPass;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private ImageView imageLoad;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageLoad.setVisible(false);
    }    
    
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
    
    @FXML
    private void btnLoginAction(ActionEvent event) throws IOException, ClassNotFoundException {
        int count = 0;
        String userLogin = fieldUser.getText().toString().toLowerCase();
        String passLogin = fieldPass.getText().toString().toLowerCase();
        String query = "SELECT * FROM `bio_user` WHERE `username` = ? AND `password` = ?";
        try {
            conn = DBConfig.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userLogin);
            ps.setString(2, passLogin);
            rs = ps.executeQuery();
            if (rs.next()) {
                imageLoad.setVisible(true);
                PauseTransition pt = new PauseTransition();
                pt.setDuration(Duration.millis(2000));
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/View/Dashboard.fxml"));
                Parent root = (Parent) loader.load();
                DashboardController controll = loader.getController();
                controll.initUser(fieldUser.getText());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setOnCloseRequest((event1) -> {
                    event1.consume();
                });
                stage.show();
                btnLogin.getScene().getWindow().hide();
            }else {
                JOptionPane.showMessageDialog(null, "User/Password salah");
                imageLoad.setVisible(false);
            }
        } catch (SQLException e) {
            System.out.println("Ada kesalahan " + e);
        }
    }    
}
