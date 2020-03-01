package exceptions;

/**
 * Customs Exception object for failed reading of hard disk
 */
public class HardDiskCorruptedException extends DukeException {
    private String errorMessage;

    /**
     * Constructs a HardDiskCorruptedException object
     * @param errorMessage String provided by user
     */
    public HardDiskCorruptedException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a formatted string
     * @return String formatting the error message
     */
    @Override
    public String toString() {
        return String.format("Hard Disk Data is corrupted! %s", errorMessage);
    }
}