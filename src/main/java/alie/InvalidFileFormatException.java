package alie;

public class InvalidFileFormatException extends Exception {
    //exceptions specific to ALIE
    public InvalidFileFormatException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String toString() {
        return "INVALID FILE FORMAT ERROR: " + super.getMessage();
    }
}
