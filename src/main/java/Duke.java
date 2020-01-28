import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner myInput = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Task[] taskList;
        taskList = new Task[100];
        int numberOfActions = 0;
        String userInput = myInput.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(System.lineSeparator());
            String[] splitUserInput = userInput.split(" ");
            if (splitUserInput[0].equals("list") && numberOfActions > 0) {
                for (int i = 0; i < numberOfActions; i++) {
                    System.out.println(
                            Integer.toString(i + 1) + ". " + taskList[i].toString());
                }
            } else if (splitUserInput[0].equals("list") && numberOfActions == 0) {
                System.out.println("Nothing yet");
            } else if (splitUserInput[0].equals("done")) {
                int actionListNumber = Integer.parseInt(splitUserInput[1]);
                if ((actionListNumber - 1) > numberOfActions || actionListNumber == 0) {
                    System.out.println("Out of range");
                } else {
                    taskList[actionListNumber - 1].markAsDone();
                    System.out.println(
                            "Nice! I marked this as done: " + taskList[actionListNumber - 1].toString());
                }
            } else {
                Task newTask;
                switch (splitUserInput[0]) {
                case "todo":
                    newTask = new Todo(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
                    break;
                case "deadline":
                    newTask = new Deadline(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
                    break;
                case "event":
                    newTask = new Event(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
                    break;
                default:
                    newTask = new Task(userInput);
                    break;
                }
                taskList[numberOfActions] = newTask;
                numberOfActions = numberOfActions + 1;
                System.out.println("Got it. I've added this task: " + newTask.toString());
            }

            System.out.println("What else do you want to do?");
            userInput = myInput.nextLine();
        }
        System.out.println(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
        myInput.close();
    }

    public static String[] splitTaskDescription(String input) {
        String[] returnSplit = new String[2];
        if (!input.contains("/")) {
            returnSplit[0] = input.split(" ", 2)[1];
            returnSplit[1] = "";
            return returnSplit;
        }
        String[] obtainedSplit = input.split("/");
        String[] obtainedDescription = obtainedSplit[0].split(" ", 2);
        returnSplit[0] = obtainedDescription[1];
        returnSplit[1] = obtainedSplit[1];
        return returnSplit;
    }


}
