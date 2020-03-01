package duke;

import java.util.Scanner;

public class Ui {

    Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke\n");
        System.out.println("What can I do for you?\n");
    }

    public String readUserData() {
        return (scan.nextLine());
    }

    public void errorMessage(String err) {
        System.out.println(err);
    }

}
