package duke;

import java.util.Scanner;

public class Parser {

    public String getCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().toLowerCase();
    }

}
