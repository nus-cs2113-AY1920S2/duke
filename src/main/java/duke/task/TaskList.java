package duke.task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static Task getIndex(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static void clearList() {
        tasks.clear();
    }

    public static void markTask(int index) {
        Task completedTask = tasks.get(index);
        completedTask.markAsDone();
    }

    public static void add(Task newItem) {
        tasks.add(newItem);
    }

    public static void addEvent(String description, String duration) {
        Event newEvent = new Event(description,duration);
        tasks.add(newEvent);
        /* throws formaterrorexception,ioexception
        int index = command.indexOf("/at");
        if (index == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1, index - 1);
        String duration = command.substring(index + timingSpecifier);
        */
    }

    public static void completeTask(int index) {
        Task completedTask = tasks.get(index-1);
        completedTask.markAsDone();
        /* throws OutOfBoundsException, IOException
        int index = Integer.parseInt(phrases[1]);
        if (index > instructions.size()) {
            throw new OutOfBoundsException();
        }
        if (type.equals("complete")) {
            System.out.println(completeMessage);
            System.out.println(spacing + completedTask);
        } else {
            System.out.println(deleteMessage);
            System.out.println(spacing + completedTask);
            System.out.println("Now you have " + (instructions.size()-1) + " tasks in the list.");
        }
        FileWriter textAdder = new FileWriter("duke.txt");
        for (int i = 0; i < instructions.size(); i += 1) {
            Task newVersion = instructions.get(i);
            if (newVersion instanceof Todo) {
                textAdder.write("T|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "\n");
            } else if (newVersion instanceof Event) {
                textAdder.write("E|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "|"
                        + ((Event) newVersion).getDuration() + "\n");
            } else {
                textAdder.write("D|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "|"
                        + ((Deadline) newVersion).getBy() + "\n");
            }
        }
        textAdder.close();*/
    }

    public static void removeTask(int index) {
        Task unwantedTask = tasks.get(index);
        tasks.remove(unwantedTask);
    }
}
