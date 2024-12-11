package view;

import model.Transaksi;
import java.util.List;

public class KeuanganView {
    public void tampilkanLaporan(List<Transaksi> transaksiList, double totalPendapatan, double totalPengeluaran) {
        System.out.println("\n=== Laporan Keuangan ===");
        for (int i = 0; i < transaksiList.size(); i++) {
            System.out.println((i + 1) + ". " + transaksiList.get(i).tampilkanTransaksi());
        }
        System.out.println("------------------------");
        System.out.println("Total Pendapatan: Rp" + totalPendapatan);
        System.out.println("Total Pengeluaran: Rp" + totalPengeluaran);
        System.out.println("Saldo: Rp" + (totalPendapatan - totalPengeluaran));
    }

    public void tampilkanKategori(String[] kategori) {
        System.out.println("\n=== Pilihan Kategori ===");
        for (int i = 0; i < kategori.length; i++) {
            System.out.println((i + 1) + ". " + kategori[i]);
        }
        System.out.println("0. Batalkan");
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}
