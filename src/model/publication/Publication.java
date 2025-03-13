package src.model.publication;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a publication with an identifier, release year, title, and keywords.
 *
 * @version 1.1
 */
public class Publication {
    private final String id;
    private final int year;
    private final String title;
    private final Set<String> keywords;

    /**
     * Constructs a publication.
     *
     * @param id    the publication identifier
     * @param year  the release year of the publication
     * @param title the title of the publication
     */
    public Publication(String id, int year, String title) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.keywords = new HashSet<>();
    }

    /**
     * Adds valid keywords to the publication.
     * Only keywords matching the regex "^[a-z]+$" are added.
     *
     * @param keywords the set of keywords to add
     */
    public void addKeywords(Set<String> keywords) {
        keywords.stream()
                .filter(keyword -> keyword.matches("^[a-z]+$"))
                .forEach(this.keywords::add);
    }

    /**
     * Returns the set of keywords for the publication.
     *
     * @return a set of keywords
     */
    public Set<String> getKeywords() {
        return keywords;
    }

    /**
     * Returns the publication identifier.
     *
     * @return the id of the publication
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the release year of the publication.
     *
     * @return the release year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the title of the publication.
     *
     * @return the publication title
     */
    public String getTitle() {
        return title;
    }
}
