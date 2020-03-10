import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * Activated when the list is requested. Generates a text file from the list automatically.
 */

public class Storage {

    /**
     * The output seen in the text file.
     */
    private String output;

    /**
     * Constructor for Storage.
     */

    public Storage() {
        this.output = "";
    }

    /**
     * Adds new text to the output.
     * @param newString the string to be added on.
     */

    public void addToList(String newString) {
        output += (newString + "\n");
    }

    /**
     * Generates the text file of the list of tasks.
     */

    public void storeList() {
        try (PrintWriter printer = new PrintWriter("out.txt")) {
            printer.println(output);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
