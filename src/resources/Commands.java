package resources;

/**
 * Provides command and parameter constants for the application.
 * This utility class should not be instantiated.
 *
 * Available commands include actions such as adding elements, quitting the application,
 * retrieving or listing publications, and more.
 *
 * @author bsoenmez
 * @version 1.1
 */
public abstract class Commands {
    /** Command to add a new element (e.g., author, journal, series, conference, article, keywords). */
    public static final String COMMAND_ADD = "add";

    /** Command to quit the application. */
    public static final String COMMAND_QUIT = "quit";

    /** Command for the "written by" operation. */
    public static final String COMMAND_WRITTEN_BY = "written";

    /** Command to specify a citation relationship between publications. */
    public static final String COMMAND_CITES = "cites";

    /** Command to list all publications. */
    public static final String COMMAND_ALL_PUBLICATIONS = "all";

    /** Command to list invalid publications. */
    public static final String COMMAND_LIST_INVALID_PUBLICATIONS = "list";

    /** Command to retrieve publications by a specific criterion. */
    public static final String COMMAND_PUBLICATIONS = "publications";

    /** Command to filter publications in proceedings. */
    public static final String COMMAND_IN = "in";

    /** Command to find elements based on keywords. */
    public static final String COMMAND_FIND = "find";

    /** Command to calculate the Jaccard coefficient for keywords. */
    public static final String COMMAND_JACCARD = "jaccard";

    /** Command to calculate the similarity between two articles. */
    public static final String COMMAND_SIMILARITY = "similarity";

    /** Command to compute the g-index of an author. */
    public static final String COMMAND_G_INDEX = "g-index";

    /** Command to list the coauthors of an author. */
    public static final String COMMAND_COAUTHORS = "coauthors";

    /** Command to display foreign citations of an author. */
    public static final String COMMAND_FOREIGN = "foreign";

    /** Command to print a formatted bibliography. */
    public static final String COMMAND_PRINT = "print";

    /** Separator used to split commands. */
    public static final String COMMAND_SEPARATOR = " ";

    /** Separator used to split parameters. */
    public static final String PARAMETER_SEPARATOR = ",";

    private Commands() {
        // Utility class; prevent instantiation.
    }
}
