import controller.KeuanganController;
import model.LoginModel;
import view.KeuanganView;
import view.LoginView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LoginView loginView = new LoginView();
        LoginModel loginModel = new LoginModel();

        boolean isAuthenticated = false;
        while (!isAuthenticated) {
            loginView.displayMessage("===== Login Wealth Wise =====");
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            if (loginModel.validateLogin(username, password)) {
                loginView.displayMessage("Login berhasil! Selamat datang, " + username + "!");
                isAuthenticated = true;
            } else {
                loginView.displayMessage("Login gagal. Username atau password salah. Coba lagi.");
            }
        }

        KeuanganView view = new KeuanganView();
        KeuanganController controller = new KeuanganController(view);

        controller.muatDataDariTxt();

        boolean running = true;
        while (running) {
            System.out.println("\n===== Wealth Wise =====");
            System.out.println("1. Tambah Transaksi");
            System.out.println("2. Hapus Transaksi");
            System.out.println("3. Lihat Laporan");
            System.out.println("4. Simpan Data");
            System.out.println("5. Reset Data");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi (1-6): ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1 -> {
                    view.tampilkanKategori(controller.getKategori());
                    System.out.print("Pilih Kategori (1-5): ");
                    int kategoriPilihan = scanner.nextInt();
                    if (kategoriPilihan == 0) {
                        view.tampilkanPesan("Transaksi dibatalkan.");
                        break;
                    }
                    String kategori = controller.getKategori()[kategoriPilihan - 1];

                    System.out.print("Masukkan Jumlah: ");
                    double jumlah = scanner.nextDouble();

                    LocalDate tanggal = null;
                    while (tanggal == null) {
                        System.out.print("Masukkan Tanggal (yyyy-MM-dd): ");
                        String tanggalInput = scanner.next();
                        try {
                            String[] parts = tanggalInput.split("-");
                            String tahun = parts[0];
                            String bulan = (parts[1].length() == 1) ? "0" + parts[1] : parts[1];
                            String hari = (parts[2].length() == 1) ? "0" + parts[2] : parts[2];
                            tanggal = LocalDate.parse(tahun + "-" + bulan + "-" + hari);
                        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                            view.tampilkanPesan("Format tanggal salah. Harap masukkan dalam format yyyy-MM-dd.");
                        }
                    }
                    controller.tambahTransaksi(kategori, jumlah, tanggal);
                }
                case 2 -> {
                    controller.tampilkanLaporan();
                    System.out.print("Pilih nomor transaksi yang akan dihapus (0 untuk batal): ");
                    int indeks = scanner.nextInt();
                    if (indeks != 0) {
                        controller.hapusTransaksi(indeks - 1);
                    } else {
                        view.tampilkanPesan("Penghapusan dibatalkan.");
                    }
                }
                case 3 -> controller.tampilkanLaporan();
                case 4 -> controller.simpanDataKeTxt();
                case 5 -> {
                    controller.resetData();
                    view.tampilkanPesan("semua data anda telah dihapus");
                }
                case 6 -> {
                    controller.simpanDataKeTxt();
                    running = false;
                    view.tampilkanPesan("Terima kasih telah menggunakan Wealth Wise!");
                }
                default -> view.tampilkanPesan("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }
}
