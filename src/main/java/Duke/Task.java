package Duke;
import java.nio.charset.*;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

//    public String getStatusIcon() {
//        return (isDone ? "✓" : "✗"); //return tick or X symbols
//    }
    public String getStatusIcon(){
        if(isDone) {
//            String tickString = "✓";
//            byte[] tickByte = tickString.getBytes();
//            String tick = new String (tickByte, StandardCharsets.UTF_8);
            return "O";
        } else {
                return "X";
        }
    }

    public String getDescription(){
        return description;
    }

    public void markAsDone(Task description) {
        this.isDone = true;
    }

    public void importDone() {
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" + getDescription();
    }

}
