package ui;

import common.Messages;
import data.task.Task;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;


public class TextUi {

    public static final int DISPLAYED_INDEX_OFFSET = 1;
    public static final Ansi.Color SYSTEM_COLOR_MESSAGE = BLUE;
    public static final Ansi.Color SYSTEM_COLOR_DIVIDER = GREEN;
    public static final Ansi.Color SYSTEM_COLOR_ALERT = RED;


    //display welcome message
    public static void showWelcomeMessage (){
        AnsiConsole.systemInstall();
        printDivider();
        System.out.println( ansi().bold().fg(SYSTEM_COLOR_MESSAGE).a(Messages.MESSAGE_WELCOME).reset() );
        printDivider();
        AnsiConsole.systemUninstall();
    }

    //display farewell message
    public static void showFarewellMessage (){
        AnsiConsole.systemInstall();
        printDivider();
        System.out.println( ansi().fg(SYSTEM_COLOR_MESSAGE).a(Messages.MESSAGE_FAREWELL).reset() );
        printDivider();
        AnsiConsole.systemUninstall();
    }

    //echo function, display user's input
    public static void showResult(String text) {
        AnsiConsole.systemInstall();
        ansi().reset();
        printDivider();
        System.out.println( ansi().fg(SYSTEM_COLOR_MESSAGE).a(text).reset() );
        printDivider();
        AnsiConsole.systemUninstall();
    }

    public static void displayLogo() {
        AnsiConsole.systemInstall();
        printMessage(SYSTEM_COLOR_MESSAGE,Messages.LOGO);
        printDivider();
        AnsiConsole.systemUninstall();
    }

    public static int getUserChoice() {
        int userChoice;
        AnsiConsole.systemInstall();
        printDivider();
        printMessage(SYSTEM_COLOR_MESSAGE, Messages.MESSAGE_ASK_TO_CHOOSE_UI);
        printDivider();
        try {
            Scanner sc = new Scanner(System.in);
            userChoice = sc.nextInt();
            return userChoice;
        } catch (InputMismatchException ex) {
            getUserChoice();
            return -1;
        }
    }

    public static void askForReInput() {
        printDivider();
        printAlert();
        printMessage(SYSTEM_COLOR_MESSAGE, Messages.MESSAGE_INVALID_USER_CHOICE);
    }

    public static void acknowledgementUserChoice(int userChoice){
        switch (userChoice){
        case 1:
            printMessage(SYSTEM_COLOR_MESSAGE, Messages.MESSAGE_THANKS_FOR_GUI);
            break;
        case 2:
            printMessage(SYSTEM_COLOR_MESSAGE, Messages.MESSAGE_THANKS_FOR_CLI);
            break;
        }
    }

    /**
     * print a divider
     */
    public static void printDivider(){
        System.out.println( ansi().bold().fg(SYSTEM_COLOR_DIVIDER).a(Messages.DIVIDER).reset() );
    }

    /**
     * clear the screen
     */
    public static void clearScreen(){
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen());
        AnsiConsole.systemUninstall();
    }

    public static void alertToAddDuplicateTask(Task toCheck){
        AnsiConsole.systemInstall();
        printAlert();
        printMessage(SYSTEM_COLOR_MESSAGE,
                String.format(Messages.MESSAGE_DUPLICATE_TASK_ALERT, toCheck.getTaskIndex()));
        AnsiConsole.systemUninstall();
    }

    public static void printAlert(){
        AnsiConsole.systemInstall();
        System.out.print( ansi().bold().fg(SYSTEM_COLOR_ALERT).a(Messages.MESSAGE_ALERT).reset() );
        AnsiConsole.systemUninstall();
    }

    public static void printMessage(Ansi.Color color, String message){
        AnsiConsole.systemInstall();
        System.out.println(ansi().fg(color).a(message).reset() );
        AnsiConsole.systemUninstall();
    }
}
