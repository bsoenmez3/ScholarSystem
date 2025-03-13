package src.model.venue;

import src.model.publication.Article;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * This is the main class.
 *
 * @author bsoenmez
 * @version 1.0
 */
public class Venue {

    private final List<Article> articles;
    private final Set<String> keywords;
    private final String name;

    /**
     * Constructs a venue.
     *
     * @param name name of the venue
     */
    public Venue(String name) {
        this.name = name;
        this.articles = new ArrayList<>();
        this.keywords = new HashSet<>();
    }

    /**
     * Adds an article to the venue.
     *
     * @param keywords keywords of the article
     */
    public void addKeywords(Set<String> keywords) {
        Set<String> matchedKeyWords = new HashSet<>();
        for (String keyword : keywords) {
            if (keyword.matches("^[a-z]+$")) {
                matchedKeyWords.add(keyword);
            }
        }
        this.keywords.addAll(matchedKeyWords);
        for (Article article : this.articles) {
            article.addKeywords(matchedKeyWords);
        }
    }


    /**
     * Adds an article to the venue.
     */
    public String toString() {
        return this.name;
    }

    /**
     *
     */
    public List<String> getArticlesByYear(int year) {
        List<String> matchedArticles = new ArrayList<>();
        for (Article article : this.articles) {
            if (article.getYear() == year) {
                matchedArticles.add(article.getId());
            }
        }
        if (matchedArticles.size() == 0) {
            System.err.println("-");
        } else {
            return matchedArticles;
        }
        return null;
    }

    /**
     *
     */
    public String getName() {
        return name;
    }


    /**
     * x
     *
     * @param article x
     */
    public void addArticleToVenue(Article article) {
        this.articles.add(article);
    }

}

