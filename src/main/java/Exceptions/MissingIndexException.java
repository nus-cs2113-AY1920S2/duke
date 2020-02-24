package Exceptions;

public class MissingIndexException extends DukeExceptions {
    public MissingIndexException() {
        super();
    }

    @Override
    public String toString() {
        return " [Error: Missing Index]";
    }
}
