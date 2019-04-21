
package Controller;

import DAO.KampusDAO;
import Database.DBConfig;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private void updateBtnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Mengecheck Semua Field Telah terisi
        if(fieldEditNama.getText().isEmpty() || fieldEditNpm.getText().isEmpty() || 
           pobEdit.getText().isEmpty() || dobEdit.getValue().toString().isEmpty() ||
           alamatEdit.getText().isEmpty()){
           Alert fieldKosong = new Alert(Alert.AlertType.WARNING);
           fieldKosong.setTitle("Perhatian");
           fieldKosong.setHeaderText("Form Kosong");
           fieldKosong.setContentText("Harap Mengisi Semua Field !!!");
           fieldKosong.showAndWait();
        }else{
            String jk = "";
            if (radioPriaEdit.isSelected()) {
                jk = radioPriaEdit.getText();
            }
            if (radioWanitaEdit.isSelected()) {
                jk = radioWanitaEdit.getText();
            }
            KampusDAO.updateMhs(fieldEditNama.getText(), fieldEditNpm.getText(), pobEdit.getText(), dobEdit.getValue(),
                    jk, alamatEdit.getText(), fieldEditNpm.getText());
            // Menampilkan dialog box informasi
                Alert alertSimpan = new Alert(Alert.AlertType.INFORMATION);
                alertSimpan.setTitle("Kampus App - Informasi");
                alertSimpan.setHeaderText("Informasi Update Data");
                alertSimpan.setContentText("Simpan data ke database berhasil !");
                alertSimpan.showAndWait();

                // Memanggil method
                DashboardController.getInstance().loadData();
                updateBtn.getScene().getWindow().hide();
        }
    }

    @FXML
    private void batalBtnAction(ActionEvent event) {
        // Membuat dialog box konfirmasi
        Alert alertBatal = new Alert(Alert.AlertType.CONFIRMATION);
        alertBatal.setTitle("Kampus App - Konfirmasi Batal");
        alertBatal.setHeaderText("Batal Simpan Bio");
        alertBatal.setContentText("Apakah anda yakin akan membatalkan transaksi?");
        Optional<ButtonType> konfirmasiBatal = alertBatal.showAndWait();
        if(konfirmasiBatal.get() == ButtonType.OK){
            batalBtn.getScene().getWindow().hide();
    }
    }    
    //   Method untuk mengambil data dari selected tableview dari DashboardController.java
     public void initData(String sql) throws SQLException, ClassNotFoundException{
         try {
             Connection conn = DBConfig.getConnection();
             ResultSet result = conn.createStatement().executeQuery("SELECT * FROM bio_mhs WHERE npm_mhs = '"+sql+"'");
             while (result.next()) {                 
                 String add1 = result.getString("nama_mhs");
                 fieldEditNama.setText(add1);
                 String add2 = result.getString("npm_mhs");
                 fieldEditNpm.setText(add2);
                 String add3 = result.getString("tempat_lahir");
                 pobEdit.setText(add3);
                 LocalDate add4 = result.getDate("tgl_lahir").toLocalDate();
                 dobEdit.setValue(add4);
                 if ("Pria".equals(result.getString("jenis_kelamin"))) {
                     radioPriaEdit.setSelected(true);
                 }else{
                     radioWanitaEdit.setSelected(true);
                 }
                 String add5 = result.getString("alamat_mhs");
                 alamatEdit.setText(add5);
             }
         }catch (SQLException e) {
             System.out.println("Ada Error ya" + e);
         }
    }
} 
    

