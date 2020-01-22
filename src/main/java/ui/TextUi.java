package ui;

public class TextUi {

    private static final String DIVIDER = "===================================================";
    private static final String MESSAGE_WELCOME = "  Hello! I'm Duke\n  What can I do for you?";
    private static final String MESSAGE_FAREWELL = "  Bye. Hope to see you again soon!";

    public TextUi() {
    }

    //display welcome message
    public static void showWelcomeMessage (){
        System.out.println(DIVIDER);
        System.out.println(MESSAGE_WELCOME);
        System.out.println(DIVIDER);
    }

    //display farewell message
    public static void showFarewellMessage (){
        System.out.println(DIVIDER);
        System.out.println(MESSAGE_FAREWELL);
        System.out.println(DIVIDER);
    }

    //echo function, display user's input
    public static void echo(String text) {
        System.out.println(DIVIDER);
        System.out.println("  "+ text);
        System.out.println(DIVIDER);
    }
}
