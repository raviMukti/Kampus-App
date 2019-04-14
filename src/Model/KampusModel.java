
package Model;

import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.property.*;

/**
 *
 * @author raviMukti at Aftwork
 */
public class KampusModel {
    
    String nama_mhs, npm_mhs, tempat_mhs, tanggal_mhs, jk_mhs, alamat_mhs, prodi_mhs, jenjang_mhs;
    
  

    public KampusModel(String nama_mhs, String npm_mhs, String tempat_mhs, String tanggal_mhs, String jk_mhs, String alamat_mhs, String prodi_mhs, String jenjang_mhs) {
        this.nama_mhs = nama_mhs;
        this.npm_mhs = npm_mhs;
        this.tempat_mhs = tempat_mhs;
        this.tanggal_mhs = tanggal_mhs;
        this.jk_mhs = jk_mhs;
        this.alamat_mhs = alamat_mhs;
        this.prodi_mhs = prodi_mhs;
        this.jenjang_mhs = jenjang_mhs;
    }

    public String getNama_mhs() {
        return nama_mhs;
    }

    public void setNama_mhs(String nama_mhs) {
        this.nama_mhs = nama_mhs;
    }

    public String getNpm_mhs() {
        return npm_mhs;
    }

    public void setNpm_mhs(String npm_mhs) {
        this.npm_mhs = npm_mhs;
    }

    public String getTempat_mhs() {
        return tempat_mhs;
    }

    public void setTempat_mhs(String tempat_mhs) {
        this.tempat_mhs = tempat_mhs;
    }

    public String getTanggal_mhs() {
        return tanggal_mhs;
    }

    public void setTanggal_mhs(String tanggal_mhs) {
        this.tanggal_mhs = tanggal_mhs;
    }

    public String getJk_mhs() {
        return jk_mhs;
    }

    public void setJk_mhs(String jk_mhs) {
        this.jk_mhs = jk_mhs;
    }

    public String getAlamat_mhs() {
        return alamat_mhs;
    }

    public void setAlamat_mhs(String alamat_mhs) {
        this.alamat_mhs = alamat_mhs;
    }

    public String getProdi_mhs() {
        return prodi_mhs;
    }

    public void setProdi_mhs(String prodi_mhs) {
        this.prodi_mhs = prodi_mhs;
    }

    public String getJenjang_mhs() {
        return jenjang_mhs;
    }

    public void setJenjang_mhs(String jenjang_mhs) {
        this.jenjang_mhs = jenjang_mhs;
    }
    
    
}
