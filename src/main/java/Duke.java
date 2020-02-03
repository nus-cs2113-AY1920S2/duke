import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    private static Scanner sc = new Scanner(System.in);

    private static final String BOT_NAME = "E.D.I.T.H.";
    private static final String USERNAME = "USER";
    private static final String MESSAGE_WELCOME = "\nHello! I'm " + BOT_NAME + "\nWhat can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!\n";
    private static final String LINE_DIVIDER = "\n____________________________________________________________";
    private static final String MESSAGE_INVALID_COMMAND = "Invalid Command. Please try again\n";
    private static final String COMMAND_ADD_WORD = "ADD";
    private static final String COMMAND_ADD_DESC = "Adds an item to the todo list";
    private static final String COMMAND_HELP_WORD = "HELP";
    private static final String COMMAND_HELP_DESC = "Here's the help list\n1. Add\n2. List\n3. Mark";
    private static final String COMMAND_LIST_WORD =  "LIST";
    private static final String COMMAND_MARK_WORD = "MARK";
    private static final String COMMAND_DEADLINE_WORD = "DEADLINE";
    private static final String COMMAND_EXIT_WORD = "EXIT";
    private static final String COMMAND_EVENT_WORD = "EVENT";
    private static final String COMMAND_DONE_WORD = "DONE";
    private static final String COMMAND_TODO_WORD = "TODO";



    public static final void displayWelcomeMessage(){ System.out.println( "\n" + LINE_DIVIDER + MESSAGE_WELCOME + LINE_DIVIDER);}
    public static final void displayInvalidCommand(){ System.out.println(MESSAGE_INVALID_COMMAND);}
    public static final void displayExitMessage(){ System.out.println(MESSAGE_EXIT);}
    public static final void displayHelpMenu(){ System.out.print(COMMAND_HELP_DESC);}
    public static final void displayLineDivider(){ System.out.println(LINE_DIVIDER);}
    public static final void displayNumberOfTasks(int Tasks){ System.out.print("Now you have " + Tasks + " tasks in the list.");}

    private static void echoUserCommand(String userCommand) {
        System.out.println("[Command entered: " + userCommand + "]");
    }

    private static void exitProgram(){
        displayExitMessage();
    }

    public static void main(String[] args) {

        Task task[] = new Task[100];
        int index = 0;

        displayWelcomeMessage();
        String userCommand;
        boolean run = true;
        while(run) {
            userCommand = sc.nextLine();
            displayLineDivider();
            echoUserCommand(userCommand);
            switch (userCommand) {

                case COMMAND_TODO_WORD: {
                    String input = sc.nextLine();
                    Task item = new Task(input, "T");
                    task[index] = item;
                    index++;
                    displayNumberOfTasks(index);
                    break;
                }

                case COMMAND_EVENT_WORD: {
                    String input = sc.nextLine();
                    Task item = new Task(input, "E");
                    task[index] = item;
                    input = sc.nextLine();
                    task[index].setBy(input);
                    index++;
                    displayNumberOfTasks(index);
                    break;
                }

                case COMMAND_DEADLINE_WORD: {
                    String input = sc.nextLine();
                    Task item = new Task(input, "D");
                    task[index] = item;
                    input = sc.nextLine();
                    task[index].setBy(input);
                    index++;
                    displayNumberOfTasks(index);
                    break;
                }

                case COMMAND_DONE_WORD: {
                    String input = sc.nextLine();
                    int markIndex = Integer.parseInt(input);
                    System.out.println("Done by? WRITE IN DD/MM/YY");
                    String input1 = sc.nextLine();
                    task[markIndex-1].setBy(input1);
                    break;
                }

                case COMMAND_MARK_WORD: {
                    String input = sc.nextLine();
                    int markIndex = Integer.parseInt(input);
                    task[markIndex-1].markDone();
                    break;
                }

                case COMMAND_LIST_WORD: {
                    if (index == 0){
                        System.out.print("Your list is empty! Try adding to the list first :)");
                        break;
                    }

                    System.out.print("Here are the tasks in your list:");
                    for (int i = 0 ; i < index; i++){
                        System.out.print("\n" + (i+1) + ". " + task[i].getStatusIcon()  + "[" + task[i].getType() + "] " + task[i].getDescription()  + task[i].getBy());
                    }
                    break;
                }

                case COMMAND_EXIT_WORD: {
                    run = false;
                    exitProgram();
                    break;
                }
                default: {
                    System.out.println("INVALID");
                    break;
                }
            }
            displayLineDivider();
        }
    }
}