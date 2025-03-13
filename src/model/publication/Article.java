package src.model.publication;

import src.model.Author;
import src.model.venue.Series;
import src.model.venue.Venue;
import src.resources.Errors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents an article.
 *
 * @author bsoenmez
 * @version 1.1
 */
public class Article extends Publication {

    private final List<Author> authors;
    private final ArticleType articleType;
    private final Venue venue;
    private final List<Series> seriesThatArticleUsed;
    private final int year;
    private final Set<src.model.publication.Article> givenZitates;
    private final Set<src.model.publication.Article> receivedZitates;
    private final Bibliography bibliography;

    /**
     * Constructs an article.
     *
     * @param id          identification of the article
     * @param year        release year
     * @param title       title of the article
     * @param articleType type of article
     * @param venue       publication venue of the article
     */
    public Article(String id, int year, String title, ArticleType articleType, Venue venue) {
        super(id, year, title);
        this.authors = new ArrayList<>();
        this.articleType = articleType;
        this.venue = venue;
        this.year = year;
        this.seriesThatArticleUsed = new ArrayList<>();
        this.givenZitates = new HashSet<>();
        this.receivedZitates = new HashSet<>();
        this.bibliography = new Bibliography(this);
    }

    /**
     * Returns the number of received citations.
     *
     * @return the size of the received citations.
     */
    public int getSizeOfReceivedZitates() {
        return receivedZitates.size();
    }

    /**
     * Returns the articles that cite this article.
     *
     * @return set of articles that cite this article.
     */
    public Set<src.model.publication.Article> getReceivedZitates() {
        return this.receivedZitates;
    }

    /**
     * Returns the authors of this article.
     *
     * @return list of authors.
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Cites another article.
     *
     * @param article the article to cite.
     */
    public void citePublication(src.model.publication.Article article) {
        if (article.getId().equals(this.getId())) {
            System.err.println(Errors.CANNOT_CITE_ITSELF);
        } else if (article.year == this.year) {
            System.err.println(Errors.CANNOT_CITE_SAME_YEAR);
        } else if (article.year > this.year) {
            System.err.println(Errors.CANNOT_CITE_LATER);
        } else if (this.givenZitates.contains(article)) {
            System.err.println(Errors.CANNOT_CITE_AGAIN);
        } else {
            givenZitates.add(article);
            article.getReceivedZitates().add(this);
            // Update each author of the cited article with this citation.
            for (Author author : article.authors) {
                author.getArticlesThatZitate().add(this);
            }
        }
    }

    /**
     * Returns an identifier if the article is invalid (i.e. has no authors assigned),
     * or null if the article is valid.
     *
     * @return invalid publication identifier or null.
     */
    public String getInvalidPublication() {
        return this.authors.isEmpty() ? this.getId() : null;
    }

    /**
     * Adds a series to which this article belongs.
     *
     * @param series the series the article is used in.
     */
    public void addSeriesThatArticleUsed(Series series) {
        this.seriesThatArticleUsed.add(series);
    }

    /**
     * Checks if the author is already assigned to this article.
     *
     * @param author the author to check.
     * @return true if the author is already assigned.
     */
    public boolean checkIfAuthorAlreadyAssigned(Author author) {
        return authors != null && !authors.isEmpty() && authors.contains(author);
    }

    /**
     * Returns the type of this article.
     *
     * @return the article type.
     */
    public ArticleType getArticleType() {
        return articleType;
    }

    /**
     * Returns the publication venue.
     *
     * @return the venue of the article.
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Returns the bibliography for this article.
     *
     * @return the bibliography.
     */
    public Bibliography getBibliography() {
        return bibliography;
    }

    /**
     * Assigns an author to this article.
     *
     * @param author the author to assign.
     */
    public void assignAuthorsToArticle(Author author) {
        this.authors.add(author);
    }
}
