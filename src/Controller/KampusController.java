/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KampusDAO;
import Database.DBConfig;
import Model.KampusModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author raviMukti at Aftwork
 */
public class KampusController implements Initializable {

    @FXML
    private Label labelBiodata;
    @FXML
    private Label labelSttb;
    @FXML
    private JFXTextField fieldNama;
    @FXML
    private JFXTextField fieldNpm;
    @FXML
    private JFXTextField fieldTempat;
    @FXML
    private JFXDatePicker fieldTanggal;
    @FXML
    private JFXRadioButton radioPria;
    @FXML
    private JFXRadioButton radioWanita;
    @FXML
    private JFXTextArea fieldAlamat;
    @FXML
    private JFXComboBox<String> comboProdi;
    @FXML
    private JFXComboBox<String> comboJenjang;
    @FXML
    private JFXButton btnSimpan;
    @FXML
    private JFXButton btnBatal;
    @FXML
    private TableView<KampusModel> tableBio;
    @FXML
    private TableColumn<KampusModel, String> nama_col;
    @FXML
    private TableColumn<KampusModel, String> npm_col;
    @FXML
    private TableColumn<KampusModel, String> pob_col;
    @FXML
    private TableColumn<KampusModel, String> dob_col;
    @FXML
    private TableColumn<KampusModel, String> jk_col;
    @FXML
    private TableColumn<KampusModel, String> alamat_col;
    @FXML
    private TableColumn<KampusModel, String> prodi_col;
    @FXML
    private TableColumn<KampusModel, String> jenjang_col;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
        
        // Menambahkan List Prodi ke comboProdi
        ObservableList<String> prodi = FXCollections.observableArrayList(
                "Teknik Informatika",
                "Teknik Industri",
                "DKV"
        );
        
        // Menambahkan List Jenjang ke comboJenjang
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
    private void tooltipNpm(MouseEvent event) {
        // Memberikan Tooltip saat mouse klik ke field NPM
        Tooltip tooltipAngka = new Tooltip("Hanya boleh Angka");
        fieldNpm.setTooltip(tooltipAngka);
    }

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
    private void simpanBio(ActionEvent event) throws SQLException, ClassNotFoundException{
        // Mengecheck Semua Field Telah terisi
        if(fieldNama.getText().isEmpty() || fieldNpm.getText().isEmpty() || 
           fieldTempat.getText().isEmpty() || fieldTanggal.getValue().toString().isEmpty() ||
           fieldAlamat.getText().isEmpty()){
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
            KampusDAO.insertMhs(fieldNama.getText(), fieldNpm.getText(), fieldTempat.getText(), fieldTanggal.getValue(),
                    jk, fieldAlamat.getText(), comboProdi.getValue(), comboJenjang.getValue());
            // Menampilkan dialog box informasi
                Alert alertSimpan = new Alert(Alert.AlertType.INFORMATION);
                alertSimpan.setTitle("Kampus App - Informasi");
                alertSimpan.setHeaderText("Informasi Simpan Data");
                alertSimpan.setContentText("Simpan data ke database berhasil !");
                alertSimpan.showAndWait();

                // Memanggil method resetField()
                resetField();
                loadData();
        }
    }

    @FXML
    private void batalBio(ActionEvent event) {
        // Membuat dialog box konfirmasi
        Alert alertBatal = new Alert(Alert.AlertType.CONFIRMATION);
        alertBatal.setTitle("Kampus App - Konfirmasi Batal");
        alertBatal.setHeaderText("Batal Simpan Bio");
        alertBatal.setContentText("Apakah anda yakin akan membatalkan transaksi?");
        Optional<ButtonType> konfirmasiBatal = alertBatal.showAndWait();
        if(konfirmasiBatal.get() == ButtonType.OK){
            System.exit(1);
        }
    }
    
    private void resetField(){
        fieldNama.setText("");
        fieldNpm.setText("");
        fieldTempat.setText("");
        fieldTanggal.getEditor().clear();
        radioPria.setSelected(true);
        fieldAlamat.setText("");
        comboProdi.setValue("Teknik Informatika");
        comboJenjang.setValue("S1");
    }

   //Inisialisasi Retrieve data ke Tableview   
   private void initCol(){
       nama_col.setCellValueFactory(new PropertyValueFactory<>("nama_mhs"));
       npm_col.setCellValueFactory(new PropertyValueFactory<>("npm_mhs"));
       pob_col.setCellValueFactory(new PropertyValueFactory<>("tempat_mhs"));
       dob_col.setCellValueFactory(new PropertyValueFactory<>("tanggal_mhs"));
       jk_col.setCellValueFactory(new PropertyValueFactory<>("jk_mhs"));
       alamat_col.setCellValueFactory(new PropertyValueFactory<>("alamat_mhs"));
       prodi_col.setCellValueFactory(new PropertyValueFactory<>("prodi_mhs"));
       jenjang_col.setCellValueFactory(new PropertyValueFactory<>("jenjang_mhs"));
   }
   //Untuk retrieve data dari database dan menampilkannya ke tableview
   private void loadData(){
       ObservableList<KampusModel> oblist = FXCollections.observableArrayList();
       
       oblist.clear();
       
        try {
            Connection conn = DBConfig.getConnection();
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM bio_mhs");
            
            while (result.next()) {                
                oblist.add(new KampusModel(result.getString("nama_mhs"), result.getString("npm_mhs"), 
                        result.getString("tempat_lahir"), result.getString("tgl_lahir"), 
                        result.getString("jenis_kelamin"), result.getString("alamat_mhs"),
                        result.getString("prodi_mhs"), result.getString("jenjang_mhs")));
            }
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan "+ e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KampusController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableBio.setItems(oblist);
   }
   
}
