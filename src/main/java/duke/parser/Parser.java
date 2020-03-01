package duke.parser;

import java.util.Scanner;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.Exception.DukeException;

public class Parser {

    public static Scanner userInput;
    public static Ui ui;
    public static TaskList taskList;

    public Parser(TaskList taskList){
        userInput = new Scanner(System.in);
        this.taskList = taskList;
    }

    // Function that gets the response from the user
    public String getUserResponse(){
        String userResponse = userInput.nextLine();
        return userResponse.trim();

    }

    public void doUserCommand(String userResponse){
        // INSERT LINE SEPARATOR
        String action = userResponse.split(" ")[0];
        String restOfUserInput = userResponse.replace(action, "").trim();

        try {
            if(action.equals("bye")){
                taskList.bye();
            } else if(action.equals("list")){
                taskList.listTasks();
            } else if(action.equals("done")){
                taskList.done(restOfUserInput);
            } else if(action.equals("todo")){
                taskList.todo(restOfUserInput);
            } else if(action.equals("deadline")){
                taskList.deadline(restOfUserInput);
            } else if(action.equals("event")){
                taskList.event(restOfUserInput);
            } else if(action.equals("delete")){
                taskList.delete(restOfUserInput);
            } else if(action.equals("find")){
                taskList.find(restOfUserInput);
            } else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            ui.invalidCommand();
        }
        // INSERT LINE SEPARATOR
    }


}

