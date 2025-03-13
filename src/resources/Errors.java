package src.resources;


/**
 * This class provides resources and throws error-messages.
 *
 * @author bsoenmez
 * @version 1.0
 */
public final class Errors {

    /**
     * Error-Message if a utility class cannot be instantiated.
     */
    public static final String UTILITY_CLASS_INSTANTIATION = "Error, Utility class cannot be instantiated.";
    /**
     * Error-Message if an invalid year number is entered.
     */
    public static final String INVALID_YEAR = "Error, invalid year number is entered.";
    /**
     * Error-Message if an invalid author name is entered.
     */
    public static final String INVALID_AUTHOR_NAME = "Error, invalid author name is entered.";
    /**
     * Error-Message if an invalid input is entered.
     */
    public static final String INVALID_INPUT = "Error, invalid input.";
    /**
     * Error-Message if at least one of the given authors is already assigned to given article.
     */
    public static final String CANNOT_ASSIGNED = "Error, at least one of the given authors is already assigned "
            + "to this article.";
    /**
     * Error-Message if the system is closed.
     */
    public static final String SYSTEM_CLOSED = "Error, the system is closed.";
    /**
     * Error-Message if the conference in the given year not found.
     */
    public static final String CONFERENCE_NOT_FOUND = "Error, the conference in the given year is not found.";
    /**
     * Error-Message if the conference series not found.
     */
    public static final String SERIES_NOT_FOUND = "Error, series \"%s\" not found.";
    /**
     * Error-Message if the author is not found.
     */
    public static final String AUTHOR_NOT_FOUND = "Error, author \"%s\" not found.";
    /**
     * Error-Message if at least one of the articles does not exist in the system.
     */
    public static final String MISSING_ARTICLE = "Error, at least one of the articles does not exists.";
    /**
     * Error-Message if articles try to cite themselves.
     */
    public static final String CANNOT_CITE_ITSELF = "Error, publications cannot cite themselves.";
    /**
     * Error-Message if an article try to cite another that released in the same year.
     */
    public static final String CANNOT_CITE_SAME_YEAR = "Error, publications cannot cite publications that were "
            + "published in the same year.";
    /**
     * Error-Message if an article try to cite another that released later.
     */
    public static final String CANNOT_CITE_LATER = "Error, publications can only cite publications genuinely "
            + "published before them.";
    /**
     * Error-Message if an article try to cite an article for more than one time.
     */
    public static final String CANNOT_CITE_AGAIN = "Error, a publication can reference the same publication at "
            + "most once.";


    private Errors() {
        // This is a utility class so has a private constructor.
    }
}

