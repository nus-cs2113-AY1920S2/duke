package ui;

import common.Messages;
import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;


public class TextUi {

    public static final int DISPLAYED_INDEX_OFFSET = 1;

    //display welcome message
    public static void showWelcomeMessage (){
        AnsiConsole.systemInstall();
        System.out.println( ansi().eraseScreen().fg(BLUE).a(Messages.DIVIDER).reset() );
        System.out.println( ansi().fg(GREEN).a(Messages.MESSAGE_WELCOME).reset() );
        System.out.println( ansi().fg(BLUE).a(Messages.DIVIDER).reset() );
        AnsiConsole.systemUninstall();
    }

    //display farewell message
    public static void showFarewellMessage (){
        AnsiConsole.systemInstall();
        System.out.println( ansi().fg(BLUE).a(Messages.DIVIDER).reset() );
        System.out.println( ansi().fg(GREEN).a(Messages.MESSAGE_FAREWELL).reset() );
        System.out.println( ansi().fg(BLUE).a(Messages.DIVIDER).reset() );
        AnsiConsole.systemUninstall();
    }

    //echo function, display user's input
    public static void showResult(String text) {
        AnsiConsole.systemInstall();
        System.out.println( ansi().fg(BLUE).a(Messages.DIVIDER).reset() );
        System.out.println( ansi().fg(GREEN).a(text).reset() );
        System.out.println( ansi().fg(BLUE).a(Messages.DIVIDER).reset() );
        AnsiConsole.systemUninstall();
    }

}
