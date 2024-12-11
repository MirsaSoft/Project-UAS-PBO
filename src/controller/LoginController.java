package controller;

import model.LoginModel;
import view.LoginView;

public class LoginController {
    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        boolean running = true;


        while (running) {
            int choice = view.showMainMenu();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    view.displayMessage("Terima kasih telah menggunakan Wealth Wise. Sampai jumpa!");
                    running = false;
                    break;
                default:
                    view.displayMessage("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private void login() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (model.validateLogin(username, password)) {
            view.displayMessage("Login berhasil! Selamat datang, " + username + "!");
            // Masuk ke menu utama Wealth Wise
            wealthWiseMainMenu();
        } else {
            view.displayMessage("Login gagal. Username atau password salah.");
        }
    }

    private void register() {
        String username = view.getUsername();
        String password = view.getPassword();

        if (model.registerUser(username, password)) {
            view.displayMessage("Registrasi berhasil! Anda sekarang dapat login.");
        } else {
            view.displayMessage("Registrasi gagal. Username sudah ada.");
        }
    }

    private void wealthWiseMainMenu() {
        // Menu utama Wealth Wise
        view.displayMessage("\n=== Wealth Wise Main Menu ===");
        view.displayMessage("1. Kelola Keuangan");
        view.displayMessage("2. Lihat Laporan");
        view.displayMessage("3. Logout");
        // Implementasi fitur sesuai kebutuhan
    }
}