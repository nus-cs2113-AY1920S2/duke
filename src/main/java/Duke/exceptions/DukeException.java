package duke.exceptions;

public class DukeException extends Exception {
    String errorDescriptions;

    /**
     * Exception for general errors missing descriptions
     *
     * @param errorDescriptions String containing the description of error
     */
    public DukeException(String errorDescriptions) {
        this.errorDescriptions = errorDescriptions;
    }

    /**
     * Print the error description
     */
    public void printDescr() {
        System.out.println(errorDescriptions);
    }
}
