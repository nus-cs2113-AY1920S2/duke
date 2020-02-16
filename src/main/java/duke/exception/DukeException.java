package duke.exception;

public class DukeException extends IllegalArgumentException {
    
    public DukeException(String message) {
        super(message);
    }
    
    public String printOutput(String content) {       
        String output = "";
        output += ("_______________________"
                + "________________________"
                + "________________________");

        output += ("\n" + "\n" + content); 
        
        output += ("\n_______________________"
                + "________________________"
                + "________________________");
        
        return output;
    }
    
    @Override
    public String toString() {
        return printOutput(this.getMessage());
    }
}
