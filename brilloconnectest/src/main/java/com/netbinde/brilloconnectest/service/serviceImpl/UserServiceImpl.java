package com.netbinde.brilloconnectest.service.serviceImpl;

import com.netbinde.brilloconnectest.configuration.JwtProvider;
import com.netbinde.brilloconnectest.model.User;
import com.netbinde.brilloconnectest.service.UserService;
import com.netbinde.brilloconnectest.validator.Validation;


import java.time.LocalDate;

import static com.netbinde.brilloconnectest.appcontants.AppContants.SECRET_KEY;


public class UserServiceImpl implements UserService {

    private Validation validation;
    private JwtProvider jwtProvider;

    public UserServiceImpl(JwtProvider jwtProvider, Validation validation) {
        this.jwtProvider = jwtProvider;
        this.validation = validation;
    }

    @Override
    public void registerUser(User user) {
        String validationMessage = performValidations(user.getUsername(), user.getEmail(), user.getPassword(), user.getDob());
        if (validationMessage.equals(ValidationStatus.VALIDATION_PASSED.toString())) {
            String jwt = generateJWT(user.getUsername());
            System.out.println("Generated JWT: " + jwt);

            String verificationResult = verifyJWT(jwt);
            System.out.println("JWT Verification: " + verificationResult);
        } else {
            System.out.println(validationMessage);
        }
    }

    private String performValidations(String username, String email, String password, LocalDate dob) {
        StringBuilder validationErrors = new StringBuilder();

        if (username.length() < 4) {
            validationErrors.append("Username: not empty, min 4 characters\n");
        }

        if (email.isEmpty() || !validation.isValidEmail(email)) {
            validationErrors.append("Email: email not valid \n");
        }

        if (!validation.isValidPassword(password)) {
            validationErrors.append("Password: strong password required\n");
        }

        if (dob == null || validation.isUnder16(dob)) {
            validationErrors.append("Date of Birth: should be 16 years or greater\n");
        }

        return validationErrors.length() > 0 ? validationErrors.toString() : ValidationStatus.VALIDATION_PASSED.toString();
    }

    private String generateJWT(String subject) {
        return jwtProvider.generateJWT(subject, SECRET_KEY);
    }

    private String verifyJWT(String jwt) {
        return jwtProvider.verifyJWT(jwt, SECRET_KEY);
    }

    enum ValidationStatus {
        VALIDATION_PASSED,
        VALIDATION_FAILED
    }
}