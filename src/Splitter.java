package src;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for splitting inputs correctly.
 *
 * <p>This class is used to reconstruct parameters that might be split incorrectly when commands are split by spaces.
 * It rebuilds the parameters and splits them using a specified separator.</p>
 *
 * @author bsoenmez
 * @version 1.1
 */
public final class Splitter {

    private Splitter() {
        // Utility class; cannot be instantiated.
    }

    /**
     * Concatenates tokens from the specified start index into a single string separated by spaces,
     * then splits the concatenated string using the provided separator.
     *
     * @param startIndex the starting index from which to begin concatenation
     * @param tokens the array of split words
     * @param separator the separator used to split the concatenated string
     * @return an immutable list of strings resulting from splitting the concatenated string by the separator
     * @throws IllegalArgumentException if tokens is null or if startIndex is invalid
     */
    public static List<String> buildAndCollapse(int startIndex, String[] tokens, String separator) {
        if (tokens == null || startIndex < 0 || startIndex >= tokens.length) {
            throw new IllegalArgumentException("Invalid startIndex or tokens array");
        }
        String concatenated = String.join(" ", Arrays.copyOfRange(tokens, startIndex, tokens.length));
        return List.of(concatenated.split(separator));
    }

    /**
     * Splits the input tokens into a command and its parameters.
     * <p>
     * Starting from the given startIndex, tokens are concatenated and then split using the commandSeparator.
     * The first part is treated as the command, and the second part is further split using the parameterSeparator
     * to produce a list of parameters.
     * </p>
     *
     * @param startIndex the starting index from which to begin processing tokens
     * @param tokens the array of split words
     * @param commandSeparator the separator used to split the command and parameter parts
     * @param parameterSeparator the separator used to split the parameters
     * @return an immutable list containing two immutable lists:
     *         the first list contains the command (as a singleton list),
     *         and the second list contains the parameters.
     * @throws IllegalArgumentException if the input does not contain both command and parameters
     */
    public static List<List<String>> split(int startIndex, String[] tokens, String commandSeparator,
                                           String parameterSeparator) {
        List<String> parts = buildAndCollapse(startIndex, tokens, commandSeparator);
        if (parts.size() < 2) {
            throw new IllegalArgumentException("Input does not contain both command and parameters");
        }
        List<String> commandList = List.of(parts.get(0));
        List<String> parametersList = List.of(parts.get(1).split(parameterSeparator));
        return List.of(commandList, parametersList);
    }
}
