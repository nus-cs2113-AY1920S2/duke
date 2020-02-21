package duke;

import java.io.IOException;


public class Parser {
    // This class parses the user command and executes them

    public static void parseCommand(String command, TaskList tasks) {
        /**
         * This method processes the command by the user and executes them
         *
         * @param command The command that the user specified
         * @param tasks list of tasks that the command will be executed upon
         */

        if (command.equals("list")){
            listCommand(tasks);
        } else if (command.startsWith("done")){
            doneCommand(command,tasks);
        } else if (command.equals("bye")){
            try {
                Storage.writeToFile("./duke.txt",tasks);
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
            System.out.println("Bye. Hope to see you again soon! :)");
            System.exit(0);
        } else {
            String[] splitString = command.split(" ", 2);
            if (splitString[0].equals("deadline")){
                deadlineCommand(splitString,tasks);
            } else if (splitString[0].equals("todo")){
                todoCommand(splitString,tasks);
            } else if (splitString[0].equals("event")) {
                eventCommand(splitString,tasks);
            } else if (splitString[0].equals("delete")){
                deleteCommand(splitString,tasks);
            } else if (splitString[0].equals("find")) {
                findCommand(splitString,tasks);
            } else {
                    errorCommand();
                }
            }
        }

    private static void listCommand(TaskList tasks) {
        /**
         * This method prints the list of tasks
         *
         * @param tasks list of tasks to be printed
         */

        System.out.println("Here are the tasks on your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.printf("%d. %s\n",i +1,tasks.getIndex(i).toString());
        }
    }

    private static void doneCommand(String command,TaskList tasks){
        /**
         * This method executes the done command and changes the status of a valid task to done
         *
         * @param command string that contains the index which has been 'done'
         * @param tasks list of tasks which the done command will be executed upon
         */

        String temp = command.replaceAll("\\D+","");
        int FinishedNumber = Integer.parseInt(temp);
        if (FinishedNumber > tasks.getSize()) {
            System.out.println("Incorrect index");
        } else {
            tasks.getIndex(FinishedNumber-1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("  [%s] %s\n",tasks.getIndex(FinishedNumber-1).getStatusIcon(),tasks.getIndex(FinishedNumber-1).getDescription());
        }

    }
    private static void findCommand(String[] splitString, TaskList tasks) {
        /**
         * This method executes the find command
         *
         * @param splitString array that contains the command to be executed
         * @param tasks list of tasks that the command will be executed upon
         */

        String searchTarget = splitString[1];
        boolean noMatch = false;
        System.out.println("Here are the matching tasks in your list:");
        int counter = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getIndex(i).getDescription().contains(searchTarget)){
                System.out.printf("%d. %s\n",counter,tasks.getIndex(i).toString());
                noMatch = true;
                counter = counter + 1;
            }
        }
        if (noMatch == false) {
            System.out.println("There are no matches :(");
        }
    }
    private static  void deadlineCommand(String[] splitString,TaskList tasks) {
        /**
         * This method executes the deadline command
         *
         * @param splitString array that contains the command to be executed
         * @param tasks list of tasks that the new deadline will be added into
         */

        if (splitString.length == 1) {
            System.out.println("The description of deadline cannot be empty");

        } else{
            String[] splitString2 = splitString[1].split("/by", 2);
            if (splitString2.length == 1) {
                System.out.println("duke.Deadline requires to be separated by a '/by' statement");

            } else{
                gotItMessage();
                tasks.addTask(new Deadline(splitString2[0],splitString2[1]));
                System.out.printf("  %s\n",tasks.getIndex(tasks.getSize()-1).toString());
            }

        }

    }
    private static void todoCommand(String[] splitString, TaskList tasks) {
        /**
         * This method executes the todo command and adds a valid todo to the tasklist
         *
         * @param splitString array that contains the command to be excecuted
         * @param tasks list of tasks that the new todo task will be added into
         */
        if (splitString.length == 1) {
            System.out.println("OOPS!!! The description of todo cannot be empty");

        } else{
            gotItMessage();
            tasks.addTask(new Todo(splitString[1]));
            System.out.printf("  %s\n",tasks.getIndex(tasks.getSize()-1).toString());
        }

    }
    private static void eventCommand(String[] splitString, TaskList tasks) {
        /**
         * This method executes the event command and adds a valid event to the tasklist
         *
         * @param splitString array that contains the command to be executed
         * @param tasks list of tasks that that the new event will be added to
         */

        if (splitString.length == 1) {
            System.out.println("OOPS!!! The description of event cannot be empty");

        } else {
            String[] splitString2 = splitString[1].split("/at", 2);
            if (splitString2.length == 1) {
                System.out.println("duke.Event is required to be separated by a '/at' statement");

            } else {
                gotItMessage();
                tasks.addTask(new Event(splitString2[0], splitString2[1]));
                System.out.printf("  %s\n",tasks.getIndex(tasks.getSize()-1).toString());
            }

        }
    }
    private static void deleteCommand(String[] splitString, TaskList tasks) {
       /**
        * This function processes the 'delete' command
        * and removes a valid index from the task list
        *
        * @param splitString array that contains the command that is to be executed
        * @param tasks list of tasks that the command will be executed upon
        */

        if (splitString.length == 1) {
            System.out.println("OOPS!!! The description of event cannot be empty");

        } else {
            if (splitString[1].matches("\\d+")) {
                if (Integer.parseInt(splitString[1]) > tasks.getSize()) {
                    System.out.println("Input valid index");
                } else {
                    System.out.println("Noted: I've removed this task:");
                    System.out.printf("  %s\n", tasks.getIndex(Integer.parseInt(splitString[1]) -1).toString());
                    tasks.removeTask(Integer.parseInt(splitString[1]));
                }

            } else {
                System.out.println("Input an integer for the index you wish to delete.");
            }
        }
    }
    private static void errorCommand(){
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    private static void gotItMessage() {
        System.out.println("Got it. I've added this task:");
    }
}
