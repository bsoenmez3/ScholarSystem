package src.ui;

import resources.Commands;
import src.model.ScholarSystem;
import src.ui.commands.Command;
import src.ui.commands.CommandRegistry;
import src.resources.Errors;

import java.util.Objects;
import java.util.Scanner;

/**
 * The main UI class that starts the interactive session.
 * Delegates command processing to individual Command implementations.
 *
 * @version 2.0
 */
public class ScholarSystemUI {
    private final CommandRegistry registry;

    public ScholarSystemUI(ScholarSystem scholarSystem) {
        Objects.requireNonNull(scholarSystem, "scholarSystem cannot be null");
        this.registry = new CommandRegistry(scholarSystem);
    }

    /**
     * Starts the interactive session.
     */
    public void interactive() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(Errors.INVALID_INPUT);
                continue;
            }
            String[] tokens = input.split(Commands.COMMAND_SEPARATOR);
            if (tokens[0].equals(Commands.COMMAND_QUIT)) {
                break;
            }
            Command cmd = registry.get(tokens[0]);
            if (cmd != null) {
                try {
                    cmd.execute(tokens);
                } catch (Exception e) {
                    System.err.println(Errors.INVALID_INPUT);
                }
            } else {
                System.out.println(Errors.INVALID_INPUT);
            }
        }
        scanner.close();
    }
}
