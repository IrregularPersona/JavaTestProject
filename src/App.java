import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrePages.welcomePage(scanner);
        GameRender.generateMap();
        scanner.close();
    }

    public static class PrePages {
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
                    String emailInput;
                    String passwordInput;

                    switch (choice) {
                        case 1:
                            System.out.println("Enter your email:");
                            System.out.print(">> ");;

                            emailInput = scanner.nextLine();
                            if(!credentialFunctions.verifyEmail(emailInput)) {
                                System.out.println("Please enter a proper email!");
                                continue;
                            }
                            
                            System.out.println("Enter your password:");
                            System.out.print(">> ");

                            passwordInput = scanner.nextLine();

                            if (!credentialFunctions.verifyCredentials(emailInput, passwordInput)) {
                                System.out.println("Credentials Not found!");
                                System.out.println("Please Try again!");
                                break;
                            }
                            break;
                        case 2:
                            System.out.println("Enter an email:");
                            System.out.print(">> ");
                            emailInput = scanner.nextLine();
                            if (!credentialFunctions.verifyEmail(emailInput)) {
                                System.out.println("Please enter a proper email!");
                                continue;
                            }

                            System.out.println("Enter a password:");
                            System.out.print(">> ");
                            passwordInput = scanner.nextLine();
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

    public static class GamePage {
        public static void gameMenu(Scanner scanner) {
            System.out.println("Welcome to the game!");
            System.out.println("[1] Start Game");
            System.out.println("[2] Game Guide");
            System.out.println("[3] Exit");
            System.out.print(">> ");

            String menuInput = scanner.nextLine();
            
        }
    }

    public class GameRender {

        private static final int MAP_HEIGHT = 300;
        private static final int MAP_WIDTH = 300;
        private static final int VIEW_FRAME_WIDTH = 35;
        private static final int VIEW_FRAME_HEIGHT = 15;
        private static final int START_X = 150;
        private static final int START_Y = 150;
    
        private static final String MAP_FILE_PATH = "./src/resource/map.txt";
    
        public static void generateMap() {
            try (PrintWriter writer = new PrintWriter(new FileWriter(MAP_FILE_PATH))) {
                for (int row = 0; row < MAP_HEIGHT; row++) {
                    for (int col = 0; col < MAP_WIDTH; col++) {
                        if (row == 0 || row == MAP_HEIGHT - 1 || col == 0 || col == MAP_WIDTH - 1) {
                            writer.print("#");
                        } else {
                            writer.print(".");
                        }
                    }
                    writer.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        public static String readFile(String filePath) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine())!= null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }
    
        public static void loadViewFrame() {
            String mapContent = readFile(MAP_FILE_PATH);
            String[] lines = mapContent.split("\n");
            for (int i = START_Y; i < START_Y + VIEW_FRAME_HEIGHT; i++) {
                String line = lines[i];
                if (line!= null) {
                    System.out.println(line.substring(START_X, START_X + VIEW_FRAME_WIDTH));
                }
            }
        }

        public static void processUserInput(char userInput) {
            switch (userInput) {
                case 'w':
                    
                    break;
                case 'a':
                    
                    break;
                case 's':
                    
                    break;
                case 'd':
                    
                    break;
                case 'i':

                    break;
                case 'z':

                    break;
                case 'e':

                    break;
                default:
                    break;
            }
        }
    
        public static void currentMapRender() {
            // TO DO
        }
    
        public static void statRender() {
            // TO DO
        }

        public static void saveUserProgress() {
            // TO DO
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
                        if (email.equals(emailCheck) && password.equals(passwordCheck)) {
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
        
            return false;
        }
        

        public static boolean verifyEmail(String email) {
            String pattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
            Pattern regexPattern = Pattern.compile(pattern);
            Matcher matcher = regexPattern.matcher(email);
            return matcher.find();
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
}
