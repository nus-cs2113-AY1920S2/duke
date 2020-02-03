import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        Scanner scan = new Scanner( System.in );
        Task[] tasks = new Task[101];
        int count =0;

        while(true) {
            String userData = scan.nextLine();
            if (userData.toLowerCase().equals("bye")) {
                break;
            } else if (userData.toLowerCase().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Task.getListCount(); i += 1) {
                    //System.out.println(i + 1 + ". " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                    System.out.println(i + 1 + ". " + tasks[i].toString());
                }
            } else if (userData.toLowerCase().startsWith("done")) {
                String[] newData = userData.split(" ");
                int index = Integer.parseInt(newData[1]);
                System.out.println("Nice! I've marked this task as done: ");
                tasks[index - 1].updateIsDone();
                System.out.println(tasks[index-1].toString());
            } else if (userData.toLowerCase().startsWith("deadline")) {
                String[] removeDeadline = userData.split(" ", 2);
                String[] newData = removeDeadline[1].split("/by");
                if(newData.length == 1){
                    System.out.println("Invalid command");
                }else {
                    tasks[Task.getListCount()] = new Deadline(newData[0], newData[1]);
                    System.out.println("Got it. I've added this task:" + System.lineSeparator() + tasks[Task.getListCount() - 1].toString() + System.lineSeparator() + "Now you have " + Task.getListCount() + " task(s) in the list.");
                }
            } else if (userData.toLowerCase().startsWith("event")) {
                String[] removeEvent = userData.split(" ", 2);
                String[] newData = removeEvent[1].split("/at");
                if(newData.length == 1){
                    System.out.println("Invalid command");
                }else {
                    tasks[Task.getListCount()] = new Event(newData[0], newData[1]);
                    System.out.println("Got it. I've added this task:" + System.lineSeparator() + tasks[Task.getListCount() - 1].toString() + System.lineSeparator() + "Now you have " + Task.getListCount() + " task(s) in the list.");
                }
            } else if (userData.toLowerCase().startsWith("todo")) {
                String[] removeTodo = userData.split(" ", 2);
                tasks[Task.getListCount()] = new Todo(removeTodo[1]);
                System.out.println("Got it. I've added this task:" + System.lineSeparator() + tasks[Task.getListCount() - 1].toString() + System.lineSeparator() + "Now you have " + Task.getListCount() + " task(s) in the list.");
            }else{
                System.out.println("Invalid command ");
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}