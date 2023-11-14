package com.netbinde.brilloconnectest.validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(regex).matcher(email).matches();
    }
    public static boolean isValidPassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%^&*])(?=.*[0-9]).{8,}$";
        return Pattern.compile(regex).matcher(password).matches();
    }

    public static boolean isUnder16(LocalDate dob){
        LocalDate today = LocalDate.now();
        Period period = Period.between(dob, today);
        return period.getYears() < 16;
    }
}
