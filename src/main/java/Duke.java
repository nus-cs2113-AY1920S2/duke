import java.util.Scanner;
import java.util.ArrayList;

//todo: modularise code
public class Duke {
    public static final int MAX_COMMANDS = 100;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        boolean continueRun = true;
        String userCmd = "";
        String[] cmdArr = new String[MAX_COMMANDS];
        int numOfTasks = 0;

        ArrayList<Task> taskArrList = new ArrayList<>();

        while (continueRun){
            System.out.println("==========================");
            System.out.println("What can I do for you?");
            userCmd = sc.nextLine();

            System.out.println("You have typed: " +userCmd);

            //end program
            if (userCmd.toLowerCase().equals("bye")){
                System.out.println("Bye. Hope to see you again!");
                continueRun = false;
                break;
            }
            //List commands -->not updated
            else if (userCmd.toLowerCase().equals("list")){
                System.out.println("Current task list: ");

                if (taskArrList.size() == 0) {
                    System.out.println("List is empty!");
                }

                else {
                    for (Task t : taskArrList) {
                        System.out.println(t.getTaskID() +". [" + t.getStatusIcon() +"] " +t.getTaskName());
                    }
                }
            }
            //Mark task as done
            else if (userCmd.contains("done")){
                String[] splitCmd = userCmd.split(" ");
                int completedTask = 0;
                try {
                    completedTask = Integer.parseInt(splitCmd[1]);
                }
                catch (Exception e){
                    System.out.println("Incorrect syntax! Try again");
                    continue;
                }

                if (completedTask > taskArrList.size()){
                    System.out.println("Task not found!");
                }
                else {
                    //TODO: update to task via taskID instead of array index
                    Task temp = taskArrList.get(completedTask-1); //get index not IF
                    //todo: need to check if already completed?
                    temp.setDone();

                    System.out.println("I've marked this task as done:");
                    System.out.println("[" + temp.getStatusIcon() + "] " + temp.getTaskName());
                }
            }
            //Add commands
            else{
                numOfTasks++;
                Task newTask = new Task(numOfTasks, userCmd);
                taskArrList.add(newTask);
                System.out.println("Task added: " +userCmd);
            }




        }
    }
}
