import duke.command.Command;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import duke.exception.DukeException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final Path FILENAME = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
    private static String fileDoneStatus;
    private static final String ERROR_DELETE_MESSAGE = "Sheena: I can't delete that because"
            + " you haven't" + " added task %s yet";
    private static final String ERROR_MESSAGE = "Sheena: The %s of a %s command should not be empty.\n";
    private static final String ERROR_DELETE_PARAM = "What to do: delete [TASK NUMBER]\n";
    private static final String ERROR_DELETE_EXAMPLE = "Example: delete 1";

    private static Command splitCommand(String line) {
        String word;
        String argument;
        try {
            word = line.substring(0, line.indexOf(' '));
            argument = line.substring(line.indexOf(' ') + 1);
        } catch (IndexOutOfBoundsException e) {
            word = line;
            argument = " ";
        }
        return new Command(word, argument);
    }

    private static void printTasks(ArrayList<Task> List) throws DukeException {
        int count = 1;
        if (List.size() == 0) {
            throw new DukeException();
        } else {
            System.out.println("Sheena: These are tasks that you have saved ~ :");
            for (Task t : List) {
                System.out.print(count);
                System.out.print(". ");
                System.out.println(t.toString());
                count++;
            }
        }
    }

    private static void deleteTaskCommand(ArrayList<Task> taskList, Command command) throws NumberFormatException, IndexOutOfBoundsException {
        int index;
        if (command.getArgs() == null) {
            throw new NumberFormatException();
        }
        index = Integer.parseInt(String.valueOf(command.getArgs()));
        if (index > taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task t = taskList.get(index - 1);
        taskList.remove(index - 1);
        deleteOnFile(index);
        System.out.println("-------------------------------------------");
        System.out.println("Sheena: Noted! I've removed this task:");
        System.out.println(t.toString());
        System.out.printf("Sheena: Now you have %d tasks in your list.\n", taskList.size());
        System.out.println("-------------------------------------------");

    }

    public static Task getTheTask(ArrayList<Task> List, int j)
    {
        return List.get(j);
    }


    public static void updateCommand(Task task, ArrayList<Task> List, int count)
    {
        List.set(count,task);
    }

    private static void readExistFile(ArrayList<Task> List) throws IOException {
        FileReader fr = new FileReader(String.valueOf(FILENAME)); // file from data/duke.txt
        BufferedReader br = new BufferedReader(fr);
        String lining;
        while ((lining = br.readLine()) != null) {
            String[] parse = lining.trim().split("\\s*\\|\\s*");
            if (parse.length == 3) {
                ToDos t = new ToDos(parse[2]);
                if (parse[1].equals("1")) {
                    t.markAsDone();
                }
                List.add(t);
            } else if (parse.length == 4) {
                if (parse[0].equals("D")) {
                    Deadlines d = new Deadlines(parse[2], parse[3]);
                    if (parse[1].equals("1")) {
                        d.markAsDone();
                    }
                    List.add(d);
                } else {
                    Events e = new Events(parse[2], parse[3]);
                    if (parse[1].equals("1")) {
                        e.markAsDone();
                    }
                    List.add(e);
                }
            }

        }
        br.close();
        fr.close();
    }

    private static void saveTaskList(ArrayList<Task> List) {
        boolean isExistedFile = Files.exists(FILENAME);
        try {
            if (isExistedFile) {
                FileWriter fw = new FileWriter(String.valueOf(FILENAME));
                Writer output = new BufferedWriter(fw);
                for (Task t : List) {
                    if (t.getStatusIcon().equals("\u2718")) {
                        fileDoneStatus = "0";
                    } else {
                        fileDoneStatus = "1";
                    }
                    //must use if-else statement
                    //instanceof does not allow switch statement
                    if (t instanceof ToDos) {
                        output.write(
                                t.getTaskType() + " | " + fileDoneStatus + " | " + t.description
                                        + "\n");
                    } else if (t instanceof Events) {
                        output.write(
                                t.getTaskType() + " | " + fileDoneStatus + " | " + t.description
                                        + "|" + ((Events) t).getTimeOfEvent() + "\n");
                    } else if (t instanceof Deadlines) {
                        output.write(
                                t.getTaskType() + " | " + fileDoneStatus + " | " + t.description
                                        + "|" + ((Deadlines) t).getDueDate() + "\n");
                    }
                }
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateFile(int line, Task t) {
        if (t.getStatusIcon().equals("\u2718")) {
            fileDoneStatus = "0";
        } else {
            fileDoneStatus = "1";
        }
        try {
            List<String> lines = new ArrayList<>(
                    Files.readAllLines(FILENAME, StandardCharsets.UTF_8));

            if (t instanceof ToDos) {
                lines.set(line - 1,
                        t.getTaskType() + " | " + fileDoneStatus + " | " + t.description + "\n");
            } else if (t instanceof Events) {
                lines.set(line - 1,
                        t.getTaskType() + " | " + fileDoneStatus + " | " + t.description + "| "
                                + ((Events) t).getTimeOfEvent() + "\n");
            } else if (t instanceof Deadlines) {
                lines.set(line - 1,
                        t.getTaskType() + " | " + fileDoneStatus + " | " + t.description + "| "
                                + ((Deadlines) t).getDueDate() + "\n");
                Files.write(FILENAME, lines, StandardCharsets.UTF_8);
            }
            Files.write(FILENAME, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void deleteOnFile(int numberToDelete) {
        try {
            List<String> lines = new ArrayList<>(Files.readAllLines(FILENAME, StandardCharsets.UTF_8));
            lines.remove(numberToDelete - 1);
            Files.write(FILENAME, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello ! I'm Sheena ~ ");
        System.out.println("What can I do for you ?");


        String command;
        String description;
        ArrayList<Task> list = new ArrayList<>();
        Scanner in = new Scanner (System.in);
        System.out.println("Sheena: Let me see if you have any previous saved file");
        try {
            readExistFile(list);
            printTasks(list);
            System.out.println("Sheena: Let's begin to add more tasks!");

        } catch (IOException | DukeException e) {
            System.out.println("--------------------------------------");
            System.out.println("Sheena: Uhm, we shall create a new one ^^");
            System.out.println("---------------------------------------\n");
        }

        String option_1 = "bye";
        String option_2 = "list";
        String option_3 = "done";
        String option_4 = "todo";
        String option_5 = "event";
        String option_6 = "deadline";
        String option_7 = "delete";
        while (true)
        {

            command = in.nextLine();
            final Command option = splitCommand(command);
            String choice = option.getCommandName();

            if (choice.equals(option_1)) {
                System.out.println("Sheena: Let me save down everything ^^");
                saveTaskList(list);
                System.out.println("------------------------");
                System.out.println("Sheena: Bye bye people, see you again soon!");
                System.out.println("------------------------");
                System.exit(0);
            } else if (choice.equals(option_2)) {
                try {
                    printTasks(list);
                } catch (DukeException e) {
                    System.out.println("------------------------");
                    System.out.println("Sheena: Erm. You don't have any task yet...");
                    System.out.println("------------------------");
                }
                System.out.println("------------------------");
            } else if (choice.equals(option_3)) {
                int taskNumber = Integer.parseInt(String.valueOf(option.getArgs()));
                Task t = getTheTask(list,taskNumber-1);
                t.markAsDone();
                updateCommand(t,list,taskNumber-1);
                System.out.println("------------------------------------------");
                System.out.print("Sheena: Nice! I've marked this task as done: [");
                System.out.print(t.getStatusIcon());
                System.out.print("]");
                System.out.println(t.description);
                System.out.println("------------------------------------------");
                updateFile(taskNumber,t);
            } else if (choice.equals(option_4)) {
                Task newTask = new ToDos(option.getArgs());
                if ( option.getArgs() == " ")
                {
                    System.out.println("-------------------------------------------");
                    System.out.println("Sheena: Uh Oh! The description cannot be empty ~");
                    System.out.println("-------------------------------------------");
                } else {
                    list.add(newTask);
                    System.out.println("------------------------");
                    System.out.println("Added : ");
                    System.out.println(newTask.toString());
                    System.out.printf("Sheena: You have %d tasks in your list ^^ \n" , list.size());
                    System.out.println("------------------------");
                    saveTaskList(list);
                }
            } else if (choice.equals(option_5)) {
                final int indexOfAtPrefix = option.getArgs().indexOf("/at");
                description = option.getArgs().substring(0, indexOfAtPrefix);
                String Time = option.getArgs().substring(indexOfAtPrefix + 3).trim();
                Task newEvent = new Events(description, Time);
                list.add(newEvent);
                System.out.println("------------------------");
                System.out.println("Added: ");
                System.out.println(newEvent.toString());
                System.out.printf("Sheena: You have %d tasks in your list ^^ \n" , list.size());
                System.out.println("------------------------");
                saveTaskList(list);

            } else if (choice.equals(option_6)) {
                final int indexOfByPrefix = option.getArgs().indexOf("/by");
                description = option.getArgs().substring(0, indexOfByPrefix);
                String Date = option.getArgs().substring(indexOfByPrefix + 3).trim();
                Task newDeadline = new Deadlines(description, Date);
                list.add(newDeadline);
                System.out.println("------------------------");
                System.out.println("Added : ");
                System.out.println(newDeadline.toString());
                System.out.printf("Sheena: You have %d tasks in your list ^^ \n" , list.size());
                System.out.println("------------------------");
                saveTaskList(list);
            } else if (choice.equals(option_7)){
                try {
                    deleteTaskCommand(list, option);
                } catch (NumberFormatException e) {
                    System.out.println("------------------------------------------");
                    System.out.println(String.format(ERROR_MESSAGE, "task number", option_7)
                            + ERROR_DELETE_PARAM + ERROR_DELETE_EXAMPLE);
                    System.out.println("------------------------------------------");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("------------------------------------------");
                    System.out.println(String.format(ERROR_DELETE_MESSAGE, option.getArgs()));
                    System.out.println("------------------------------------------");
                }
            } else {
                // if there is no "done", "bye", "list" in the string
                System.out.println("-------------------------------------------");
                System.out.println("Sheena: Try again maybe? Choose the right option :) ");
                System.out.println("--------------------------------------------");
            }
        }

    }
}
