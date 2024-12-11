package model;

import java.time.LocalDate;

public class Pendapatan extends Transaksi {
    public Pendapatan(String kategori, double jumlah, LocalDate tanggal) {
        super(kategori, jumlah, tanggal);
    }

    @Override
    public String tampilkanTransaksi() {
        return "Pendapatan - Tanggal: " + getTanggal() + ", Kategori: " + getKategori() + ", Jumlah: Rp" + getJumlah();
    }
}
