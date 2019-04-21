
package Controller;

import DAO.KampusDAO;
import Database.DBConfig;
import Model.KampusModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raviMukti at Aftwork
 */
public class DashboardController implements Initializable {

    @FXML
    private TableView<KampusModel> dashboardTable;
    @FXML
    private TableColumn<KampusModel, String> nama_col;
    @FXML
    private TableColumn<KampusModel, String> npm_col;
    @FXML
    private TableColumn<KampusModel, String> pob_col;
    @FXML
    private TableColumn<KampusModel, String> dob_col;
    @FXML
    private TableColumn<KampusModel, String> gender_col;
    @FXML
    private TableColumn<KampusModel, String> alamat_col;
    @FXML
    private TableColumn<KampusModel, String> prodi_col;
    @FXML
    private TableColumn<KampusModel, String> jenjang_col;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button delBtn;
    @FXML
    private Button btnExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
        dashboardTable.refresh();
    }    

    @FXML
    private void addBtnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddMahasiswa.FXML"));
        Stage stage = new Stage();
        stage.initOwner(addBtn.getScene().getWindow());
        stage.setScene(new Scene((Parent) loader.load()));
        
        stage.showAndWait();
    }

    @FXML
    private void editBtnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/View/EditMahasiswa.fxml"));
      Parent root = (Parent) loader.load();
      EditMahasiswaController controller = loader.getController();
      controller.initData(dashboardTable.getSelectionModel().getSelectedItem().getNpm_mhs());
      Scene newScene =  new Scene(root);
      Stage newStage = new Stage();
      newStage.initModality(Modality.APPLICATION_MODAL);
      newStage.setScene(newScene);
      newStage.showAndWait();
    }

    @FXML
    private void delBtnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
       KampusModel item = dashboardTable.getSelectionModel().getSelectedItem();
       String selected = item.getNpm_mhs();
        // Membuat dialog box konfirmasi
        Alert alertBatal = new Alert(Alert.AlertType.CONFIRMATION);
        alertBatal.setTitle("Kampus App - Konfirmasi Hapus Data");
        alertBatal.setHeaderText("Hapus Data??");
        alertBatal.setContentText("Apakah anda yakin akan hapus data ini???");
        Optional<ButtonType> konfirmasiHapus = alertBatal.showAndWait();
        if(konfirmasiHapus.get() == ButtonType.OK){
            try {
                KampusDAO.deleteMhs(selected);
            } catch (SQLException e) {
                System.out.println("Ada kesalahan "+ e);
            }
        }
        DashboardController.getInstance().loadData();
    }

    @FXML
    private void btnExitAction(ActionEvent event) {
        // Membuat dialog box konfirmasi
        Alert alertBatal = new Alert(Alert.AlertType.CONFIRMATION);
        alertBatal.setTitle("Kampus App - Konfirmasi Keluar");
        alertBatal.setHeaderText("Batal Simpan Bio");
        alertBatal.setContentText("Apakah anda yakin akan Keluar?");
        Optional<ButtonType> konfirmasiBatal = alertBatal.showAndWait();
        if(konfirmasiBatal.get() == ButtonType.OK){
            System.exit(1);
        }
    }
    
   //Inisialisasi Retrieve data ke Tableview   
  void initCol(){
       nama_col.setCellValueFactory(new PropertyValueFactory<>("nama_mhs"));
       npm_col.setCellValueFactory(new PropertyValueFactory<>("npm_mhs"));
       pob_col.setCellValueFactory(new PropertyValueFactory<>("tempat_mhs"));
       dob_col.setCellValueFactory(new PropertyValueFactory<>("tanggal_mhs"));
       gender_col.setCellValueFactory(new PropertyValueFactory<>("jk_mhs"));
       alamat_col.setCellValueFactory(new PropertyValueFactory<>("alamat_mhs"));
       prodi_col.setCellValueFactory(new PropertyValueFactory<>("prodi_mhs"));
       jenjang_col.setCellValueFactory(new PropertyValueFactory<>("jenjang_mhs"));
   }
   
   //Untuk retrieve data dari database dan menampilkannya ke tableview
   void loadData(){
       ObservableList<KampusModel> oblist = FXCollections.observableArrayList();
       
       oblist.removeAll(oblist);
       
        try {
            Connection conn = DBConfig.getConnection();
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM bio_mhs");
            
            while (result.next()) {                
                oblist.add(new KampusModel(result.getString("nama_mhs"), result.getString("npm_mhs"), 
                        result.getString("tempat_lahir"), result.getDate("tgl_lahir").toLocalDate(), 
                        result.getString("jenis_kelamin"), result.getString("alamat_mhs"),
                        result.getString("prodi_mhs"), result.getString("jenjang_mhs")));
            }
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan "+ e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Ada Kesalahan" + ex);
        }
        
        dashboardTable.setItems(oblist);
   }
   
   
//   Membuat instance agar variable dan method pada DashboardController bisa di akses di Controller Lain
   private static DashboardController instance;
   public DashboardController(){
       instance = this;
   }
//   Method getInstance()
   public static DashboardController getInstance(){
       return instance;
   }

}
