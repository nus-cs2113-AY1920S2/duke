package alie.exceptions;

public class InvalidCmdException extends Exception {
    //exceptions specific to ALIE
    public InvalidCmdException (String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String toString() {
        return "INVALID CMD ERROR: " + super.getMessage();
    }
}
