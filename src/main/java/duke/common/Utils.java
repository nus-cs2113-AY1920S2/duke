package duke.common;

public class Utils {
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
        String CLEAR = "clear";
        String FIND = "find";
        if (type.equals(DONE) || type.equals(TODO) || type.equals(DEADLINE) || type.equals(FIND)
                || type.equals(EVENT) || type.equals(LISTE) || type.equals(DELETE) || type.equals(BYE) || type.equals(CLEAR)){
            return true;
        }
        return false;
    }
}
