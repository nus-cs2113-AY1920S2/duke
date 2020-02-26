package duke.task;

/* a todo class that can be instantiated to todo object */
public class Todo extends Task {

    public Todo(String command, boolean status) {super(command, status);};

    /**
     * generate description of a todo task that need to be stored in the file
     * @return a string that will be stored in the local file
     */
    public String textToFile() {
        String text = "T | 0 | ";
        if(isDone) text = "T | 1 | ";
        text += name + System.lineSeparator();
        return text;
    }

    /**
     * generate a string that display all attributes of a todo task
     * @return a string that describes the task
     */
    public String print(){
        String str = "[T]";
        if(isDone) {
            str += "[\u2713]";
        } else {
            str += "[\u274c]";
        }
        return str = str + " " + name;
    }

}
