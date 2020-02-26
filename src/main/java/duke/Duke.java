package duke;

import java.io.IOException;

import commands.Command;
import ui.TextUi;
import storage.Storage;
import common.exceptions.DukeException;
import data.TaskList;
import parser.Parser;

public class Duke {
    private TextUi ui;
    private Storage storage;
    private TaskList tasks;
    
    public Duke() throws IOException, DukeException {
    	this.ui = new TextUi();
    	this.storage = new Storage();
    	storage.readFromFile();
    	this.tasks = new TaskList(storage);
    }
    
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }
    
    public void run() throws DukeException, IOException{
        ui.greet(tasks);
        initialiseChatbot();
        ui.goodbye();
        System.exit(0);
    }
    
    public void initialiseChatbot() throws IOException, DukeException {
    	boolean isTerminated = false;
    	while (!isTerminated) {
            String input = ui.getUserCommand();
            Command command = new Parser().parse(input);
            tasks.executeCommand(ui, storage, command);
            isTerminated = tasks.isTerminated();
        }
    }
    
}
