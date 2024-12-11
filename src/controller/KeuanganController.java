package controller;

import model.*;
import view.KeuanganView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KeuanganController {
    private List<Transaksi> transaksiList = new ArrayList<>();
    private KeuanganView view;
    private static final String FILE_PATH = "data/transaksi.txt";
    private static final String[] presetKategori = {"Gaji", "Makanan", "Hiburan", "Transportasi", "Lain-lain"};

    public KeuanganController(KeuanganView view) {
        this.view = view;
    }

    public void tambahTransaksi(String kategori, double jumlah, LocalDate tanggal) {
        Transaksi transaksi;
        if (kategori.equals("Gaji")) {
            transaksi = new Pendapatan(kategori, jumlah, tanggal);
        } else {
            transaksi = new Pengeluaran(kategori, jumlah, tanggal);
        }
        transaksiList.add(transaksi);
        view.tampilkanPesan("Transaksi berhasil ditambahkan!");
    }

    public void hapusTransaksi(int index) {
        if (index >= 0 && index < transaksiList.size()) {
            transaksiList.remove(index);
            view.tampilkanPesan("Transaksi berhasil dihapus.");
        } else {
            view.tampilkanPesan("Indeks tidak valid.");
        }
    }

    public void tampilkanLaporan() {
        double totalPendapatan = 0, totalPengeluaran = 0;
        for (Transaksi transaksi : transaksiList) {
            if (transaksi instanceof Pendapatan) {
                totalPendapatan += transaksi.getJumlah();
            } else if (transaksi instanceof Pengeluaran) {
                totalPengeluaran += transaksi.getJumlah();
            }
        }
        view.tampilkanLaporan(transaksiList, totalPendapatan, totalPengeluaran);
    }

    public void simpanDataKeTxt() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir(); 
        }
    
        File file = new File("data/transaksi.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("====================== Laporan Transaksi ======================");
            writer.printf("%-5s %-15s %-15s %-15s %-15s\n", "No.", "Kategori", "Jumlah", "Tanggal", "Jenis");
            writer.println("-------------------------------------------------------------");
    
            int index = 1;
            for (Transaksi transaksi : transaksiList) {
                String jenisTransaksi = (transaksi instanceof Pendapatan) ? "IN" : "OUT";
                writer.printf("%-5d %-15s %-15.2f %-15s %-15s\n",
                        index++, transaksi.getKategori(), transaksi.getJumlah(), transaksi.getTanggal(), jenisTransaksi);
            }
    
            writer.println("=============================================================");
            view.tampilkanPesan("Data berhasil disimpan ke file: " + file.getAbsolutePath());
        } catch (IOException e) {
            view.tampilkanPesan("Gagal menyimpan data: " + e.getMessage());
        }
    }
    
    

    public void muatDataDariTxt() {
        File file = new File("data/transaksi.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                transaksiList.clear();
                for (int i = 0; i < 4; i++) {
                    reader.readLine();
                }
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty() || line.startsWith("===")) {
                        continue; 
                    }
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length >= 5) {
                        String kategori = parts[1];
                        double jumlah = Double.parseDouble(parts[2]);
                        LocalDate tanggal = LocalDate.parse(parts[3]);
                        String jenis = parts[4];
                        if (jenis.equals("IN")) {
                            transaksiList.add(new Pendapatan(kategori, jumlah, tanggal));
                        } else if (jenis.equals("OUT")) {
                            transaksiList.add(new Pengeluaran(kategori, jumlah, tanggal));
                        }
                    }
                }
                view.tampilkanPesan("Data berhasil dimuat dari file: " + file.getAbsolutePath());
            } catch (IOException e) {
                view.tampilkanPesan("Gagal memuat data: " + e.getMessage());
            }
        } else {
            view.tampilkanPesan("Belum ada data yang tersimpan.");
        }
    }
    

    public void resetData(){
        transaksiList.clear();
        File file = new File(FILE_PATH);
        if (file.exists()){
            file.delete();
        }
    }

    public String[] getKategori() {
        return presetKategori;
    }
}
