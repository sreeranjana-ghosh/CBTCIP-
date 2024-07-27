import java.util.*;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxRange = 100;
        int minRange = 1;
        int maxAttempts = 10;
        int attempts = 0;
        int finalattempts = 0;
        int maxfinalAttempts = 5;
        Random random = new Random();
        int Round1targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
        int finaltargetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
        System.out.println("Welcome to Guess the Number game Round1!");
        System.out.println("I have chosen a number between " + minRange + " and " + maxRange + ".");
        System.out.println("Can you guess it in " + maxAttempts + " attempts or less?");
        while (attempts < maxAttempts) {
            System.out.println("Enter your guess:");
            int Round1guess = sc.nextInt();
            if (Round1guess == Round1targetNumber) {
                System.out.println("Congratulations! You guessed the number " + Round1targetNumber + " in "
                        + (attempts + 1) + " attempts.");
                System.out.println("Score:" + (100-((attempts + 1) * 10)) + "/" + 100);
                System.out.println("Welcome to the Final round");
                System.out.println("I have chosen a number between " + minRange + " and " + maxRange + ".");
                System.out.println("Can you guess it in " + maxfinalAttempts + " attempts or less?");
                break;
            } else if (Round1guess > Round1targetNumber) {
                System.out.println("Your guess is too large!!");
            } else {
                System.out.println("Your guess is too small");
            }
            attempts++;
        }
        if (attempts >= maxAttempts) {
            System.out.println(
                    "Sorry, you've reached the maximum number of attempts. The number was " + Round1targetNumber + ".");
        }
        while (finalattempts < maxfinalAttempts) {
            System.out.println("Enter your guess:");
            int finalguess = sc.nextInt();
            if (finalguess == finaltargetNumber) {
                System.out.println("Congratulations! You guessed the number " + finaltargetNumber + " in "
                        + (finalattempts + 1) + " attempts.");
                 System.out.println("Final Score :" +  (100-((finalattempts + 1)*10)) + "/" + 100);
                break;
            } else if (finalguess > finaltargetNumber) {
                System.out.println("Your guess is too large!!");
            } else {
                System.out.println("Your guess is too small");
            }
            finalattempts++;
        }
        if (finalattempts >= maxfinalAttempts) {
            System.out.println(
                    "Sorry, you've reached the maximum number of attempts. The number was " + finaltargetNumber + ".");
        }
    }
}
