package com.ninja4testing.api.utils;

import java.util.Random;
import java.util.UUID;

public class Utils {

    // Genera un nombre de usuario único usando UUID
    public static String generarUsername() {
        return "ninja4testing_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    // Genera una contraseña que cumple con los requisitos mínimos:
    // Al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial
    public static String generarPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "@#$%&*!";
        String all = upper + lower + digits + special;
        Random rand = new Random();

        StringBuilder password = new StringBuilder();
        password.append(upper.charAt(rand.nextInt(upper.length())));
        password.append(lower.charAt(rand.nextInt(lower.length())));
        password.append(digits.charAt(rand.nextInt(digits.length())));
        password.append(special.charAt(rand.nextInt(special.length())));

        for (int i = 4; i < 12; i++) {
            password.append(all.charAt(rand.nextInt(all.length())));
        }
        return password.toString();
    }
}

