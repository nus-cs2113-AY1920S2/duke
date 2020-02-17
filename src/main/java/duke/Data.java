package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    private static String[] availableTasks = {"todo", "deadline", "event"};
    private static ArrayList<Todo> todos;

    public Data() throws FileNotFoundException {
        this.todos = new ArrayList<Todo>();
        File f = new File("lib/data.txt");


        // Adapted from https://nus-cs2113-ay1920s2.github.io/website/schedule/week6/topics.html
        Scanner s = new Scanner(f);
        int i = 0;
        while (s.hasNext()) {
            String line = s.nextLine();
            String taskType = line.substring(0, line.indexOf(" "));
            String description;
            switch (taskType) {
                case "todo":
                    description = line.substring(line.indexOf(" ")+1);
                    todos.add(new Todo(description));
                    break;
                case "deadline":
                    description = line.substring(line.indexOf(" ") + 1, line.indexOf("/") - 1);
                    String by = line.substring(line.indexOf("/") + 4);
                    todos.add(new Deadline(description, by));
                    break;
                case "event":
                    description = line.substring(line.indexOf(" ")+1, line.indexOf("/") - 1);
                    String at = line.substring(line.indexOf("/") + 4);
                    todos.add(new Event(description, at));
                    break;
                default:
                    break;
            }
            boolean isDone = Boolean.parseBoolean(s.nextLine());
            if (isDone) {
                setDone(i);
            }
            i++;
        }

    }

    public static void newTask(String cmd) throws DukeException {
        if (cmd.isEmpty()) {
            throw new DukeException("Please type something.");
        }
        if (cmd.contains("delete")) {
            System.out.println("  Noted. I've removed this task:");
            System.out.println("     " + todos.get(getSize() - 1));
            todos.remove(Integer.parseInt(cmd.substring(cmd.indexOf(" ")+1))-1);
            System.out.println("  Now you have " + getSize() + " tasks in the list.");
        } else {
            if (!cmd.contains(" ") && cmd.length() > 0) {
                if (cmd.equals(availableTasks[0]) || cmd.equals(availableTasks[1])
                        || cmd.equals(availableTasks[2])) {
                    throw new DukeException("OOPS!!! The description of a " + cmd + " cannot be empty.");
                }
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            String taskType = cmd.substring(0, cmd.indexOf(" "));
            String description;
            switch (taskType) {
                case "todo":
                    if (cmd.indexOf(" ") + 1 >= cmd.length()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty. Follow format: todo [message]");
                    }
                    description = cmd.substring(cmd.indexOf(" ") + 1);
                    todos.add(new Todo(description));
                    break;
                case "deadline":
                    if ((cmd.indexOf(" ") + 1 >= cmd.length() || cmd.contains("/by") == false) && cmd.length() > 0) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty. Follow format: dead" +
                                "line [message] /by [date]");
                    }
                    description = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf("/") - 1);
                    String by = cmd.substring(cmd.indexOf("/") + 4);
                    todos.add(new Deadline(description, by));
                    break;
                case "event":
                    if ((cmd.indexOf(" ") + 1 >= cmd.length() || cmd.contains("/at") == false) && cmd.length() > 0) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty. Follow format: event [message] /at [location]");
                    }
                    description = cmd.substring(cmd.indexOf(" ") + 1, cmd.indexOf("/") - 1);
                    String at = cmd.substring(cmd.indexOf("/") + 4);
                    todos.add(new Event(description, at));
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("  Got it. I've added this task:");
            System.out.println("     " + todos.get(getSize() - 1));
            System.out.println("  Now you have " + getSize() + " tasks in the list.");
        }

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
    public String getDescription(int i) {
        return todos.get(i).getDescription();
    }
    public void saveToFile() throws IOException {
        FileWriter fw = new FileWriter("lib/data.txt");
        for (Todo todo : todos) {
            String description = todo.getDescription();
            boolean isDone = todo.isItDone();
            char taskType = todo.getTaskType();
            String toBeWritten = null;
            String at = null;
            String by = null;
            switch (taskType) {
                case 'T':
                    toBeWritten = "todo " + description;
                    break;
                case 'E':
                    Event e = (Event) todo;
                    toBeWritten = "event " + description + " /at " + e.getAt() ;
                    break;
                case 'D':
                    Deadline d = (Deadline) todo;
                    toBeWritten = "deadline " + description + " /by " + d.getBy(); ;
                    break;
                default:
                    break;
            }
            fw.write(toBeWritten);
            fw.write("\n" + isDone + "\n");
        }
        fw.close();
    }
}