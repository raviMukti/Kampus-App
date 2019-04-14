
package DAO;

import Database.DBConfig;
import Model.KampusModel;
import java.sql.ResultSet;
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
        String updateStmt = 
                        "INSERT INTO `bio_mhs` (`nama_mhs`, `npm_mhs`, `tempat_lahir`,"
                + " `tgl_lahir`, `jenis_kelamin`, `alamat_mhs`, `prodi_mhs`, `jenjang_mhs`) VALUES ("
                + " '"+nama+"', '"+npm+"', '"+tempat+"', '"+tanggal+"',"
                + "'"+jk+"', '"+alamat+"','"+prodi+"','"+jenjang+"')";
        
        try {
            DBConfig.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.println("Ada Kesalahan saat input data ke Database" + e);
        }
    }
    
}
