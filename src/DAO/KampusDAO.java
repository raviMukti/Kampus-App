
package DAO;

import Database.DBConfig;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author raviMukti at Aftwork
 */
public class KampusDAO {
    
    //Method insertMhs()
    public static void insertMhs(String nama, String npm, String tempat, LocalDate tanggal, String jk, 
           String alamat, String prodi, String jenjang) throws  SQLException, ClassNotFoundException {
        //Deklarasi Insert  Statement
        String insertStmt = 
                        "INSERT INTO `bio_mhs` (`nama_mhs`, `npm_mhs`, `tempat_lahir`,"
                + " `tgl_lahir`, `jenis_kelamin`, `alamat_mhs`, `prodi_mhs`, `jenjang_mhs`) VALUES ("
                + " '"+nama+"', '"+npm+"', '"+tempat+"', '"+tanggal+"',"
                + "'"+jk+"', '"+alamat+"','"+prodi+"','"+jenjang+"')";
        
        try {
            DBConfig.dbExecuteUpdate(insertStmt);
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan saat input data ke Database" + e);
        }
    }
    
    //Method deleteMhs()
    public static void deleteMhs(String npm) throws SQLException, ClassNotFoundException{
        String deleteStmt = "DELETE FROM `bio_mhs` WHERE npm_mhs = '"+npm+"'";
        try {
            DBConfig.dbExecuteUpdate(deleteStmt);
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan " + e);
        }
        DBConfig.dbDisconnect();
    }
    
    //Method selectMhs()
    public static void selectMhs(String username, String password) throws SQLException, ClassNotFoundException{
        String dispMhsStmt = "SELECT `username`, `password` FROM `bio_user` WHERE "
                + "`bio_user`.`username` AND `bio_user`.`password` = '"+username+"' , '"+password+"'";
        try {
            DBConfig.dbExecuteQuery(dispMhsStmt);
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan " + e);
        }
        DBConfig.dbDisconnect();
    }
    
    //Method updateMhs()
//    UPDATE `bio_mhs` SET `nama_mhs` = 'Junaedi' WHERE `bio_mhs`.`id_mhs` = 4
    public static void updateMhs(String nama, String npm, String tempat, LocalDate tanggal, String jk, 
           String alamat, String sql) throws  SQLException, ClassNotFoundException {
        //Deklarasi Insert  Statement
        String updateStmt = 
                  "UPDATE `bio_mhs` SET `nama_mhs`='"+nama+"', `npm_mhs`='"+npm+"',"
                + "`tempat_lahir`='"+tempat+"',"
                + " `tgl_lahir`='"+tanggal+"', `jenis_kelamin`='"+jk+"', `alamat_mhs`='"+alamat+"' WHERE "
                + "`bio_mhs`.`npm_mhs` = '"+sql+"'";
        try {
            DBConfig.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan saat input data ke Database" + e);
        }
    }
}
