# Tugas Praktikum 4 - Array, List, Iterator
<p align="center">
 <img width="286" height="286" alt="image" src="https://github.com/user-attachments/assets/5e2ec322-9379-4ad8-91be-de301db61dff"/><br>
  <b>Nama: Hanung Yoga Adi Pramono<br>
  NIM: L0325026</b>
</p>

## Penjelasan source code

### AsetIT.java
```java
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
```

Class ini berfungsi sebagai model data untuk merepresentasikan satu unit aset IT. Terdapat empat atribut, yaitu idAset, namaPerangkat, lokasi, dan statusKondisi. Atribut ini tidak diberi kata kunci private, mengikuti gaya penulisan class Produk pada modul, sehingga masih bisa diakses langsung oleh class lain selama berada dalam package yang sama. Constructor pada class ini adalah parameterized constructor yang mengisi keempat atribut tersebut saat objek AsetIT dibuat lewat new AsetIT(...). Kata kunci this dipakai karena nama parameter sengaja dibuat sama dengan nama atribut, sehingga this.idAset merujuk ke atribut milik objek, sedangkan idAset di sisi kanan merujuk ke parameter yang diterima constructor. Selain itu ada method getIdAset() yang mengembalikan nilai idAset milik objek; method ini dibutuhkan karena ManajemenAset perlu cara untuk membaca ID setiap objek saat proses pencarian dan penghapusan berlangsung. Terakhir, method tampilkanInfoAset() bertugas mencetak keempat data aset dalam satu baris dengan format id, nama perangkat, lokasi, dan status yang dipisahkan tanda garis vertikal, sama persis dengan format tampilan pada class Produk.

### ManajemenAset.java

```java
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
```

Class ini bertugas mengelola sekumpulan objek AsetIT, bukan menyimpan data aset secara langsung. Data ditampung dalam daftarAset, dideklarasikan dengan tipe interface List<AsetIT> namun objeknya dibuat dari ArrayList, mengikuti pola JCF yang dijelaskan pada Dasar Teori modul. Method tambahAset(AsetIT asetBaru) hanya memanggil daftarAset.add(asetBaru), sehingga setiap objek baru langsung masuk ke posisi terakhir list. Method tampilkanSemuaAset() menelusuri seluruh isi list dengan perulangan for-each dan memanggil tampilkanInfoAset() pada tiap objek, sehingga urutan tampilan akan sama persis dengan urutan objek dimasukkan. Bagian paling penting ada pada method hapusAset(String idAset). Di sini digunakan Iterator, bukan for-each, karena penghapusan elemen di tengah proses iterasi hanya aman dilakukan lewat it.remove(); jika dipaksakan lewat for-each akan memicu ConcurrentModificationException seperti yang diperingatkan pada Dasar Teori poin 4. Selama it.hasNext() masih bernilai true, it.next() mengambil objek saat ini, lalu id-nya dibandingkan dengan idAset yang dicari menggunakan .equals() karena yang dibandingkan adalah isi String, bukan alamat objeknya. Jika cocok, objek dihapus lewat it.remove() dan variabel boolean ditemukan diubah menjadi true lalu loop dihentikan. Jika sampai akhir loop ditemukan tetap false, artinya tidak ada objek dengan ID tersebut, sehingga dicetak pesan peringatan sesuai instruksi tugas poin d.

### MainAset.java
```java
package tugasprak4;
public class MainAset {
    public static void main(String[] args) {
        ManajemenAset manajemen = new ManajemenAset();

        manajemen.tambahAset(new AsetIT("A01", "Server", "Ruang Server", "Baik"));
        manajemen.tambahAset(new AsetIT("A02", "Router", "Ruang Jaringan", "Baik"));
        manajemen.tambahAset(new AsetIT("A03", "Switch", "Ruang Jaringan", "Rusak"));
        manajemen.tambahAset(new AsetIT("A04", "PC", "Lab Komputer", "Baik"));

        System.out.println("--- Data Aset (Sebelum Dihapus) ---");
        manajemen.tampilkanSemuaAset();
        manajemen.hapusAset("A03");
        
        System.out.println("\n--- Data Aset (Setelah Dihapus) ---");
        manajemen.tampilkanSemuaAset();
    }
}
```
Class ini berisi method main yang menjalankan skenario program secara berurutan, tanpa mendefinisikan struktur data baru. Pertama dibuat satu objek ManajemenAset yang akan menampung seluruh data selama program berjalan. Kemudian dipanggil tambahAset() sebanyak empat kali, masing-masing membawa objek AsetIT baru (Server, Router, Switch, PC) yang langsung dibuat lewat new AsetIT(...) di dalam pemanggilannya. Setelah semua data masuk, tampilkanSemuaAset() dipanggil untuk pertama kali guna menampilkan kondisi list sebelum ada perubahan. Selanjutnya hapusAset("A03") dipanggil untuk menghapus aset Switch, dan terakhir tampilkanSemuaAset() dipanggil sekali lagi untuk membuktikan bahwa isi daftarAset benar-benar berubah setelah proses penghapusan, bukan sekadar berjalan tanpa error.

## Penjelasan Output
```
--- Data Aset (Sebelum Dihapus) ---
A01 | Server | Ruang Server | Baik
A02 | Router | Ruang Jaringan | Baik
A03 | Switch | Ruang Jaringan | Rusak
A04 | PC | Lab Komputer | Baik

--- Data Aset (Setelah Dihapus) ---
A01 | Server | Ruang Server | Baik
A02 | Router | Ruang Jaringan | Baik
A04 | PC | Lab Komputer | Baik
```

Pada blok "Data Aset (Sebelum Dihapus)", keempat aset yang dimasukkan tampil sesuai urutan penambahannya, yaitu Server, Router, Switch, lalu PC. Ini membuktikan bahwa List (melalui ArrayList) mempertahankan urutan data sesuai urutan add(), dan perulangan for-each pada tampilkanSemuaAset() bekerja dengan benar tanpa mengubah urutan tersebut. Setelah blok ini, program memanggil hapusAset("A03"). Karena ID "A03" memang ada dalam data, Iterator berhasil menemukannya saat menelusuri list dan langsung memanggil it.remove(), sehingga variabel ditemukan bernilai true dan pesan peringatan tidak ikut tercetak. Hal ini terlihat dari tidak munculnya baris peringatan apa pun di antara dua blok data pada output. Pada blok terakhir, "Data Aset (Setelah Dihapus)", hanya tersisa tiga baris, yaitu A01, A02, dan A04; baris A03 sudah tidak muncul lagi, sementara data dan urutan tiga aset lainnya tidak berubah sama sekali. Ini membuktikan bahwa Iterator hanya menghapus satu objek yang cocok dengan ID yang dicari, tanpa memengaruhi objek lain di dalam list. Baris BUILD SUCCESS di paling bawah menandakan bahwa proses kompilasi dan eksekusi program berjalan tanpa error atau exception sama sekali, sehingga seluruh alur CRUD (penambahan, penampilan, dan penghapusan) yang diminta pada tugas terbukti berjalan sesuai harapan.
