package src.ui.commands;

/**
 * A simple command interface for handling UI commands.
 */
public interface Command {
    /**
     * Executes the command using the given tokenized input.
     *
     * @param tokens the tokenized input command line.
     */
    void execute(String[] tokens);
}
