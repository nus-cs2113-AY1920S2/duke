package Duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ";
    private static final String SPACE = "  ";
    private static List<Task> tasksList = new ArrayList<>(100);
    private static final String LIST_COMMAND = "list";
    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String TASK_ERROR_MESSAGE = "Task not found";
    private static final String TODO_ERROR_MESSAGE = "Oops! The description of todo cannot be empty.";
    private static final String DEADLINE_ERROR_MESSAGE = "Oops! The description of deadline cannot be empty.";
    private static final String EVENT_ERROR_MESSAGE = "Oops! The description of event cannot be empty.";
    private static final String INVALID_COMMAND_MESSAGE = " Oops! Im sorry, but I don't know what that means :(";

    private static void printBorder(){
        System.out.println(BORDER);
    }
    private static void printMessage(String message){
        System.out.println(SPACE + message);
    }

    private static void greetUser(){
        printBorder();
        printMessage("Hello! I'm Duke");
        printMessage("What can I do for you?");
        printBorder();
    }

    private static void replyUser(String input) throws DukeException {
        if(input.equals(LIST_COMMAND)){
            if(tasksList.size() == 0){
                printMessage("There are no tasks in your list!");
            }
            else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasksList.size(); i++) {
                    printMessage((i + 1) + ". " + tasksList.get(i).toString());
                }
            }
        }
        else {
            String[] line = input.split(" ");
            List<String> taskList = Arrays.asList(line);
            String command = line[0];
            List<String> arguments = taskList.subList(1, taskList.size());
            Task task = null;
            String s;
            boolean isTask = false;
            switch (command) {
            case DONE_COMMAND:
                if(line.length == 2 && isNumeric(line[1])){
                    int i = Integer.parseInt(line[1]);
                    if (i > 0 && i <= tasksList.size()){
                        System.out.println("Nice! I've marked this task as done");
                        tasksList.get(i - 1).markAsDone();
                        System.out.println(i + ". " + tasksList.get(i - 1).toString());
                    } else{
                        throw new DukeException(TASK_ERROR_MESSAGE);
                    }
                }
                break;

            case DELETE_COMMAND:
                if(line.length == 2 && isNumeric(line[1])){
                    int i = Integer.parseInt(line[1]);
                    if (i > 0 && i <= tasksList.size()){
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(i + "." + tasksList.get(i-1).toString());
                        tasksList.remove(i-1);
                        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                    }
                    else{
                        throw new DukeException(TASK_ERROR_MESSAGE);
                    }
                }
                break;

            case TODO_COMMAND:
                if (line.length == 1){
                    throw new DukeException(TODO_ERROR_MESSAGE);
                }
                isTask = true;
                s = String.join(" ", arguments);
                task = new Todo(s);
                break;

            case DEADLINE_COMMAND:
                if (line.length == 1){
                    throw new DukeException(DEADLINE_ERROR_MESSAGE);
                }
                isTask = true;
                int index = taskList.indexOf("/by");
                if(index == -1){
                    throw new DukeException("Deadline undetected. Please enter again with this format - deadline *todo task* /by *deadline*: ");
                }
                String by = String.join(" ", taskList.subList(index + 1, taskList.size()));
                String description = String.join(" ", taskList.subList(1, index));
                task = new Deadline(description, by);
                break;

            case EVENT_COMMAND:
                if (line.length == 1){
                    throw new DukeException(EVENT_ERROR_MESSAGE);
                }
                isTask = true;
                index = taskList.indexOf("/at");
                if(index == -1){
                    throw new DukeException("Time undetected. Please enter again with this format - event *event name* /at *time*: ");
                }
                String at = String.join(" ", taskList.subList(index + 1, taskList.size()));
                description = String.join(" ", taskList.subList(1, index));
                task = new Event(description, at);
                break;

            default:
                throw new DukeException(INVALID_COMMAND_MESSAGE);
            }
            if(isTask) {
                tasksList.add(task);
                System.out.println("Got it! I've added this task: ");
                System.out.println(" " + task.toString());
                System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            }
        }
        printBorder();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        greetUser();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            if(input.equals(BYE_COMMAND)) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            }
            printBorder();
            try{
                replyUser(input);
            } catch (DukeException e) {
                printMessage(e.getMessage());
                printBorder();
            }
        }
    }





}