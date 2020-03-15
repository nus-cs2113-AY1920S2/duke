package duke.exceptions;

public class MissingDescriptionException extends Exception {

    String errorDescriptions;

    /**
     * Exceptions for inputs missing descriptions
     *
     * @param errorDescriptions String containing the error message
     */
    public MissingDescriptionException(String errorDescriptions) {
        this.errorDescriptions = errorDescriptions;
    }

    /**
     * Print error message
     */
    public void printDescr() {
        System.out.println(errorDescriptions);
    }
}
