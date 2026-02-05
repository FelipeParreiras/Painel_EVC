package com.evcvistoria.gestao.utils;

public class Validators {
    public static boolean isPasswordValid(String password) {
        return password != null
                && password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*\\d.*")
                && password.matches(".*[!@#$%^&*()].*");
    }

    public static boolean isEmailValid(String email) {
        return email != null
                && email.length() <= 254
                && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

}
