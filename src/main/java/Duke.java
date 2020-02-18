import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________\n"
                + "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n");

        String filePathString = "../../../docs/data/duke.txt";
        try {
            File f = new File(filePathString);
            if(!f.exists()){
                f.createNewFile();
            }
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                System.out.println(line);
                String[] words = line.split(",");
                System.out.println(Arrays.toString(words));
                Task t = new Task(line);
                switch (words[0]){
                    case "T":
                        t = new Todo(words[2]);
                        break;
                    case "D":
                        t = new Deadline(words[2], words[3]);
                        break;
                    case "E":
                        t = new Event(words[2], words[3]);
                        break;
                }
                taskList.add(t);
                if(words[1].equals("\u2713")){
                    t.markAsDone();
                }
            }
            s.close();
        } catch (IOException e) {
            printIOExceptionMessage();
        }

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")){
            String[] words = line.split(" ",2); // split the first word from the rest of the sentence using space as the delimiter
            try {
                handleCommand(line, words);
                writeToFile(filePathString);
            } catch (EmptyTaskListException e) {
                printEmptyTaskListExceptionMessage();
            } catch (EmptyDoneException e) {
                printEmptyDoneExceptionMessage();
            } catch (EmptyDeleteException e) {
                printEmptyDeleteExceptionMessage();
            } catch (UnknownWordException e) {
                printUnknownWordExceptionMessage();
            } catch (EmptyTodoException e) {
                printEmptyTodoExceptionMessage();
            } catch (EmptyDeadlineException e) {
                printEmptyDeadlineExceptionMessage();
            } catch (EmptyEventException e) {
                printEmptyEventExceptionMessage();
            } catch (IndexOutOfBoundsException e) {
                printIndexOutOfBoundsExceptionMessage();
            } catch (IOException e){
                printIOExceptionMessage();
            }


            line = in.nextLine();
        }
        printGoodbyeMessage();
    }

    private static void handleCommand(String line, String[] words) throws EmptyTaskListException, EmptyDoneException, EmptyDeleteException, UnknownWordException, EmptyTodoException, EmptyDeadlineException, EmptyEventException {
        if (line.equals("list")) {
            if(taskList.isEmpty()){
                throw new EmptyTaskListException();
            }
            printListMessage();
        } else if (words[0].equals("done")) {
            if(taskList.isEmpty()){
                throw new EmptyTaskListException();
            }
            if (words.length < 2){
                throw new EmptyDoneException();
            }
            int taskNum = Integer.parseInt(words[1]);
            Task t=taskList.get(taskNum-1);
            t.markAsDone();
            printDoneMessage(t);
        } else if (words[0].equals("delete")) {
            if(taskList.isEmpty()){
                throw new EmptyTaskListException();
            }
            if (words.length < 2){
                throw new EmptyDeleteException();
            }
            int taskNum = Integer.parseInt(words[1]);
            Task t = taskList.get(taskNum-1);
            taskList.remove(t);
            printDeleteMessage(t);
        } else if (words[0].equals("todo") || words[0].equals("deadline") || words[0].equals("event")){
            String taskType = words[0];
            Task t = new Task(line);
            switch (taskType){
                case "todo":
                    if (words.length < 2){
                        throw new EmptyTodoException();
                    }
                    t = new Todo(words[1]);
                    break;
                case "deadline":
                    String[] deadlineWords = words[1].split(" /by ", 2); // split the deadline task from the by string using /by as the delimiter
                    if (deadlineWords.length < 2){
                        throw new EmptyDeadlineException();
                    }
                    t = new Deadline(deadlineWords[0], deadlineWords[1]);
                    break;
                case "event":
                    String[] eventWords = words[1].split(" /at ", 2); // split the event task from the by string using /at as the delimiter
                    if (eventWords.length < 2){
                        throw new EmptyEventException();
                    }
                    t = new Event(eventWords[0], eventWords[1]);
                    break;
            }
            taskList.add(t);
            printAddTaskMessage(t);
        } else {
            throw new UnknownWordException();
        }
    }

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.close();
        for(Task t : taskList){
            fw = new FileWriter(filePath, true);
            fw.write(t.toFileString() + System.lineSeparator());
            fw.close();
        }
    }

    private static void printListMessage() {
        System.out.println("\t____________________________________________________________\n"
                +"\t Here are the tasks in your list:");
        int i=0;
        for (Task t : taskList) {
            System.out.println("\t " + (i+1) + ". " + t.toString());
            i++;
        }
        System.out.println("\t____________________________________________________________\n");
    }


    private static void printDoneMessage(Task t) {
        System.out.println("\t____________________________________________________________\n"
                +"\t Nice! I've marked this task as done:\n"
                +"\t\t" + t.toString() + "\n"
                +"\t____________________________________________________________\n");
    }

    private static void printDeleteMessage(Task t) {
        System.out.println("\t____________________________________________________________\n"
                +"\t Noted. I've removed this task:\n"
                +"\t\t" + t.toString() + "\n"
                + "\t Now you have " + taskList.size() +" tasks in the list.\n"
                +"\t____________________________________________________________\n");
    }

    private static void printAddTaskMessage(Task t) {
        System.out.println("\t____________________________________________________________\n"
                + "\t Got it. I've added this task:\n"
                + "\t   " + t.toString() + "\n"
                + "\t Now you have " + taskList.size() +" tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    private static void printGoodbyeMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t Bye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyTodoExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of a todo must contain a task name in this format:\n"
                + "\t todo <task name>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyDoneExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t☹ OOPS!!! The description of a done must contain a task number in this format:\n"
                + "\t done <task number>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyDeleteExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t☹ OOPS!!! The description of a delete must contain a task number in this format:\n"
                + "\t delete <task number>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyDeadlineExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of a deadline must contain a task name and a date in this format:\n"
                + "\t deadline <task name> /by <date>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyEventExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The description of an event must contain a task name and a location in this format:\n"
                + "\t event <task name> /at <location>\n"
                + "\t____________________________________________________________\n");
    }

    private static void printUnknownWordExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "\t You can try the following commands instead:\n"
                + "\t\t list\n"
                + "\t\t todo <task name>\n"
                + "\t\t deadline <task name> /by <date>\n"
                + "\t\t event <task name> /at <location>\n"
                + "\t\t done <task number>\n"
                + "\t\t delete <task number>\n"
                + "\t\t bye\n"
                + "\t____________________________________________________________\n");
    }

    private static void printIndexOutOfBoundsExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! The index you have entered is out of bounds.\n"
                + "\t You can try index in the range of 1 to " + taskList.size() + " instead.\n"
                + "\t____________________________________________________________\n");
    }

    private static void printEmptyTaskListExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! Your task list is empty!\n"
                + "\t You can try entering a task using the following commands first:\n"
                + "\t\t done <task number>\n"
                + "\t\t deadline <task name> /by <date>\n"
                + "\t\t event <task name> /at <location>\n");
    }

    private static void printIOExceptionMessage() {
        System.out.println("\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! File is not found!\n"
                + "\t____________________________________________________________\n");
    }
}
