import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Storage {
    private String output;

    public Storage() {
        this.output = "";
    }

    public void addToList(String newString) {
        output += (newString + "\n");
    }

    public void storeList() {
        try (PrintWriter printer = new PrintWriter("out.txt")) {
            printer.println(output);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
