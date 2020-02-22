package duke;

import java.util.ArrayList;


public abstract class Commands {
    public static String finalCommand;

    public Commands(String command) {
        finalCommand = command.trim();
    }

    public Boolean isExit(String command) {
        String ogString = command.trim();
        Boolean yesExit = false;
        if (ogString.toUpperCase().equals("BYE")) {
            yesExit = true;
        }
        return yesExit;
    }

    public static String returnTaskType(String line){
        String[] words = line.split(" ");
        String taskType = words[0].trim();
        return taskType;
    }

    /**
     * Checks if the given task is valid
     * @param type Type of task
     * @return Checks if the task type is valid
     */
    public static Boolean checkIfValidTask(String type){
        String DONE = "done";
        String DEADLINE = "deadline";
        String TODO = "todo";
        String EVENT = "event";
        String LISTE = "list";
        String DELETE = "delete";
        String BYE = "bye";
        if (type.equals(DONE) || type.equals(TODO) || type.equals(DEADLINE)
                || type.equals(EVENT) || type.equals(LISTE) || type.equals(DELETE) || type.equals(BYE) ){
            return true;
        }
        return false;
    }

}
