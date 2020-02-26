package duke;

import duke.commands.Command;
import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.data.task.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TextUi ui;
    private TaskList tasks;

    public Duke() {
        try {
            ui = new TextUi();
            storage = new Storage();
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        try {
            while (!isExit) {
                try {
                    String fullCommand = ui.readCommand();
                    System.out.println(Messages.DIVIDER);
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks);
                    isExit = c.isExit();
                }catch (DukeException e) {
                    System.out.println(e.getMessage());
                }catch (NumberFormatException nfe) {
                    System.out.println(Messages.MESSAGE_INDEX_NOT_AN_INTEGER);
                } catch (Exception e) {
                    System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } finally {
                    ui.showLine();
                }
            }
            storage.save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

