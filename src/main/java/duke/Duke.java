package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    //public static final int MAXIMUM_TASKS = 100;
    public static final String HAPPY_FACE = "(＾▽＾)";
    public static final String SAD_FACE = "(╥_╥)";
    private static final String DIVIDER = "===================================================";
    //private static Task[] tasks;
    //private static int taskNumber;
    private static String s;

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        printUserGreeting(name);
        manageTasks(sc, name);
    }

    private static void manageTasks(Scanner sc, String name) {
        //Task[]  tasks = new Task[MAXIMUM_TASKS];

        ArrayList<Task> tasks = new ArrayList<Task>();
        //int taskNumber = 0;

        while (true) {
            String input = sc.nextLine();
            String[] parseInput = input.split(" ", 2);
            String command = parseInput[0];
            //parseInput[1].isEmpty() for empty string, not empty spaces

            try {
                if (command.equalsIgnoreCase("done")) {
                    try {
                        printDoneTasks(tasks, parseInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(String.format("%50s", "Please include task number " + SAD_FACE));
                        System.out.println(DIVIDER);
                    }
                } else if (command.equalsIgnoreCase("list")) {
                    printList(tasks);
                } else if (command.equalsIgnoreCase("todo")) {
                    addToDo(tasks, parseInput);
                } else if (command.equalsIgnoreCase("bye")) {
                    printByeMessage(name);
                    break;
                } else if (command.equalsIgnoreCase("event")) {
                    try {
                        addEvent(tasks, parseInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(String.format("%50s", "Oops! Information is incomplete " + SAD_FACE));
                        System.out.println(DIVIDER);
                    }
                } else if (command.equalsIgnoreCase("deadline")) {
                    try {
                        addDeadline(tasks, parseInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(String.format("%50s", "Oops! Information is incomplete " + SAD_FACE));
                        System.out.println(DIVIDER);
                    }
                } else if (command.equalsIgnoreCase("delete")) {
                    try {
                        int removeTask = Integer.parseInt(parseInput[1])-1;
                        System.out.println(String.format("%50s", "Noted. I've removed this task: "+ HAPPY_FACE));
                        System.out.println(String.format("%50s", tasks.get(removeTask)));
                        tasks.remove(removeTask);
                        System.out.println(String.format("%50s", "Now you have " + tasks.size() + " tasks in the list."));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(String.format("%50s", "Please include task number in the list " + SAD_FACE));
                        System.out.println(DIVIDER);
                    } catch (NumberFormatException e) {
                        System.out.println(String.format("%50s", "Oops! Information is incomplete " + SAD_FACE));
                        System.out.println(DIVIDER);
                    }
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                //catch invalid commands
                System.out.println(String.format("%50s", "Oops! I don't know what that means " + SAD_FACE));
                System.out.println(DIVIDER);
            }
        }
    }

    private static void printList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println(String.format("%50s", "YAYYYY! There are no tasks in your list " + HAPPY_FACE));
            System.out.println(DIVIDER);
            return;
        }
        System.out.println(String.format("%50s", "Here are the tasks in your list:"));
        for (int i = 1; i <= tasks.size(); i++) { //is it better to start from 0 ?
            System.out.println(String.format("%50s", i + ". " + tasks.get(i-1)));
        }
        System.out.println(DIVIDER);
    }

    private static void addDeadline(ArrayList<Task> tasks, String parseInput) {
        try {
            String[] deadline;
            deadline = parseInput.split("/by", 2);
            deadline[0].trim();
            if (!deadline[0].isEmpty()) {
                String time = deadline[1];
                if (time.isEmpty()) {
                    System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
                    System.out.println(DIVIDER);
                }
                tasks.add(new Deadline(deadline[0], time));
                System.out.println(String.format("%50s", "Got it. I've added this deadline:"));
                System.out.println(String.format("%50s", tasks.get(tasks.size()-1)));
                System.out.println(String.format("\n%50s", tasks.size() + " tasks in the list " + SAD_FACE));
                System.out.println(DIVIDER);
            } else {
                //catch empty string
//                System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
//                System.out.println(DIVIDER);
                throw new DukeException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(String.format("%50s", "Oops! Please include /by follows by the date " + SAD_FACE));
            System.out.println(DIVIDER);
        } catch (DukeException e) {
            System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
            System.out.println(DIVIDER);
        }
    }

    private static void addEvent(ArrayList<Task> tasks, String parseInput) {
        try {
            String[] event;
            event = parseInput.split("/at", 2);
            event[0].trim();
            if (!event[0].isEmpty()) {
                String time = event[1].trim();
                if (time.isEmpty()) {
                    System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
                    System.out.println(DIVIDER);
                }
                tasks.add(new Event(event[0], time));
                System.out.println(String.format("%50s", "Got it. I've added this event:"));
                System.out.println(String.format("%50s", tasks.get(tasks.size()-1)));
                System.out.println(String.format("\n%50s", tasks.size() + " tasks in the list " + SAD_FACE));
                System.out.println(DIVIDER);
            } else {
                //catch empty string
//                System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
//                System.out.println(DIVIDER);
                throw new DukeException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(String.format("%50s", "Oops! Please include /at follows by the date " + SAD_FACE));
            System.out.println(DIVIDER);
        } catch (DukeException e) {
            System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
            System.out.println(DIVIDER);
        }
    }

    private static void addToDo(ArrayList<Task> tasks, String[] parseInput) {
        try {
            if (!parseInput[1].isEmpty()) {
                tasks.add(new ToDo(parseInput[1]));
                System.out.println(String.format("%50s", "Got it. I've added this task:"));
                System.out.println(String.format("%50s", tasks.get(tasks.size()-1)));
                System.out.println(String.format("\n%50s", tasks.size() + " tasks in the list " + SAD_FACE));
                System.out.println(DIVIDER);
            } else {
                //empty string
                //System.out.println(String.format("%50s", "Please include task number " + SAD_FACE));
                //System.out.println(DIVIDER);
                throw new DukeException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
            System.out.println(DIVIDER);
        } catch (DukeException e) {
            System.out.println(String.format("%50s", "Please include task description. " + SAD_FACE));
            System.out.println(DIVIDER);
        }
    }

    private static void printDoneTasks(ArrayList<Task> tasks, String s) {
        try {
            int doneTask = Integer.parseInt(s)-1;
            tasks.get(doneTask).updateTask();
            System.out.println(String.format("%50s", "Nice! I've marked this task as done:"));
            System.out.println(String.format("%50s", tasks.get(doneTask)));
            System.out.println(DIVIDER);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format("Task not included in the list, please try again."));
            System.out.println(DIVIDER);
        } catch (Exception e) {
            //done + empty string/invalid input
            System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
            System.out.println(DIVIDER);
        }
    }

    private static void printByeMessage(String name) {
        System.out.println(String.format("%50s", "Bye, " + name + ". Hope to see you again soon!"));
        System.out.println(DIVIDER);
    }

    private static void printUserGreeting(String name) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%50s", "Hello " + name + ", Anything I can help you with?"));
        System.out.println(DIVIDER);
    }

    private static void printWelcomeMessage() {
        String logo = " /$$   /$$                     /$$\n"
                + "| $$  /$$/                    |__/\n"
                + "| $$ /$$/   /$$$$$$   /$$$$$$$ /$$ /$$$$$$$\n"
                + "| $$$$$/   /$$__  $$ /$$_____/| $$| $$__  $$\n"
                + "| $$  $$  | $$$$$$$$|  $$$$$$ | $$| $$  \\ $$\n"
                + "| $$\\  $$ | $$_____/ \\____  $$| $$| $$  | $$\n"
                + "| $$ \\  $$|  $$$$$$$ /$$$$$$$/| $$| $$  | $$\n"
                + "|__/  \\__/ \\_______/|_______/ |__/|__/  |__/";

        System.out.println("Hello from\n" + logo);
        System.out.println(DIVIDER);
        String s1 = String.format("%50s", "What's your name?");
        System.out.println(s1);
    }
}