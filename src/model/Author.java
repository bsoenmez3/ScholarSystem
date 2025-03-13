package src.model;

import src.model.publication.Article;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Locale;

/**
 * Represents an author.
 *
 * @author bsoenmez
 * @version 1.0
 */
public class Author {
    private final String firstName;
    private final String lastName;
    private final List<Article> articles;
    private final Set<src.model.Author> coAuthors;
    private final Set<Article> articlesThatZitate;

    /**
     * Constructs an author. An author has a first and a last name. An author writes articles, alone or with coauthors.
     *
     * @param firstName first name of the author
     * @param lastName  last name of the author
     */
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.articles = new ArrayList<>();
        this.coAuthors = new HashSet<>();
        this.articlesThatZitate = new HashSet<>();
    }

    /**
     * Checks if author's name matches with the given string.
     *
     * @param author author's name
     * @return true if author's name matches with the given string.
     */
    public boolean equalsString(String author) {
        return this.toString().equals(author);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode();
    }

    /**
     * Returns author's whole name.
     *
     * @return author's whole name.
     */
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Returns author's name in reversed format.
     *
     * @return author's name in reversed format.
     */
    public String toStringBackwards() {
        return this.lastName + ", " + this.firstName.toUpperCase(Locale.ROOT).charAt(0) + ".";
    }

    /**
     * Returns the articles of the author, which have citate from different articles.
     *
     * @return x
     */
    public Set<Article> getArticlesThatZitate() {
        return this.articlesThatZitate;
    }

    /**
     * Returns identification of articles that Zitate.
     *
     * @return x
     */
    public Set<String> getArticleIdsThatZitate() {
        Set<String> articles = new HashSet<>();
        for (Article article : this.articlesThatZitate) {
            for (src.model.Author author : this.coAuthors) {
                if (!article.getAuthors().contains(author)) {
                    articles.add(article.getId());
                } else {
                    break;
                }
            }
        }
        return articles;
    }

    /**
     * Returns name of coauthors.
     *
     * @return name of coauthors.
     */
    public Set<String> getCoAuthorsNames() {
        Set<String> coAuthorsNames = new HashSet<>();
        for (src.model.Author author : this.coAuthors) {
            coAuthorsNames.add(author.toString());
        }
        return coAuthorsNames;
    }

    /**
     * Assigns article to the authors.
     *
     * @param article article to assign
     * @param authors list of authors
     */
    public void assignArticle(Article article, List<src.model.Author> authors) {
        this.articles.add(article);
        this.coAuthors.addAll(authors);
        this.coAuthors.remove(this);
    }

    /**
     * Returns articles.
     *
     * @return articles list.
     */
    public List<Article> getArticles() {
        return articles;
    }


}
