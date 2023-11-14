

import com.netbinde.brilloconnectest.configuration.JwtProvider;
import com.netbinde.brilloconnectest.model.User;
import com.netbinde.brilloconnectest.service.serviceImpl.UserServiceImpl;
import com.netbinde.brilloconnectest.validator.Validation;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Please enter user email: ");
        String email = sc.nextLine();
        System.out.println("Please Enter your password: ");
        String password = sc.nextLine();
        System.out.println("Please Enter your Date of birth (yyyy-MM-dd): ");
        String dobString = sc.nextLine();

        LocalDate dob = null;
        try {
            dob = LocalDate.parse(dobString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }

        if (dob != null) {
            JwtProvider jwtProvider = new JwtProvider();
            Validation validation = new Validation();
            UserServiceImpl userService = new UserServiceImpl(jwtProvider, validation);

            User newUser = new User(username, email, password, dob);
            userService.registerUser(newUser);
        }
    }
}
