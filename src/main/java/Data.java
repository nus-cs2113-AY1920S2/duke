import java.util.ArrayList;

public class Data {
    private static ArrayList<Todo> todos;

    public Data(){
        this.todos = new ArrayList<Todo>();
    }

    public static void newTask(String taskType, String cmd) {
        String description;
        switch (taskType) {
        case "todo":
            description = cmd.substring(cmd.indexOf(" ")+1);
            todos.add(new Todo(description));
            break;
        case "deadline":
            description = cmd.substring(cmd.indexOf(" ")+1, cmd.indexOf("/")-1);
            String by = cmd.substring(cmd.indexOf("/")+4);
            todos.add(new Deadline(description, by));
            break;
        case "event":
            description = cmd.substring(cmd.indexOf(" ")+1, cmd.indexOf("/")-1);
            String at = cmd.substring(cmd.indexOf("/")+4);
            todos.add(new Event(description, at));
            break;
        default:
            System.out.println("Error");
            break;
        }
        System.out.println("  Got it. I've added this task:");
        System.out.println("     " + todos.get(getSize()-1));
        System.out.println("  Now you have " + getSize() + " tasks in the list.");
    }

    public static int getSize() {
        return todos.size();
    }

    public String printItem(int i) {
        return todos.get(i).toString();
    }
    public void setDone(int i) {
        todos.get(i).setDone();
    }
}
