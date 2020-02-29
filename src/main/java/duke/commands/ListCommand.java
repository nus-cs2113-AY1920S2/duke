package duke.commands;

import duke.taskmanager.Tasks;

import java.util.List;

public class ListCommand extends Command {
    private static final String FORMAT = "0O=-             %-60s-=O0%n";
    private static final String SPLIT = "=============================" +
            "====================================================";

    private static final String BLANK_LINE = "0O=-                      " +
            "                                                   -=O0";

    private static final String SPLIT_UPPER_BOUNDARY = SPLIT +"\n000000000000000" +
            "00000000000000000000000000000000000000000000000000000000000" +
            "0000000\n" + BLANK_LINE;

    private static final String SPLIT_LOWER_BOUNDARY = BLANK_LINE + "\n0000000" +
            "00000000000000000000000000000000000000000000000000000000" +
            "000000000000000000\n" + SPLIT;

    public ListCommand() {
    }

    public static void printIntro() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Your current task list:");
    }

    public static void printEmpty() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "You have no ongoing task.");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }

    public static void execute(List<Tasks> tasks) {
        printIntro();
        if (tasks.size() == 0){
            printEmpty();
        } else {
            int index = 0;
            for (Tasks task : tasks) {
                System.out.printf(FORMAT, index + ". "+ task.toString());
                index++;
            }
            System.out.println(SPLIT_LOWER_BOUNDARY);
        }
    }
}
