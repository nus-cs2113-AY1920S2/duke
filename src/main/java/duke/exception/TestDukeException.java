package duke.exception;

import duke.exception.DukeException;

public class TestDukeException {
    private String message;
    private boolean isTodo ;
    private boolean isDeadline;
    private boolean isEvent;
    private boolean isWrongType;
    private String[] words;
    private String taskType;

    public TestDukeException(String message){
        this.message = message;
        words = this.message.split(" ");
        taskType = words[0];
        isTodo = taskType.equalsIgnoreCase("todo");
        isDeadline = taskType.equalsIgnoreCase("deadline");
        isEvent = taskType.equalsIgnoreCase("event");
        isWrongType = !(isTodo || isDeadline || isEvent);
    }

    public void throwToDoException() throws DukeException {
        String[] words = message.split(" ");
        boolean isWrongLength = words.length <= 1;
        if(isWrongLength && isTodo){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void throwDeadlineException() throws DukeException{
        String[] words = message.split(" ");
        boolean isWrongLength = words.length <= 1;
        if(isWrongLength && isDeadline){
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public void throwEventException() throws DukeException{
        String[] words = message.split(" ");
        boolean isWrongLength = words.length <= 1;
        if(isWrongLength && isEvent){
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    public void throwTaskTypeException() throws DukeException{
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
