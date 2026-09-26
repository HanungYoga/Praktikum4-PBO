package tugasprak4;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ManajemenAset {
    List<AsetIT> daftarAset = new ArrayList<>();

    public void tambahAset(AsetIT asetBaru) {
        daftarAset.add(asetBaru);
    }

    public void tampilkanSemuaAset() {
        for (AsetIT aset : daftarAset) {
            aset.tampilkanInfoAset();
        }
    }

    public void hapusAset(String idAset) {
        Iterator<AsetIT> it = daftarAset.iterator();
        boolean ditemukan = false;
        while (it.hasNext()) {
            AsetIT aset = it.next();
            if (aset.getIdAset().equals(idAset)) {
                it.remove();
                ditemukan = true;
                break;
            }
        }
   
        if (!ditemukan) {
            System.out.println("Peringatan: Aset dengan ID " + idAset + " tidak ditemukan.");
        }
    }
}
