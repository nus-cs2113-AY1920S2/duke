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
        Task[] actionList;
        actionList = new Task[100];
        int numberOfActions = 0;
        String userInput = myInput.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(System.lineSeparator());
            String[] splitUserInput = userInput.split(" ");
            if (userInput.equals("list") && numberOfActions > 0) {
                for (int i = 0; i < numberOfActions; i++) {
                    System.out.println(
                            Integer.toString(i + 1) + ". [" + actionList[i].getStatusIcon() + "] " +
                                    actionList[i].description);
                }
            } else if (userInput.equals("list") && numberOfActions == 0) {
                System.out.println("Nothing yet");
            } else if (splitUserInput[0].equals("done")) {
                int actionListNumber = Integer.parseInt(splitUserInput[1]);
                if ((actionListNumber - 1) > numberOfActions || actionListNumber == 0) {
                    System.out.println("Out of range");
                } else {
                    actionList[actionListNumber - 1].markAsDone();
                    System.out.println(
                            "Nice! I marked this as done: [" + actionList[actionListNumber - 1].getStatusIcon() + "] " +
                                    actionList[actionListNumber - 1].description);
                }
            } else {
                Task newTask = new Task(userInput);
                actionList[numberOfActions] = newTask;
                numberOfActions = numberOfActions + 1;
                System.out.println("added: " + userInput);
            }

            System.out.println("What else do you want to do?");
            userInput = myInput.nextLine();
        }
        System.out.println(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
        myInput.close();
    }


}
