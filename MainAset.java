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
