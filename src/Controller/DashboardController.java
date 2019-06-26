
package Controller;

import DAO.KampusDAO;
import Database.DBConfig;
import Model.KampusModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

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
    @FXML
    private Label userDash;
    @FXML
    private Button printBtn;
    @FXML
    private MenuBar menuBarUtama;
    @FXML
    private MenuItem menuItemTambah;
    @FXML
    private MenuItem menuItemExit;
    @FXML
    private MenuItem menuItemUbah;
    @FXML
    private MenuItem menuItemHapus;
    @FXML
    private MenuItem menuItemTentang;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
        dashboardTable.refresh();
        delBtn.setDisable(true);
        editBtn.setDisable(true);
        printBtn.setDisable(true);
        menuItemUbah.setDisable(true);
        menuItemHapus.setDisable(true);
    }    

    @FXML
    private void addBtnAction(ActionEvent event) throws IOException {
//      Load file AddMahasiswa.fxml untuk berpindah stage
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AddMahasiswa.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Kampus App - Add Mahasiswa");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void editBtnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/View/EditMahasiswa.fxml"));
      Parent root = (Parent) loader.load();
      EditMahasiswaController controller = loader.getController(); //Load controller agar bisa ambil method di ctrl lain
//    method initData (Untuk Ambil id yg terpilih) ketika tableview dipilih dan pass variable ke controller EditMahasiswa
      controller.initData(dashboardTable.getSelectionModel().getSelectedItem().getNpm_mhs());
      Scene newScene =  new Scene(root);
      Stage newStage = new Stage();
      newStage.initModality(Modality.APPLICATION_MODAL);
      newStage.setTitle("Kampus App - Edit Mahasiswa");
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
                setDisable();
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
        alertBatal.setHeaderText("Keluar Aplikasi");
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

   public void initUser(String user){
       this.userDash.setText(user);
   }

    @FXML
    private void setDeleteActive(MouseEvent event) {
        editBtn.setDisable(false);
        delBtn.setDisable(false);
        printBtn.setDisable(false);
        menuItemUbah.setDisable(false);
        menuItemHapus.setDisable(false);
    }
    
    public void setDisable(){
        editBtn.setDisable(true);
        delBtn.setDisable(true);
        menuItemUbah.setDisable(true);
        menuItemHapus.setDisable(true);
    }
        
    // Method to call Jasper File
    @FXML
    private void printBtnAction(ActionEvent event) throws SQLException, ClassNotFoundException, FileNotFoundException, JRException {
        Connection conn = DBConfig.getConnection(); //Make Connection to DB
        KampusModel item = dashboardTable.getSelectionModel().getSelectedItem(); // Get Selected item from JFXTableView
        String selected = item.getNpm_mhs();
        try {
            File file = new File("D:\\ITSUPPORT\\Project\\Java\\Aplikasi\\Kampus App\\src\\Report\\LaporanMahasiswaDetail.jrxml");
            InputStream stream = new FileInputStream(file);
            JasperDesign design = JRXmlLoader.load(stream);
            System.out.println(design);
            JasperReport report = JasperCompileManager.compileReport(design);
            Map<String, Object> params = new HashMap<>();
            params.put("NPM", selected); //Put Parameter NPM
            JasperPrint print = JasperFillManager.fillReport(report, params, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        } catch (FileNotFoundException | JRException e) {
            System.out.println("Error" + e);
        }
    }

    @FXML
    private void menuItemTambahAction(ActionEvent event) throws IOException {
//      Load file AddMahasiswa.fxml untuk berpindah stage
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AddMahasiswa.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Kampus App - Add Mahasiswa");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void menuItemExitAction(ActionEvent event) {
        // Membuat dialog box konfirmasi
        Alert alertBatal = new Alert(Alert.AlertType.CONFIRMATION);
        alertBatal.setTitle("Kampus App - Konfirmasi Keluar");
        alertBatal.setHeaderText("Keluar Aplikasi");
        alertBatal.setContentText("Apakah anda yakin akan Keluar?");
        Optional<ButtonType> konfirmasiBatal = alertBatal.showAndWait();
        if(konfirmasiBatal.get() == ButtonType.OK){
            System.exit(1);
        }
    }

    @FXML
    private void menuItemUbahAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/View/EditMahasiswa.fxml"));
      Parent root = (Parent) loader.load();
      EditMahasiswaController controller = loader.getController(); //Load controller agar bisa ambil method di ctrl lain
//    method initData (Untuk Ambil id yg terpilih) ketika tableview dipilih dan pass variable ke controller EditMahasiswa
      controller.initData(dashboardTable.getSelectionModel().getSelectedItem().getNpm_mhs());
      Scene newScene =  new Scene(root);
      Stage newStage = new Stage();
      newStage.initModality(Modality.APPLICATION_MODAL);
      newStage.setTitle("Kampus App - Edit Mahasiswa");
      newStage.setScene(newScene);
      newStage.showAndWait();
    }

    @FXML
    private void menuItemHapusAction(ActionEvent event) throws ClassNotFoundException {
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
                setDisable();
            } catch (SQLException e) {
                System.out.println("Ada kesalahan "+ e);
            }
        }
        DashboardController.getInstance().loadData();
    }

    @FXML
    private void menuItemTentangAction(ActionEvent event) {
        Alert alertTentang = new Alert(Alert.AlertType.INFORMATION);
        alertTentang.setTitle("Informasi Aplikasi");
        alertTentang.setHeaderText("Kampus App - CRUD Sederhana");
        alertTentang.setContentText("Ravi Mukti");
        alertTentang.showAndWait();
    }
}
