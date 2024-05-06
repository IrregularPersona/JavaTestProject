import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
// import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        welcomePage(scanner);
        scanner.close();
    }

    public static void welcomePage(Scanner scanner) {
        System.out.println("Welcome to this page!");
        System.out.println("Press [Enter] to continue!");

        checkForEnter(scanner);
        loginPage(scanner);
    }

    public static void loginPage(Scanner scanner) {
        while (true) {
            System.out.println("Hello!");
            System.out.println("[1]. Login");
            System.out.println("[2]. Sign Up");
            System.out.println("[3]. Exit App");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("You chose Login.");
                        printAllCredentials();
                        break;
                    case 2:
                        System.out.println("You chose Sign Up.");

                        break;
                    case 3:
                        System.out.println("Exiting App. Goodbye!");
                        return; // Exit the app
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer option.");
                scanner.next(); // Consume invalid input
            }
        }
    }

    public static void checkForEnter(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
        }
    }

    public static void printAllCredentials() {
        String file_path = "." + File.separator + "src" + File.separator + "resource" + File.separator + "credentials.txt";
        File file = new File(file_path);
    
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class EmailValidator {
        public static boolean verifyEmail(String email) {
            String pattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
            Pattern regexPattern = Pattern.compile(pattern);
            Matcher matcher = regexPattern.matcher(email);
            boolean matchFound = matcher.find();
    
            return matchFound;
        }
    }
}