package duke.commands;

import java.util.ArrayList;

public class CommandResult {
    private final String message;
    private final boolean isPrintList;
    private ArrayList<Integer> printListIndices;

    public CommandResult(String message) {
        this.message = message;
        isPrintList = false;
        printListIndices = null;
    }

    public CommandResult(String message, boolean isPrint) {
        this.message = message;
        isPrintList = isPrint;
        printListIndices = null;
    }

    public CommandResult(String message, boolean isPrint, ArrayList<Integer> list) {
        this.message = message;
        isPrintList = isPrint;
        printListIndices = list;
    }

    public String getMessage() {
        return message;
    }

    public boolean getPrintStatus() {
        return isPrintList;
    }

    public ArrayList<Integer> getPrintListIndices() {
        return printListIndices;
    }
}
