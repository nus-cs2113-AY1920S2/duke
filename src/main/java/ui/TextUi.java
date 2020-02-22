package ui;

import common.Messages;


public class TextUi {

    public static final int DISPLAYED_INDEX_OFFSET = 1;

    //display welcome message
    public static void showWelcomeMessage (){
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.MESSAGE_WELCOME);
        //System.out.println(Messages.LOGO);
        System.out.println(Messages.DIVIDER);
    }

    //display farewell message
    public static void showFarewellMessage (){
        System.out.println(Messages.DIVIDER);
        System.out.println(Messages.MESSAGE_FAREWELL);
        System.out.println(Messages.DIVIDER);
    }

    //echo function, display user's input
    public static void showResult(String text) {
        System.out.println(Messages.DIVIDER);
        System.out.println(text);
        System.out.println(Messages.DIVIDER);
    }

}
