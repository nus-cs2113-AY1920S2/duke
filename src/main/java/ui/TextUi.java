package ui;

import common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {

    //display welcome message
    public static void showWelcomeMessage (){
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.MESSAGE_WELCOME);
        System.out.println(Messages.DIVIDER);
    }

    //display farewell message
    public static void showFarewellMessage (){
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.MESSAGE_FAREWELL);
        System.out.println(Messages.DIVIDER);
    }

    //echo function, display user's input
    public static void echo(String text) {
        System.out.println(Messages.DIVIDER);
        System.out.println("  "+ text);
        System.out.println(Messages.DIVIDER);
    }

//    public String getUserCommand() {
//
//        String fullInputLine = scanner.nextLine();
//        //System.out.println("[Command entered:" + fullInputLine + "]");;
//        return fullInputLine;
//    }
}
