package ui;

import common.Messages;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;


public class TextUi {

    public static final int DISPLAYED_INDEX_OFFSET = 1;

    //display welcome message
    public static void showWelcomeMessage (){
        AnsiConsole.systemInstall();
        printDivider(BLUE);
        System.out.println( ansi().bold().fg(GREEN).a(Messages.MESSAGE_WELCOME).reset() );
        printDivider(BLUE);
        AnsiConsole.systemUninstall();
    }

    //display farewell message
    public static void showFarewellMessage (){
        AnsiConsole.systemInstall();
        printDivider(BLUE);
        System.out.println( ansi().bold().fg(GREEN).a(Messages.MESSAGE_FAREWELL).reset() );
        printDivider(BLUE);
        AnsiConsole.systemUninstall();
    }

    //echo function, display user's input
    public static void showResult(String text) {
        AnsiConsole.systemInstall();
        printDivider(BLUE);
        System.out.println( ansi().bold().fg(GREEN).a(text).reset() );
        printDivider(BLUE);
        AnsiConsole.systemUninstall();
    }

    public static void displayLogo() {
        AnsiConsole.systemInstall();
        System.out.println( ansi().bold().eraseScreen().fg(MAGENTA).a(Messages.LOGO).reset() );
        printDivider(BLUE);
    }

    public static int getUserChoice() {
        int userChoice;
        AnsiConsole.systemInstall();
        printDivider(BLUE);
        System.out.println( ansi().bold().fg(GREEN).a(Messages.MESSAGE_ASK_TO_CHOOSE_UI).reset() );
        printDivider(BLUE);
        try {
            Scanner sc = new Scanner(System.in);
            userChoice = sc.nextInt();
            AnsiConsole.systemUninstall();
            return userChoice;
        } catch (InputMismatchException ex) {
            AnsiConsole.systemUninstall();
            getUserChoice();
            return -1;
        }
    }

    public static void askForReInput() {
        AnsiConsole.systemInstall();
        printDivider(BLUE);
        System.out.println( ansi().bold().fg(RED).a(Messages.MESSAGE_INVALID_USER_CHOICE).reset() );
        AnsiConsole.systemUninstall();
    }

    public static void acknowledgementUserChoice(int userChoice){
        switch (userChoice){
        case 1:
            System.out.println( ansi().bold().fg(GREEN).a(Messages.MESSAGE_THANKS_FOR_GUI).reset() );
            break;
        case 2:
            System.out.println( ansi().bold().fg(GREEN).a(Messages.MESSAGE_THANKS_FOR_CLI).reset() );
            break;
        }
    }

    public static void printDivider(Ansi.Color color){
        System.out.println( ansi().bold().fg(color).a(Messages.DIVIDER).reset() );
    }
}
