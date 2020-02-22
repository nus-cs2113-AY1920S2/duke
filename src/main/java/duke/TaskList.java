package duke;

import java.util.ArrayList;





public class TaskList {

    public static ArrayList<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<Task>();
    }


    /**
     * Returns a boolean that marks a task as done if it exists
     * Marks a task as done
     *
     * @param taskList TaskList containg all tasks
     * @param ogString Inputted command by the user that is fed as a string
     */
    public static void markTaskAsDone(ArrayList<Task> taskList, String ogString) {
        String[] words = ogString.split(" ");
        taskList.get(Integer.parseInt(words[1]) - 1).markIt();
        String statusIcon = taskList.get(Integer.parseInt(words[1]) - 1).getStatusIcon();
        String typeIcon = taskList.get(Integer.parseInt(words[1]) - 1).getTypeIcon();
        String description = taskList.get(Integer.parseInt(words[1]) - 1).getDescription();
        System.out.println((Integer.parseInt(words[1])) + ". " + typeIcon + "[" + statusIcon + "]" + " " + description);
        System.out.println("Done! We have checked " + words[1] + "!");
    }


    /**
     * Print all available tasks.
     *
     * @param taskList Tasklist of all available tasks
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        int taskCounter = 1;
        for (Task task : taskList) {
            String description = task.getDescription();
            String statusIcon = task.getStatusIcon();
            String typeIcon = task.getTypeIcon();
            System.out.println(taskCounter + ". " + typeIcon + " [" + statusIcon + "] " + description);
            taskCounter += 1;
        }
    }


    /**
     * Deletes the task at index taskNumber from the tasklist
     *
     * @param taskList Tasklist of all available tasks
     * @param ogString The index of the task we are trying delete
     */
    public static void deleteTask(ArrayList<Task> taskList, String ogString) {
        String[] words = ogString.split(" ");
        int taskNumber = Integer.parseInt(words[1].trim()) - 1;
        Task task = taskList.get(taskNumber);
        String description = task.getDescription();
        String statusIcon = task.getStatusIcon();
        String typeIcon = task.getTypeIcon();
        System.out.println("Cool, we will remove the following task:");
        System.out.println(typeIcon + " [" + statusIcon + "] " + description);
        taskList.remove(taskNumber);
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " items in your list");
    }


    public static void addTasks(ArrayList<Task> taskList, String ogString, String taskType) {
        String[] todoOrDeadlineOrEvent = Parser.returnStringToAdd(ogString, taskType);
        if (taskType.equals("event")) {
            Event t = new Event(todoOrDeadlineOrEvent[0], todoOrDeadlineOrEvent[1]);
            taskList.add(t);
        } else if (taskType.equals("deadline")) {
            Deadline t = new Deadline(todoOrDeadlineOrEvent[0], todoOrDeadlineOrEvent[1]);
            taskList.add(t);
        } else {
            Todo e = new Todo(todoOrDeadlineOrEvent[1].trim());
            taskList.add(e);
        }
    }

}







