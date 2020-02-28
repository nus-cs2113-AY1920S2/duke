package Duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Duke {
    private static List<Task> tasksList = new ArrayList<>(100);
    private static final String LIST_COMMAND = "list";
    private static final String BYE_COMMAND = "bye";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String TODO_ERROR_MESSAGE = "Oops! The description of todo cannot be empty.";
    private static final String DEADLINE_ERROR_MESSAGE = "Oops! The description of deadline cannot be empty.";
    private static final String EVENT_ERROR_MESSAGE = "Oops! The description of event cannot be empty.";
    private static final String INVALID_COMMAND_MESSAGE = " Oops! Im sorry, but I don't know what that means :(";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke
     *
     * @param filePath path of the file where tasks will be stored.
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Executes Duke.
     */
    public void run() {
        ui.greetUser();
        Scanner scanner =  new Scanner(System.in);
        boolean isExit = false ;
        while(!isExit){
            try{
                String input = scanner.nextLine().trim();
                String[] parsedInput = Parser.parseInput(input);
                int inputLength = parsedInput.length;
                List<String> parsedList = Arrays.asList(parsedInput);
                String command = parsedInput[0];
                List<String> arguments = parsedList.subList(1, parsedList.size());

                switch(command){
                case BYE_COMMAND:
                    storage.writeNewData(tasks);
                    ui.exitProgram();
                    isExit = true;
                    break;

                case LIST_COMMAND:
                    ui.list(tasks);
                    break;

                case DONE_COMMAND:
                    if(inputLength == 2 && Parser.isNumeric(parsedInput[1])){
                        int taskNumber = Integer.parseInt(parsedInput[1]);
                        try{
                            tasks.markAsDone(taskNumber - 1);
                            ui.showDoneMessage(tasks.getTasks().get(taskNumber-1).toString());
                        } catch (IndexOutOfBoundsException e) {
                            ui.showTaskErrorMessage();
                        }
                    }
                    else{
                        throw new DukeException(INVALID_COMMAND_MESSAGE);
                    }
                    break;

                case DELETE_COMMAND:
                    if(inputLength == 2 && Parser.isNumeric(parsedInput[1])){
                        int taskNumber = Integer.parseInt(parsedInput[1]);
                        try{
                            tasks.delete(taskNumber - 1);
                            ui.showTasksLeft(tasks);
                        } catch (IndexOutOfBoundsException e) {
                            ui.showTaskErrorMessage();
                        }
                    }
                    else{
                        throw new DukeException(INVALID_COMMAND_MESSAGE);
                    }
                    break;

                case TODO_COMMAND:
                    if(inputLength ==  1){
                        throw new DukeException(TODO_ERROR_MESSAGE);
                    }
                    String todoDescription = Parser.getTodoDescription(arguments);
                    tasks.add(new Todo(todoDescription));
                    ui.showTasksLeft(tasks);
                    break;

                case DEADLINE_COMMAND:
                    if(inputLength == 1){
                        throw new DukeException(DEADLINE_ERROR_MESSAGE);
                    }
                    String deadlineDescription = Parser.getDeadlineDescription(parsedList);
                    String by = Parser.getBy(parsedList);
                    tasks.add(new Deadline(deadlineDescription, by));
                    ui.showTasksLeft(tasks);
                    break;

                case EVENT_COMMAND:
                    if(inputLength == 1){
                        throw new DukeException(EVENT_ERROR_MESSAGE);
                    }
                    String eventDescription = Parser.getEventDescription(parsedList);
                    String at = Parser.getAt(parsedList);
                    tasks.add(new Event(eventDescription, at));
                    ui.showTasksLeft(tasks);
                    break;

                default:
                    throw new DukeException(INVALID_COMMAND_MESSAGE);
                }
            } catch (DukeException de) {
                ui.printMessage(de.getMessage());
            } finally {
                ui.printBorder();
            }
        }
    }

}