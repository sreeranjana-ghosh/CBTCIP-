import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    String questionText;
    List<String> options;
    int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class OnlineExamination {
    static List<Question> questions = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static User loggedInUser;
    static boolean sessionOpen = false;

    public static void main(String[] args) {
        initializeQuestions();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void initializeQuestions() {
        List<String> options1 = new ArrayList<>();
        options1.add("5");
        options1.add("6");
        options1.add("4");
        options1.add("1");
        List<String> options2 = new ArrayList<>();
        options2.add("Mars");
        options2.add("Neptune");
        options2.add("Earth");
        options2.add("Sun");

        Question question1 = new Question("What is 2 + 2?", options1, 2);
        Question question2 = new Question("Which planet is known as the Red Planet?", options2, 0);

        questions.add(question1);
        questions.add(question2);
    }

    public static void register(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // Check if the username is already taken
        for (User user : users) {
            if (user.username.equals(username)) {
                System.out.println("Username already taken. Please choose another one.");
                return;
            }
        }

        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("Registration successful! You can now log in.");
    }

    public static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate credentials
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                loggedInUser = user;
                sessionOpen = true;
                startExam(scanner);
                return;
            }
        }

        System.out.println("Invalid credentials. Please try again.");
    }

    public static void startExam(Scanner scanner) {
        System.out.println("Welcome, " + loggedInUser.username + "!");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.questionText);
            for (int j = 0; j < question.options.size(); j++) {
                System.out.println((j + 1) + ". " + question.options.get(j));
            }

            System.out.print("Select your answer (1-" + question.options.size() + "): ");
            int userChoice = scanner.nextInt();
            if (userChoice == question.correctOption + 1) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect.");
            }
        }

        System.out.println("Exam completed!");
        sessionOpen = false;
    }
}
