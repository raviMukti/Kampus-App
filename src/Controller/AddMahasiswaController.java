
package Controller;

import DAO.KampusDAO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author raviMukti at Aftwork
 */
public class AddMahasiswaController implements Initializable {

    @FXML
    private JFXTextField fieldNama;
    @FXML
    private JFXTextField fieldNpm;
    @FXML
    private JFXTextField pob;
    @FXML
    private DatePicker dob;
    @FXML
    private JFXTextArea alamat;
    @FXML
    private JFXComboBox<String> comboProdi;
    @FXML
    private JFXComboBox<String> comboJenjang;
    @FXML
    private Button simpanBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private JFXRadioButton radioPria;
    @FXML
    private JFXRadioButton radioWanita;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inisialisasi Array Combobox
        ObservableList<String> prodi = FXCollections.observableArrayList(
                "Teknik Informatika",
                "Teknik Industri",
                "DKV"
        );
        
        ObservableList<String> jenjang = FXCollections.observableArrayList(
                "D1",
                "D2",
                "D3",
                "D4",
                "S1",
                "S2"
        );
        
        // Menampilkan List ke comboProdi
        comboProdi.setItems(prodi);
        //Default comboProdi
        comboProdi.setValue("Teknik Informatika");
        
        // Menampilkan List ke comboProdi
        comboJenjang.setItems(jenjang);
        //Default comboProdi
        comboJenjang.setValue("S1");
        
        // Togglegroup untuk RadioButton
        ToggleGroup radioJk = new ToggleGroup();
        radioPria.setToggleGroup(radioJk);
        radioWanita.setToggleGroup(radioJk);
        // Membuat default RadioButton
        radioPria.setSelected(true);
        
    }    

    @FXML
    private void simpanBtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
            // Mengecheck Semua Field Telah terisi
        if(fieldNama.getText().isEmpty() || fieldNpm.getText().isEmpty() || 
           pob.getText().isEmpty() || dob.getValue().toString().isEmpty() ||
           alamat.getText().isEmpty()){
           Alert fieldKosong = new Alert(Alert.AlertType.WARNING);
           fieldKosong.setTitle("Perhatian");
           fieldKosong.setHeaderText("Form Kosong");
           fieldKosong.setContentText("Harap Mengisi Semua Field !!!");
           fieldKosong.showAndWait();
        }else{
            String jk = "";
            if (radioPria.isSelected()) {
                jk = radioPria.getText();
            }
            if (radioWanita.isSelected()) {
                jk = radioWanita.getText();
            }
            KampusDAO.insertMhs(fieldNama.getText(), fieldNpm.getText(), pob.getText(), dob.getValue(),
                    jk, alamat.getText(), comboProdi.getValue(), comboJenjang.getValue());
            // Menampilkan dialog box informasi
                Alert alertSimpan = new Alert(Alert.AlertType.INFORMATION);
                alertSimpan.setTitle("Kampus App - Informasi");
                alertSimpan.setHeaderText("Informasi Simpan Data");
                alertSimpan.setContentText("Simpan data ke database berhasil !");
                alertSimpan.showAndWait();

                // Memanggil method resetField()
                DashboardController.getInstance().loadData();
                simpanBtn.getScene().getWindow().hide();
        }
    }

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        // Membuat dialog box konfirmasi
        Alert alertBatal = new Alert(Alert.AlertType.CONFIRMATION);
        alertBatal.setTitle("Kampus App - Konfirmasi Batal");
        alertBatal.setHeaderText("Batal Simpan Bio");
        alertBatal.setContentText("Apakah anda yakin akan membatalkan transaksi?");
        Optional<ButtonType> konfirmasiBatal = alertBatal.showAndWait();
        if(konfirmasiBatal.get() == ButtonType.OK){
            cancelBtn.getScene().getWindow().hide();
        }
    }
    
//    private void resetField(){
//        fieldNama.setText("");
//        fieldNpm.setText("");
//        pob.setText("");
//        dob.getEditor().clear();
//        radioPria.setSelected(true);
//        alamat.setText("");
//        comboProdi.setValue("Teknik Informatika");
//        comboJenjang.setValue("S1");
//    }

    @FXML
    private void validasiNpm(KeyEvent event) {
        // Hanya membolehkan inputan Angka / Numerik
       fieldNpm.textProperty().addListener((observable, oldValue, newValue) -> {
           if(!newValue.matches("\\d*")){
              fieldNpm.setText(newValue.replaceAll("[^\\d]", ""));
           }
       });
    }

    @FXML
    private void tooltipNpm(MouseEvent event) {
        // Memberikan Tooltip saat mouse klik ke field NPM
        Tooltip tooltipAngka = new Tooltip("Hanya boleh Angka");
        fieldNpm.setTooltip(tooltipAngka);
    }
    
}
