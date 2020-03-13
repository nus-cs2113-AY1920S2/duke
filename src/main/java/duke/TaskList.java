package duke;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    /**
     * Takes in the list in taskList
     * and list the input to the terminal.
     */
    public static void listTask() {
        System.out.println("____________________________________________________________");
        int i = 0;
        while (i < taskList.size()) {
            int j = i + 1;
            System.out.println(j + " ." + taskList.get(i).toString());
            i++;
        }

        System.out.println("____________________________________________________________");
    }

    /**
     * Add a task item into taskList
     * and prints a message
     *
     * @throws IOException
     */
    public static void addTask(Task newTask){
        System.out.println("Got it. I've added this task: " + newTask.description);
        taskList.add(newTask);
        System.out.println("added : " + newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Deletes a task according to the index
     * Raises error if index is missing or out of range
     *
     * @param str A string from the terminal
     * @throws IOException DukeException if index is missing or out of range
     */
    public static void deleteTask(String str) throws DukeException {
        if (str.equals("delete")) {
            new DukeException("OOPS!!! The index cannot be empty.");
        } else {
            int dividerPosition = str.indexOf(" ");
            String index = str.substring(dividerPosition + 1);
            int i = Integer.parseInt(index);
            if (i == 0) {
                new DukeException("OOPS!!!Invalid input index");
            } else if (i > taskList.size()) {
                new DukeException("OOPS!!!Index out of range");
            } else {
                Task toRemove = taskList.get(i - 1);
                taskList.remove(i - 1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println("Removed : " + toRemove);
                System.out.println("Now you have " + taskList.size() + " tasks in the list");
            }
        }
    }

    /**
     * Mark a task done according to the index.
     * Raises errors if index is missing or index out of range
     *
     * @throws DukeException if index is missing or out of range
     */
    public static void markDone(String str) throws DukeException{
        if (str.equals("done")) {
            new DukeException("OOPS!!! The index cannot be empty.");
        }else {
            int dividerPosition = str.indexOf(" ");
            String index = str.substring(dividerPosition + 1);
            int i = Integer.parseInt(index);
            if (i == 0) {
                new DukeException("OOPS!!!Invalid input index");
            } else if (i > taskList.size()) {
                new DukeException("OOPS!!!Index out of range");
            } else {
                taskList.get(i - 1).markDone();
            }
            listTask();
        }
    }

    /**
     * Takes in the list generated using findTask instead of the taskList
     * and list the input to the terminal.
     * @param  input A list of tasks found using keywords
     */
    public static void listSearchResult(ArrayList<Task> input) {
        System.out.println("____________________________________________________________");
        int i = 0;
        while (i < input.size()) {
            int j = i + 1;
            System.out.println(j + " ." + input.get(i).toString());
            i++;
        }

        System.out.println("____________________________________________________________");
    }

    /**
     * finds tasks  according to the index.
     * Raises errors if keyword is missing
     * Return a emppty list if no match
     * @param input A string from the terminal
     */
    public static void findTask(String input){
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        ArrayList<Task> searchResult = new ArrayList<>();
        String keyword = input.substring(5);
        for(Task task: taskList){
            if(task.description.contains(keyword)){
                searchResult.add(task);
            }
        }
        if(searchResult.size() == 0){
            System.out.println(" No matching results ");
        }
        listSearchResult(searchResult);
    }




}
