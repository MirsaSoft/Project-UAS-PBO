package model;

import java.util.HashMap;

public class LoginModel {
    private HashMap<String, String> users;

    public LoginModel() {
        users = new HashMap<>();
        // Daftar pengguna default
        users.put("admin", "1234");
        users.put("user", "abcd");
    }

    public boolean validateLogin(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username sudah ada
        }
        users.put(username, password); // Tambahkan pengguna baru
        return true;
    }
}
