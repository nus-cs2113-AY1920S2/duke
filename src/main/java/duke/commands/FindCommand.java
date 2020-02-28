package duke.commands;

import duke.TaskList;

public class FindCommand extends Command{

    public FindCommand(String parameters) {
        super(parameters.trim());
    }

    @Override
    public void Execute(TaskList tasks) {
        boolean isFound = false;
        for(int i = 0; i < tasks.getSize(); i++) {
            if(tasks.get(i).getDescription().toLowerCase().contains(parameters.toLowerCase())) {
                System.out.println(tasks.get(i));
                isFound = true;
            }
        }
        if(!isFound) {
            System.out.println("No such task found :(");
        }
    }
}
