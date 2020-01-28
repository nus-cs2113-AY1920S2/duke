import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<Task>();

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
        }else if (str.contains("done")) {
            markDone(str);
            listTask();
            chat();
        }else {
            System.out.println(str);
            System.out.println("____________________________________________________________");
            chat();
        }
    }


    public static void addTaskScreen() throws IOException {
        System.out.println("____________________________________________________________");
        System.out.println("Please add tasks");
        System.out.println("____________________________________________________________");
        String str = readCommand();
        if (str.equals("bye")) {
            bye();
        }else if (str.contains("done")) {
            System.out.println("Mark task done");
            markDone(str);
            addTaskScreen();
        }else if (str.equals("list")) {
            listTask();
            addTaskScreen();
        }else {
            addTask(str);
            addTaskScreen();
        }
    }

    public static void addTask(String newTask) throws IOException {
        Task task = new Task(newTask);
        taskList.add(task);
        System.out.println("added : " + newTask);
    }

    public static void listTask() {
        System.out.println("____________________________________________________________");
        int i = 0;
        while (i < taskList.size()) {
            int j = i + 1;
            System.out.println(j + " ." + "[" +  taskList.get(i).getStatusIcon() + "]" + taskList.get(i).displayTask());
            i++;
        }

        System.out.println("____________________________________________________________");
    }

    public  static  void markDone(String str){
        int dividerPosition = str.indexOf(" ");
        String index = str.substring(dividerPosition+1);
        int i = Integer.parseInt(index);
        System.out.println(i);
        if(i == 0){
            System.out.println("____________________________________________________________");
            System.out.println("Invalid index");
            System.out.println("____________________________________________________________");
        }else{
            taskList.get(i-1).markDone();
        }
        listTask();
    }


}




