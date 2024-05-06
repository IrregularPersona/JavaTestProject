import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pages.welcomePage(scanner);
        scanner.close();
    }

    public static class Pages {
        public static void welcomePage(Scanner scanner) {
            System.out.println("Welcome to this page!");
            System.out.println("Press [Enter] to continue!");

            arbitraryChecks.checkForEnter(scanner);
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
                            System.out.println("Enter your email:");
                            System.out.print(">> ");;

                            String emailInput = scanner.nextLine();
                            if(!verifyEmail(emailInput)) {
                                System.out.println("Please enter a proper email!");
                                continue;
                            }
                            
                            System.out.println("Enter your password:");
                            System.out.print(">> ");

                            String passwordInput = scanner.nextLine();

                            verifyCredentials(emailInput, passwordInput);

                            break;
                        case 2:
                            System.out.println("Enter an email:");
                            System.out.print(">> ");
                            String emailInput = scanner.nextLine();
                            if (!credentialFunctions.verifyEmail(emailInput)) {
                                System.out.println("Please enter a proper email!");
                                continue;
                            }

                            System.out.println("Enter a password:");
                            System.out.print(">> ");
                            String passwordInput = scanner.nextLine();
                            int passLength = passwordInput.length();

                            if(passLength < 8) {
                                System.out.println("Passwords have to be more than 8 characters!");
                                continue;
                            }

                            credentialFunctions.pushCredentials(emailInput, passwordInput);
                            break;
                        case 3:
                            System.out.println("Exiting App. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer option.");
                    scanner.next(); 
                }
            }
        }
    }

    public static class arbitraryChecks {
        public static void checkForEnter(Scanner scanner) {
            while (true) {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    break;
                }
            }
        }
    }

    public static class credentialFunctions {
        public static void pushCredentials(String email, String password) {
            String file_path = "." + File.separator + "src" + File.separator + "resource" + File.separator + "credentials.txt";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_path, true))) {
                bw.write(email + ";" + password + "\n");
                System.out.println("Credentials saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void printAllCredentials() {
            String file_path = "." + File.separator + "src" + File.separator + "resource" + File.separator + "credentials.txt";
            File file = new File(file_path);
        
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 2) {
                        String email = parts[0].trim();
                        String password = parts[1].trim();
                        System.out.println("Email: " + email + ", Password: " + password);
                    } else {
                        System.out.println("Invalid format: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static boolean verifyCredentials(String email, String password) {
            String file_path = "." + File.separator + "src" + File.separator + "resource" + "credentials.txt";

            File file = new File(file_path);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 2) {
                        String emailCheck = parts[0].trim();
                        String passwordCheck = parts[1].trim();
                        if(email.equals(emailCheck) && password.equals(passwordCheck)) {
                            return true;
                        }
                    } else {
                        System.out.println("Invalid credentials!");
                        return false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static boolean verifyEmail(String email) {
            String pattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
            Pattern regexPattern = Pattern.compile(pattern);
            Matcher matcher = regexPattern.matcher(email);
            return matcher.find();
        }
    }
}
