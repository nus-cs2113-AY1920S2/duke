package duke;

import duke.exception.FormatErrorException;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidInputException;
import duke.exception.OutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Duke {

    private static final int timingSpecifier = 4;

    private static int savedStatus = 1;

    private static int savedType = 0;

    private static int savedDescription = 2;

    private static int savedBy = 3;

    private static final String welcomeMessage = "Hello! I'm KJ\nHow can I help you today?";

    private static final String completeMessage = "Nice! I've marked this task as done:";

    private static final String deleteMessage = "Noted. I've removed this task:";

    private static final String byeMessage = "Bye. Hope to see you again soon!";

    private static final String listMessage = "Here are the tasks in your list:";

    private static final String noTaskMessage = "There are no tasks in your list currently.";

    private static final String invalid = "Oops, I'm sorry but I don't know what that means :-(";

    private static final String incomplete = "Uh-oh, I need more information to process that request :-(";

    private static final String indexProblem = "It appears you have entered an invalid index :O";

    private static final String fileError = "Error: Could not create save file";

    private static final String writeError = "Error: Could not save data to file";

    private static final String formatProblem = "The format of your command is incorrect, please use:\n"
            + "deadline (item) /by (time)\n" + "event (item) /at (time)";

    private static String spacing = "  ";

    private static boolean shouldContinue;

    private static String command;

    private static String[] phrases;

    private static Scanner input;

    private static ArrayList<Task> instructions;

    public static void main(String[] args) {
        try {
            initDuke();
        } catch (IOException e) {
            printError(fileError);
            return;
        } catch (InvalidInputException e) {
            printError(invalid);
            return;
        }
        displayWelcome();
        while (shouldContinue) {
            readInput();
            try {
                processCommand();
            } catch (InvalidInputException e) {
                printError(invalid);
            } catch (IncompleteInputException e) {
                printError(incomplete);
            } catch (NumberFormatException e) {
                printError(indexProblem);
            } catch (FormatErrorException e) {
                printError(formatProblem);
            } catch (OutOfBoundsException e) {
                printError(indexProblem);
            } catch (IOException e) {
                printError(writeError);
                return;
            }

        }
    }

    private static void processCommand() throws InvalidInputException, IncompleteInputException, FormatErrorException,
            OutOfBoundsException, IOException {
        phrases = command.split(" ");
        switch(phrases[0]) {
        case "bye":
            sayBye();
            break;
        case "list":
            listTasks();
            break;
        case "done":
            checkCompleteInput();
            completeTask("complete");
            break;
        case "deadline":
            checkCompleteInput();
            addDeadline();
            confirmTask();
            break;
        case "event":
            checkCompleteInput();
            addEvent();
            confirmTask();
            break;
        case "todo":
            checkCompleteInput();
            addTodo();
            confirmTask();
            break;
        case "delete":
            checkCompleteInput();
            completeTask("delete");
            break;
        default:
            throw new InvalidInputException();
        }
    }

    private static void printError(String s) {
        System.out.println(s);
    }

    private static void checkCompleteInput() throws IncompleteInputException {
        if (phrases.length == 1) {
            throw new IncompleteInputException();
        }
    }

    private static void addTodo() throws IOException {
        String description = command.substring(phrases[0].length()+1);
        Todo newTodo = new Todo(description);
        instructions.add(newTodo);
        FileWriter textAdder = new FileWriter("duke.txt",true);
        textAdder.write("T|" + "Not complete" + "|" + newTodo.getDescription() + "\n");
        textAdder.close();
    }

    private static void addEvent() throws FormatErrorException, IOException {
        int index = command.indexOf("/at");
        if (index == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1, index - 1);
        String duration = command.substring(index + timingSpecifier);
        Event newEvent = new Event(description,duration);
        instructions.add(newEvent);
        FileWriter textAdder = new FileWriter("duke.txt",true);
        textAdder.write("E|" + "Not complete" + "|" + newEvent.getDescription() + "|" + newEvent.getDuration() + "\n");
        textAdder.close();
    }

    private static void addDeadline() throws FormatErrorException, IOException {
        int index = command.indexOf("/by");
        if (index == -1) {
            throw new FormatErrorException();
        }
        String description = command.substring(phrases[0].length()+1, index - 1);
        String by = command.substring(index + timingSpecifier);
        Deadline newDeadline = new Deadline(description,by);
        instructions.add(newDeadline);
        FileWriter textAdder = new FileWriter("duke.txt",true);
        textAdder.write("D|" + "Not complete" + "|" + newDeadline.getDescription() + "|" + newDeadline.getBy() + "\n");
        textAdder.close();
    }

    private static void completeTask(String type) throws OutOfBoundsException, IOException {
        int index = Integer.parseInt(phrases[1]);
        if (index > instructions.size()) {
            throw new OutOfBoundsException();
        }
        Task completedTask = instructions.get(index-1);
        if (type.equals("complete")) {
            completedTask.markAsDone();
            System.out.println(completeMessage);
            System.out.println(spacing + completedTask);
        } else {
            System.out.println(deleteMessage);
            System.out.println(spacing + completedTask);
            System.out.println("Now you have " + (instructions.size()-1) + " tasks in the list.");
            instructions.remove(completedTask);
        }
        FileWriter textAdder = new FileWriter("duke.txt");
        for (int i = 0; i < instructions.size(); i += 1) {
            Task newVersion = instructions.get(i);
            if (newVersion instanceof Todo) {
                textAdder.write("T|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "\n");
            } else if (newVersion instanceof Event) {
                textAdder.write("E|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "|"
                        + ((Event) newVersion).getDuration() + "\n");
            } else {
                textAdder.write("D|" + newVersion.getStatus() + "|" + newVersion.getDescription() + "|"
                        + ((Deadline) newVersion).getBy() + "\n");
            }
        }
        textAdder.close();
    }

    private static void listTasks() {
        if (instructions.size() == 0) {
            System.out.println(noTaskMessage);
        } else {
            System.out.println(listMessage);
            for (int i = 0; i < instructions.size(); i++) {
                System.out.println((i+1) + "." + instructions.get(i));
            }
        }
    }

    private static void sayBye() {
        System.out.println(byeMessage);
        shouldContinue = false;
    }

    private static void readInput() {
        command = input.nextLine();
    }

    private static void initDuke() throws InvalidInputException,IOException {
        instructions = new ArrayList<>();
        shouldContinue = true;
        input = new Scanner(System.in);
        File savedData = new File("duke.txt");
        if (!savedData.exists()) {
            savedData.createNewFile();
        }
        Scanner loader = new Scanner(savedData);
        while (loader.hasNext()) {
            command = loader.nextLine();
            phrases = command.split("\\|");
            switch(phrases[savedType]) {
            case "T":
                Todo newTodo = new Todo(phrases[savedDescription]);
                if (phrases[savedStatus].equals("Complete")) {
                    newTodo.markAsDone();
                }
                instructions.add(newTodo);
                break;
            case "D":
                Deadline newDeadline = new Deadline(phrases[savedDescription],phrases[savedBy]);
                if (phrases[savedStatus].equals("Complete")) {
                    newDeadline.markAsDone();
                }
                instructions.add(newDeadline);
                break;
            case "E":
                Event newEvent = new Event(phrases[savedDescription],phrases[savedBy]);
                if (phrases[savedStatus].equals("Complete")) {
                    newEvent.markAsDone();
                }
                instructions.add(newEvent);
                break;
            default:
                throw new InvalidInputException();
            }
        }
    }

    private static void confirmTask() {
        System.out.println("Got it. I've added this task:\n  " + instructions.get(instructions.size()-1) + "\n"
            + "Now you have " + instructions.size() + " tasks in the list.");
    }

    private static void displayWelcome() {
        System.out.println(welcomeMessage);
    }
}
