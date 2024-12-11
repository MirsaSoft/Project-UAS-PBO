package model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Transaksi implements Serializable{
    private String kategori;
    private double jumlah;
    private LocalDate tanggal;

    public Transaksi(String kategori, double jumlah, LocalDate tanggal) {
        this.kategori = kategori;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }

    public String getKategori() {
        return kategori;
    }

    public double getJumlah() {
        return jumlah;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public abstract String tampilkanTransaksi();
}
