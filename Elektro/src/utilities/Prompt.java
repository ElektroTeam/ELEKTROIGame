package utilities;

import music.manager.AudioLoader;
import music.enums.AudioFile;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for prompts related actions.
 */
public class Prompt {
    /**
     * Requests an integer input from the user.
     * If the input is not a valid integer, it prompts the user to try again.
     * @return the integer input provided by the user.
     */
    public static int requestInt(){
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        try{
            option = scanner.nextInt();
            scanner.nextLine();
        } catch(InputMismatchException e){
            System.out.print("You've entered an invalid input, please try again: ");
            option = requestInt();
        } catch(Exception e){
            System.out.println("An unexpected error occurred: ");
            e.printStackTrace();
        }
        return option;
    }
    /**
     * Prompts the user to press "ENTER" to continue.
     * This method stops the program until the user presses ENTER.
     */
    public static void promptEnterToContinue(){
        System.out.print("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        AudioLoader.playSound(AudioFile.NEXT);
    }
    /**
     * Clears the terminal screen.
     * This method works in the OS terminal (not IntelliJ terminal).
     */
    public static void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
