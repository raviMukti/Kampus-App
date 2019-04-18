
package Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import Controller.DashboardController;
import Model.KampusModel;
import java.time.LocalDate;
/**
 * FXML Controller class
 *
 * @author raviMukti at Aftwork
 */
public class EditMahasiswaController implements Initializable {

    @FXML
    private JFXTextField fieldEditNama;
    @FXML
    private JFXTextField fieldEditNpm;
    @FXML
    private JFXTextField pobEdit;
    @FXML
    private DatePicker dobEdit;
    @FXML
    private JFXTextArea alamatEdit;
    @FXML
    private JFXComboBox<String> comboProdiEdit;
    @FXML
    private JFXComboBox<String> comboJenjangEdit;
    @FXML
    private JFXRadioButton radioPriaEdit;
    @FXML
    private JFXRadioButton radioWanitaEdit;
    @FXML
    private Button updateBtn;
    @FXML
    private Button batalBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void updateBtnAction(ActionEvent event) {
    }

    @FXML
    private void batalBtnAction(ActionEvent event) {
    }
    
    
   private static EditMahasiswaController instance;
   public EditMahasiswaController(){
       instance = this;
   }
//   Method getInstance()
   public static EditMahasiswaController getInstance(){
       return instance;
   }
    
}
