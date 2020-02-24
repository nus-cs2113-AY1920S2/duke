package UI;

import Duke.Task;

import java.util.ArrayList;

public class TextUI {

    public static void printList(ArrayList<Task> tasks) {
        int count = 1;
        System.out.println("Listing tasks below:");

        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment!");
        } else {
            for (Task currentTask : tasks) {
                currentTask.printListDetails(count);
                count++;
            }
        }
        System.out.println("");
    }

    public static void printHelp() {
        System.out.println("Commands: ");
        System.out.println("List: lists all recorded tasks \nusage: list\n");
        System.out.println("Done: mark task as completed \nusage: done <task number>\n");
        System.out.println("Todo: Tasks without date/time \nUsage: todo <task> \n(Avoid using other keywords as the first word)\n");
        System.out.println("Duke.Event: Duke.Event including date/time \nUsage: event <task> /<date> \n(Avoid using other keywords as the first word)\n");
        System.out.println("Duke.Deadline: Tasks including date/time \nUsage: deadline <task> /<date> \n(Avoid using other keywords as the first word)\n");
        System.out.println("");
    }

}
