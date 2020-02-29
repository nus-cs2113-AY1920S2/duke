package duke.util;

import duke.taskmanager.Tasks;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<Tasks> tasks = new ArrayList<>();
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

    public TaskList() throws IOException {
        tasks = new ArrayList<>();
        Load load = new Load(Paths.get("data/myTasks.txt"));
        tasks = load.readData();
    }


    public static void printIntro() {
        System.out.printf(FORMAT, "Your current task list:");
    }

    public static void printEmpty() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "You have no ongoing task.");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }


    public List<Tasks> getTasks() {
        return tasks;
    }
    public static Tasks getTask(int index) {
        return tasks.get(index);
    }
    public static void add(Tasks task) {
        tasks.add(task);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static void showList() {
        printIntro();
        if (getSize() == 0){
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
