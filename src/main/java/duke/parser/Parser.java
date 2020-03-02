package duke.parser;

import java.util.Scanner;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.Exception.DukeException;

/**
 * Represents a Scanner object that is created from the user's input/response with
 * the program. This class handles the user response and tells the program what commands to execute.
 * A parser object corresponds to user's response e.g, 'todo return book'
 */
public class Parser {

    public static Scanner userInput;
    public static Ui ui;
    public static TaskList taskList;

    /**
     * Constructor for the Parser class
     * @param taskList a pointer/reference to the tasklist
     */
    public Parser(TaskList taskList){
        userInput = new Scanner(System.in);
        this.ui = new Ui(taskList);
        this.taskList = taskList;
    }


    /**
     * Returns a trimmed String representation of the response from the user
     * @return trimmed String user response.
     */
    public String getUserResponse(){
        String userResponse = userInput.nextLine();
        return userResponse.trim();
    }

    /**
     * Function that parses through the user response and tells the program which
     * command it should execute.
     * @param userResponse trimmed String of user response
     */
    public void doUserCommand(String userResponse){
        String action = userResponse.split(" ")[0];
        String restOfUserInput = userResponse.replace(action, "").trim();
        ui.printLineSeparator();
        try {
            if(action.equals("bye")){
                ui.goodbye();
            } else if(action.equals("list")){
                taskList.listTasks();
            } else if(action.equals("done")){
                try {
                    taskList.done(restOfUserInput);
                } catch (DukeException e) {
                    ui.invalidDone();
                }
            } else if(action.equals("todo")){
                try {
                    taskList.todo(restOfUserInput);
                } catch (DukeException e) {
                    ui.invalidTodo();
                }
            } else if(action.equals("deadline")){
                try {
                    taskList.deadline(restOfUserInput);
                } catch (DukeException e) {
                    ui.invalidDeadline();
                }
            } else if(action.equals("event")){
                try {
                    taskList.event(restOfUserInput);
                } catch (DukeException e) {
                    ui.invalidEvent();
                }
            } else if(action.equals("delete")){
                try {
                    taskList.delete(restOfUserInput);
                } catch (DukeException e) {
                    ui.invalidDelete();
                }
            } else if(action.equals("find")){
                try {
                    taskList.find(restOfUserInput);
                } catch (DukeException e) {
                    ui.invalidFind();
                }
            } else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            ui.invalidCommand();
        }
        ui.printLineSeparator();
    }

}

