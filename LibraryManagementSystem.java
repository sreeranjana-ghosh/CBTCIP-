import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int id;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    // Getters and setters for Book properties
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author + " (" + (available ? "Available" : "Not Available") + ")";
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private int nextBookId = 1;

    public void addBook(String title, String author) {
        books.add(new Book(nextBookId++, title, author));
    }

    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id && book.isAvailable()) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }
}

class UserModule {
    private Library library;

    public UserModule(Library library) {
        this.library = library;
    }

    public void displayAllBooks() {
        ArrayList<Book> books = library.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void borrowBook(int bookId) {
        Book book = library.getBookById(bookId);
        if (book != null) {
            book.setAvailable(false);
            System.out.println("You have borrowed: " + book);
        } else {
            System.out.println("Book not found or not available.");
        }
    }
}

class AdminModule {
    private Library library;

    public AdminModule(Library library) {
        this.library = library;
    }

    public void addBook(String title, String author) {
        library.addBook(title, author);
        System.out.println("Book added successfully.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        UserModule userModule = new UserModule(library);
        AdminModule adminModule = new AdminModule(library);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Library Management System\n");
            System.out.println("1. User Module");
            System.out.println("2. Admin Module");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    userMenu(userModule, scanner);
                    break;
                case 2:
                    adminMenu(adminModule, scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void userMenu(UserModule userModule, Scanner scanner) {
        while (true) {
            System.out.println("\nUser Module\n");
            System.out.println("1. Display All Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userModule.displayAllBooks();
                    break;
                case 2:
                    System.out.print("Enter the book ID to borrow: ");
                    int bookId = scanner.nextInt();
                    userModule.borrowBook(bookId);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu(AdminModule adminModule, Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Module\n");
            System.out.println("1. Add a Book");
            System.out.println("2. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author: ");
                    String author = scanner.nextLine();
                    adminModule.addBook(title, author);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
