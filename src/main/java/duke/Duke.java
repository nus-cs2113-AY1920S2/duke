package duke;


import java.io.FileNotFoundException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(String filePath, Scanner input) {
        this.ui = new Ui(input);
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.readFromFile(filePath);
        } catch (FileNotFoundException e){
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String command = ui.getUserCommand();
            Parser.parseCommand(command, this.tasks);
        }
     }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        new Duke("./duke.txt",input).run();

        }

}

