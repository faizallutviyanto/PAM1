package customtools;

/**
 * Created by admin on 3/9/2015.
 */
public class DataMahasiswa {
    public String nim;
    public String nama;
    public String fakultas;

    public DataMahasiswa(){}

    public DataMahasiswa(String nim, String nama){
        this.nim = nim;
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }
}
