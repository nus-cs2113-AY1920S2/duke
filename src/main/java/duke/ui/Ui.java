package duke.ui;

import java.util.Scanner;
import static duke.constants.Messages.*;


public class Ui {
    Scanner scanner = new Scanner(System.in);
    public String readInput() {
        return scanner.nextLine();
    }
    public void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.println(lvlzero);
    }

    public void exit() {
        System.out.println(MESSAGE_GOODBYE);
    }

    public void showLoadingError() {
        System.out.println(MESSAGE_LOADINGERROR);
    }
}
