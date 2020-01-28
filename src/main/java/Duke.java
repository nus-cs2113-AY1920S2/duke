import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> taskList = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        chat();


    }

    private static String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Type something");
        str = br.readLine().toLowerCase();
        return str;
    }

    public static void greeting() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        System.out.println("____________________________________________________________");

    }

    public static void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }


    public static void chat() throws IOException {
        System.out.println("____________________________________________________________");
        String str = readCommand();
        System.out.println("____________________________________________________________");

        if (str.equals("bye")) {
            bye();
        } else if (str.equals("add task")) {
            System.out.println("____________________________________________________________");
            System.out.println("Please add no more than 100 tasks");
            System.out.println("____________________________________________________________");
            addTaskScreen();
        } else if (str.equals("list")) {
            listTask();
            chat();
        } else {
            System.out.println(str);
            System.out.println("____________________________________________________________");
            chat();
        }
    }


    public static void addTaskScreen() throws IOException {
        System.out.println("____________________________________________________________");
        System.out.println("Please add tasks");
        System.out.println("____________________________________________________________");
        String newTask = readCommand();
        if (newTask.equals("bye")) {
            bye();
        } else {
            addTask(newTask);
            addTaskScreen();
        }
    }

    public static void addTask(String newTask) {
        if (newTask.equals("list")) {
            listTask();
        } else {
            taskList.add(newTask);
            System.out.println("added : " + newTask);
        }
    }

    public static void listTask() {
        System.out.println("____________________________________________________________");
        int i = 0;
        while (i < taskList.size()) {
            int j = i + 1;
            System.out.println(j + " ." + taskList.get(i));
            i++;
        }

        System.out.println("____________________________________________________________");
    }

}




