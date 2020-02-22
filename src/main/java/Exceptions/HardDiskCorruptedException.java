package Exceptions;

public class HardDiskCorruptedException extends DukeException {
    private String errorMessage;

    public HardDiskCorruptedException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return String.format("Hard Disk Data is corrupted! %s", errorMessage);
    }
}
