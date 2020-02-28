package duke;

public class Parser {

    /**
     * reads inputLine and determines which command
     * it is then passes on the input Line to the
     * correct command to carry out.
     * If command is "bye" then will prepare to send out of program
     *
     * @throws DukeException if command is unreadable
     */
    public static void determineCommand() throws DukeException {
        if (Duke.inputLine.equals("bye")) {
            Duke.isEnd = true;
        } else if (Duke.inputLine.substring(0, 4).equals("list")) {
            Ui.printsOutTheList(); // prints out the list

        } else if (Duke.inputLine.substring(0, 4).equals("done")) {
            TaskList.indicateTaskAsDone(); // marks task in the stated index as done

        } else if (Duke.inputLine.substring(0, 6).equals("remove")) {
            TaskList.removeTaskFromList(); // removes task in the stated index

        } else {
            TaskList.addInNewTask();// adds a new task into the list
        }
    }
}
