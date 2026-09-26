package tugasprak4;
public class AsetIT {
    String idAset;
    String namaPerangkat;
    String lokasi;
    String statusKondisi;

    public AsetIT(String idAset, String namaPerangkat, String lokasi, String statusKondisi) {
        this.idAset = idAset;
        this.namaPerangkat = namaPerangkat;
        this.lokasi = lokasi;
        this.statusKondisi = statusKondisi;
    }

    public String getIdAset() {
        return idAset;
    }

    public void tampilkanInfoAset() {
        System.out.println(idAset + " | " + namaPerangkat + " | " + lokasi + " | " + statusKondisi);
    }
}
