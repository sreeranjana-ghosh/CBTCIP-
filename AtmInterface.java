import java.util.Scanner;

class Date {
    int dd;
    int mm;
    int yy;
}

class Account {
    String name;
    int acc_no;
    float payment;
    float old_balance;
    float new_balance;
    Date last_payment;
}

public class AtmInterface {
    public static void newAcc(Account[] acc, int i) {
        Scanner scanner = new Scanner(System.in);
        acc[i] = new Account(); // Initialize the account object
        System.out.print("Enter account holder's name: ");
        acc[i].name = scanner.nextLine();
        System.out.print("Enter account number: ");
        acc[i].acc_no = scanner.nextInt();
        System.out.print("Enter opening balance: ");
        acc[i].payment = scanner.nextFloat();
        acc[i].old_balance = 0.0f;
        acc[i].new_balance = acc[i].payment + acc[i].old_balance;
        acc[i].last_payment = new Date();
        System.out.print("Enter date (DD MM YY): ");
        acc[i].last_payment.dd = scanner.nextInt();
        acc[i].last_payment.mm = scanner.nextInt();
        acc[i].last_payment.yy = scanner.nextInt();
    }

    public static void deposit(Account[] acc, int i) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value of money to deposit: ");
        float depositAmount = scanner.nextFloat();
        acc[i].payment = depositAmount;
        acc[i].old_balance = acc[i].new_balance;
        acc[i].new_balance = acc[i].old_balance + acc[i].payment;
        System.out.print("Enter date (DD MM YY): ");
        acc[i].last_payment.dd = scanner.nextInt();
        acc[i].last_payment.mm = scanner.nextInt();
        acc[i].last_payment.yy = scanner.nextInt();
    }

    public static void withdraw(Account[] acc, int i) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value of money to withdraw: ");
        float withdrawAmount = scanner.nextFloat();
        if (withdrawAmount > acc[i].new_balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        acc[i].payment = withdrawAmount;
        acc[i].old_balance = acc[i].new_balance;
        acc[i].new_balance = acc[i].old_balance - acc[i].payment;
        System.out.print("Enter date (DD MM YY): ");
        acc[i].last_payment.dd = scanner.nextInt();
        acc[i].last_payment.mm = scanner.nextInt();
        acc[i].last_payment.yy = scanner.nextInt();
    }

    public static void viewBalance(Account[] acc, int i) {
        System.out.println("\nAccount No\tName\tOld Balance\tNew Balance\tLast Payment");
        for (int j = 0; j < i; j++) {
              System.out.println(
                    acc[j].acc_no + "\t" + "\t" + acc[j].name + "\t" + "  \t" + acc[j].old_balance + "\t" + "   \t"
                            + acc[j].new_balance + "\t" + "\t" + "\t"
                            + acc[j].last_payment.dd + "/" + acc[j].last_payment.mm + "/" + acc[j].last_payment.yy);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account[] acc = new Account[20];
        int i = 0;
        char yn;

        do {
            System.out.println("\n1. Create new account\n2. Deposit money\n3. Withdraw money\n4. Transiction");
            System.out.print("Enter your choice: ");
            int ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    newAcc(acc, i);
                    i++;
                    break;
                case 2:
                    System.out.print("Enter an account number: ");
                    int num = scanner.nextInt();
                    int flag = 0;
                    for (int j = 0; j < i; j++) {
                        if (num == acc[j].acc_no) {
                            flag = 1;
                            deposit(acc, j);
                            break;
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Bank account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter an account number: ");
                    num = scanner.nextInt();
                    flag = 0;
                    for (int j = 0; j < i; j++) {
                        if (num == acc[j].acc_no) {
                            flag = 1;
                            withdraw(acc, j);
                            break;
                        }
                    }
                    if (flag == 0) {
                        System.out.println("Bank account not found.");
                    }
                    break;
                case 4:
                    viewBalance(acc, i);
                    break;
                default:
                    System.out.println("Sorry! Invalid choice.");
            }

            System.out.print("\nDo you want to continue? (Y/N): ");
            yn = scanner.next().charAt(0);

        } while (yn == 'Y' || yn == 'y');

        scanner.close(); // Close the Scanner after using it
    }
}
