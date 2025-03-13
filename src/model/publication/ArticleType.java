package src.model.publication;

/**
 * This is the main class.
 *
 * @author bsoenmez
 * @version 1.0
 */
public enum ArticleType {
    /**
     * The {@link src.model.publication.ArticleType conference article}
     */
    CONFERENCE_ARTICLE("Conference"),
    /**
     * The {@link src.model.publication.ArticleType journal article}
     */
    JOURNAL_ARTICLE("Journal");
    private final String identifier;

    /**
     * Constructs the article types.
     *
     * @param identifier identifier of article types.
     */
    ArticleType(String identifier) {
        this.identifier = identifier;
    }


    @Override
    public String toString() {
        return this.identifier;
    }
}

