package model;

import java.time.LocalDate;

public class Pengeluaran extends Transaksi {
    public Pengeluaran(String kategori, double jumlah, LocalDate tanggal) {
        super(kategori, jumlah, tanggal);
    }

    @Override
    public String tampilkanTransaksi() {
        return "Pengeluaran - Tanggal: " + getTanggal() + ", Kategori: " + getKategori() + ", Jumlah: Rp" + getJumlah();
    }
}
