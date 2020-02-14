import task.Deadline;
import task.Event;
import task.Todo;
import task.Task;
import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;

import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws InvalidCommandException, MissingDescriptionException {
        UI.initUI();
        UI.printGreetMessage();

        String userInput;
        ArrayList<Task> tasks = new ArrayList<Task> (100);

        while (true) {
            userInput = UI.getNextLine();

            // Exit if user says bye
            if (userInput.equals("bye")) {
                break;
            }

            try {
                String[] parsedInput = userInput.split(" ", 2);
                String command = parsedInput[0];
                String words = "";
                if (parsedInput.length > 1) {
                    words = parsedInput[1];
                }

                switch(command) {
                case "list": // List all the tasks
                    listTasks(tasks, tasks.size());
                    break;
                case "done": // Mark a task as done
                    UI.br();
                    System.out.println("\t Dun dun dun dun! This task is done:");
                    int taskIdx = Integer.parseInt(words);
                    taskIdx--; // -1 for zero-based indexing
                    tasks.get(taskIdx).setDone();
                    System.out.println("\t   " + tasks.get(taskIdx));
                    UI.br();
                    break;
                case "delete": // Delete a task
                    UI.br();
                    System.out.println("\t This task has been whisked out of existence:");
                    // To add code
                    System.out.println(tasks.size() + " tasks remaining.");
                    UI.br();
                    break;
                default: // Add a task
                    Task t;
                    // Parse input to obtain text and timeDescriptor
                    String timeDescriptor = "";
                    String text = "";
                    int slashPos = words.indexOf('/');
                    if (slashPos != -1) {
                        timeDescriptor = words.substring(slashPos + 4);
                        text = words.substring(0, slashPos);
                    }

                    if (command.equals("todo")) {
                        t = new Todo(words);
                        if (words.equals("")) {
                            throw new MissingDescriptionException();
                        }
                    } else if (command.equals("deadline")) {
                        t = new Deadline(text, timeDescriptor);
                        if (text.equals("") || timeDescriptor.equals("")) {
                            throw new MissingDescriptionException();
                        }
                    } else if (command.equals("event")) {
                        t = new Event(text, timeDescriptor);
                        if (text.equals("") || timeDescriptor.equals("")) {
                            throw new MissingDescriptionException();
                        }
                    } else {
                        throw new InvalidCommandException();
                    }

                    tasks.add(t); // Append the task to the ArrayList

                    printAddedTaskMessage(t, tasks.size());
                    break;
                }
            } catch (MissingDescriptionException e) {
                UI.br();
                System.out.println("\t ☹ OOPS!!! The task description cannot be empty.");
                UI.br();
            } catch (InvalidCommandException e) {
                UI.br();
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                UI.br();
            } catch (IndexOutOfBoundsException e) {
                UI.br();
                System.out.println("\t ☹ OOPS!!! The task description cannot be empty.");
                UI.br();
            }
        }

        UI.printEndMessage();
    }

    /** Prints the message that is displayed after a task is added */
    private static void printAddedTaskMessage(Task t, int numTasks) {
        UI.br();
        System.out.println("\t Dook has added task: ");
        System.out.println("\t  " + t);
        System.out.println("\t " + numTasks + " task(s) in the list now!");
        UI.br();
    }

    /** Prints all tasks in the list */
    private static void listTasks(ArrayList<Task> tasks, int numTasks) {
        UI.br();
        System.out.println("\t Dook will list your tasks now:");
        for (int i=0; i<numTasks; i++) {
            int taskNum = i+1;
            System.out.println("\t " + taskNum + ". " + tasks.get(i));
        }
        UI.br();
    }

}
