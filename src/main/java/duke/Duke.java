import duke.commands.*;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.FileNotFoundException;

// unicode-character reference list: \u2717 = ✗, \u2713 ✓

public class Duke {

    public static Storage storage;
    public static TaskList tasks;
    public static Ui ui = new Ui();

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadListDataFromDisk());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public static void main(String[] args) {
        Ui.showWelcome();
        new Duke("data/duke_list.txt");

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = Ui.readInput();
                Command c = Parser.parseInput(userInput);
                c.execute(tasks, ui, storage); //add to taskList list, show ui, add to storage /////////////////
                isExit = c.isExit();
            } catch (DukeException e) {
                e.getMessage();
            }
        }
    }
}
/*
    private static void loadListDataFromDisk(ArrayList<Task> list) {
        try {
            File f = new File("data/duke_list.txt");
            Scanner reader = new Scanner(f);
            while (reader.hasNext()) {
                list.add(new Task(reader.nextLine()));
            }
        } catch (FileNotFoundException ignored) {}
    }
*/

/*
    private static void updateListDataOnDisk(ArrayList<Task> list) {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) {}
        try {
            Files.createFile(Paths.get("data/duke_list.txt"));
        } catch (IOException ignored) {}
        try {
            FileWriter fw = new FileWriter("data/duke_list.txt");
            list.forEach((n) -> {
                try {
                    fw.write(n.filteredTask + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

//    private static void processDeleteCommand(ArrayList<Command> list, String command) {
//        try {
//            System.out.println("    ____________________________________________________________\n"
//                    + "     Noted. I've removed this task: \n" + "       "
//                    + list.get(Integer.parseInt(command.replaceAll("[^\\d]",""))-1).command
//                    + "\n     Now you have " + (list.size()-1) + (list.size()>1?" tasks":" task") + " in the list.\n"
//                    + "    ____________________________________________________________");
//            list.remove(Integer.parseInt(command.replaceAll("[^\\d]",""))-1);
//        } catch (NumberFormatException e){
//            System.out.println("    ____________________________________________________________\n"
//                    + "     Task does not exist!\n"
//                    + "    ____________________________________________________________");
//        }
//    }
//
//    private static void processDoneCommand(ArrayList<Command> list, String command) {
//        try {
//            int listIndex = Integer.parseInt(command.replaceAll("[^\\d]", ""));
//            list.get(listIndex - 1).command = list.get(listIndex - 1).command.replace("✗", "✓");
//            System.out.println("    ____________________________________________________________\n"
//                    + "     Nice! I've marked this task as done:\n       " + list.get(listIndex - 1).command
//                    + "\n    ____________________________________________________________");
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("    ____________________________________________________________\n"
//                    + "     Task does not exist!\n"
//                    + "    ____________________________________________________________");
//        }
//    }
//
//
//    private static void processListCommand(ArrayList<Command> list) {
//        System.out.println("    ____________________________________________________________\n"
//                + "     Here are the tasks in your list:");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("     " + (i + 1) + "." + list.get(i).command);
//        }
//        System.out.print("    ____________________________________________________________\n");
//    }
//
//}
//
