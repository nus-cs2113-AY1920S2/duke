package chatty.command;

/**
 * Find command used in the application.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for find command.
     * @param keyword The keyword in the find command.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets keyword in the find command.
     * @return Keyword in the find command.
     */
    public String getKeyword() {
        return keyword;
    }
}
