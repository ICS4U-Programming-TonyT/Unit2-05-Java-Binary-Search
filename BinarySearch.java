import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This program generates a list of random numbers, sorts them, and
 * allows the user to search for a specific number using binary search.
 * The program continues to prompt the user for a number until they
 * choose to quit by entering "q". The program handles invalid input
 * gracefully and provides feedback on whether the number was found
 * in the list.
 * @author Tony Tran
 * @version 1.0
 * @since 2025-03-31
 */
final class BinarySearch {
    /**
     * This is a private constructor to satisfy style checker.
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private BinarySearch() {
        throw new IllegalStateException("Utility class");
    }    
    /**
     * This is the number of brackets to generate.
     */
    private static final int NUMBER_BRACKET = 1;
    /**
     * This is the number of random numbers to generate in each line.
     */
    private static final int AMOUNT_NUMBER = 10;
    /**
     * This is the maximum amount of random numbers to generate.
     */
    private static final int MAXIMUM_AMOUNT = 101;
    /**
     * This is the main method to run the program.
     * @param args
     */
    public static void main(final String[] args) throws Exception {
        // Create a Random object to generate random numbers
        Random rand = new Random();
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Variable to hold user input
        String userInput = "";
        do {
            try {
                // Create an array to hold the random numbers
                int[][] randomNumbers = new int[NUMBER_BRACKET][AMOUNT_NUMBER];
                // Generate random numbers and fill the array
                for (int lines = 0; lines < NUMBER_BRACKET; lines++) {
                    // Fill each line with 10 random numbers
                    for (int numBracket = 0; numBracket < AMOUNT_NUMBER;
                     numBracket++) {
                        randomNumbers[lines][numBracket] = rand.nextInt(
                            MAXIMUM_AMOUNT); // Random number between 0 and 100
                    }
                }
                // Sort the first line of random numbers
                Arrays.sort(randomNumbers[0]);
                // Print the random numbers
                System.out.println("What number are you searching for in the "
                 + "list below? Enter \"q\" to quit.");
                System.out.print(Arrays.toString(
                        randomNumbers[0]));
                System.out.print(" Number: ");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("q")) {
                    break;
                }
                int intUserInput = Integer.parseInt(userInput);
                // Perform binary search on the sorted array
                int left = 0;
                int right = randomNumbers[0].length - 1;
                int index = -1; // Initialize index to -1 (not found)
                while (left <= right) {
                    int mid = left + (right - left) / 2; // Calculate mid index
                    if (randomNumbers[0][mid] == intUserInput) {
                        index = mid; // Number found at index mid
                        break;
                    }
                    if (randomNumbers[0][mid] < intUserInput) {
                        left = mid + 1; // Search in the right half
                    } else {
                        right = mid - 1; // Search in the left half
                    }
                }
                // Check if the number was found
                if (index != -1) {
                    System.out.println("The number " + intUserInput
                     + " is found at index " + index + ".");
                } else {
                    System.out.println("The number " + intUserInput
                     + " is not found in the list of numbers.");
                }

            } catch (NumberFormatException error) {
                System.out.println("An error occurred: " + error.getMessage());
            }
        } while (!userInput.equalsIgnoreCase("q"));
        scanner.close();
        System.out.println("Thank you for playing!");
    }
}
