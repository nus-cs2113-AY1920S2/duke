package duke.Util;

import duke.taskmanager.Tasks;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<Tasks> tasks = new ArrayList<>();
    private static final String format = "0O=-             %-60s-=O0%n";
    private static final String split = "=============================" +
            "====================================================";

    private static final String blankLine = "0O=-                      " +
            "                                                   -=O0";

    private static final String splitUpperBoundary = split +"\n000000000000000" +
            "00000000000000000000000000000000000000000000000000000000000" +
            "0000000\n" + blankLine;

    private static final String splitLowerBoundary = blankLine + "\n0000000" +
            "00000000000000000000000000000000000000000000000000000000" +
            "000000000000000000\n" + split;

    public TaskList() throws IOException {
        tasks = new ArrayList<>();
        Load load = new Load(Paths.get("data/myTasks.txt"));
        tasks = load.readData();
    }


    public static void printIntro() {
        System.out.printf(format, "Your current task list:");
    }

    public static void printEmpty() {
        System.out.println(splitUpperBoundary);
        System.out.printf(format, "You have no ongoing task.");
        System.out.println(splitLowerBoundary);
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
                System.out.printf(format, index + ". "+ task.toString());
                index++;
            }
            System.out.println(splitLowerBoundary);
        }
    }
}
