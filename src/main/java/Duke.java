import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ";
    private static final String SPACE = "  ";
    private static final List<Task> tasksList = new ArrayList<>(100);
    private static final String LIST_COMMAND = "list";
    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

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

    private static void replyUser(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            printBorder();
            if(input.equals(BYE_COMMAND)) {
                printMessage("Bye. Hope to see you again soon!");
                printBorder();
                break;
            }
            else if(input.equals(LIST_COMMAND)){
                if (tasksList.size() == 0){
                    printMessage("There are no tasks in your list!");
                    printBorder();
                }
                else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasksList.size(); i++) {
                        printMessage((i + 1) + ". " + tasksList.get(i).toString());
                    }
                    printBorder();
                }
            }
            else{
                String[] line = input.split(" ");
                List<String> taskList = Arrays.asList(line);
                String command = line[0];
                List<String> arguments = taskList.subList(1, taskList.size());
                Task task = null;
                String s;
                boolean isTask = false;
                switch(command){
                case DONE_COMMAND:
                    if(line.length == 2 && isNumeric(line[1])) {
                        int i = Integer.parseInt(line[1]);
                        if (i > 0 && i <= tasksList.size()) {
                            System.out.println("Nice! I've marked this task as done");
                            tasksList.get(i - 1).markAsDone();
                            System.out.println(i + ". " + tasksList.get(i - 1).toString());
                        } else {
                            System.out.println("Task not found");
                            printBorder();
                        }
                    }
                    break;

                case TODO_COMMAND:
                    isTask = true;
                    s = String.join("", arguments);
                    task = new Todo(s);
                    break;

                case DEADLINE_COMMAND:
                    isTask = true;
                    int index = taskList.indexOf("/by");
                    if(index == -1){
                        System.out.println("Please give a deadline: ");
                        printBorder();
                        continue;
                    }
                    String by = String.join("", taskList.subList(index + 1, taskList.size()));
                    String description = String.join("", taskList.subList(1, index));
                    task = new Deadline(description, by);
                    break;

                case EVENT_COMMAND:
                    isTask = true;
                    index = taskList.indexOf("/at");
                    if(index == -1){
                        System.out.println("Please give a location: ");
                        printBorder();
                        continue;
                    }
                    String at = String.join("", taskList.subList(index + 1, taskList.size()));
                    description = String.join("", taskList.subList(1, index));
                    task = new Event(description, at);
                    break;

                default:
                    System.out.println("invalid");
                    printBorder();
                }
                if(isTask) {
                    tasksList.add(task);
                    System.out.println("Got it! I've added this task: ");
                    System.out.println(" " + task.toString());
                    System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                    printBorder();
                }
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
        replyUser();
    }
}
