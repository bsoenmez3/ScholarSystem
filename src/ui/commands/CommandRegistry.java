package src.ui.commands;

import src.model.ScholarSystem;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Maps command names to their corresponding Command implementations.
 * This registry is built once during construction and is immutable.
 */
public class CommandRegistry {
    private final Map<String, Command> registry;

    /**
     * Constructs a CommandRegistry and registers all command handlers.
     *
     * @param system the ScholarSystem instance used by command handlers
     */
    public CommandRegistry(ScholarSystem system) {
        Map<String, Command> commands = new HashMap<>();
        this.registry = Collections.unmodifiableMap(commands);
    }

    /**
     * Returns the Command associated with the given command name.
     *
     * @param commandName the name of the command to look up
     * @return the corresponding Command, or null if not found
     */
    public Command get(String commandName) {
        return registry.get(commandName);
    }
}
