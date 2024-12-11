package view;

import java.util.Scanner;

public class LoginView {
    private Scanner scanner;

    public LoginView() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("=== Wealth Wise ===");
        System.out.println("Selamat datang di aplikasi pengelolaan keuangan Anda!");
    }

    public int showMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Pilih opsi: ");
        return scanner.nextInt();
    }

    public String getUsername() {
        System.out.print("Masukkan Username: ");
        
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.print("Masukkan Password: ");
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
