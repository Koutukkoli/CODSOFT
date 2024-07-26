import java.util.*;

public class NumberGuessTask {
    private static final int MAX_CHANCES = 5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean playAgain;
        int totalScore = 0;

        do {
            int targetNum = rand.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("A number between 1 to 100 has been generated. Try to guess it!");
            System.out.println("You have 5 chances.");

            while (attempts < MAX_CHANCES && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == targetNum) {
                    System.out.println("Excellent! You guessed it right.");
                    guessedCorrectly = true;
                    totalScore += (MAX_CHANCES - attempts + 1);
                } else if (guess > targetNum) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You ran out of chances.");
                System.out.println("The correct number was: " + targetNum);
            }

            System.out.println("Current score: " + totalScore);
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = sc.next().equalsIgnoreCase("yes");

        } while (playAgain);

        sc.close();
        System.out.println("Thanks for playing!");
    }
}
