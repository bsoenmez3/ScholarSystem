package src.management;


import src.model.publication.Article;
import src.resources.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Manages the stored articles in the system.
 *
 * @author bsoenmez
 * @version 1.0
 */
public class ArticleManager {

    private final List<Article> articles;

    /**
     * Constructs an empty {@link src.management.ArticleManager article manager}.
     */
    public ArticleManager() {
        this.articles = new ArrayList<>();
    }


    /**
     * Adds an article to the system.
     *
     * @param article the article to add
     */
    public void addArticle(Article article) {
        if (article != null) {
            this.articles.add(article);
        }
    }


    /**
     * Checks if all the given articles exist in the system.
     *
     * @param articleIds an array of article identifications
     * @return true if all articles exist; otherwise prints an error and returns false
     */
    public boolean checkIfArticlesExist(String[] articleIds) {
        for (String id : articleIds) {
            if (!articleExists(id)) {
                System.err.println(Errors.MISSING_ARTICLE);
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if an article exists in the system.
     *
     * @param articleId the identification of the article
     * @return true if the article exists, false otherwise
     */
    private boolean articleExists(String articleId) {
        return articles.stream().anyMatch(article -> article.getId().equals(articleId));
    }

    /**
     * Prints article identifications in case-insensitive sorted order.
     */
    public void printArticles() {
        List<String> sortedIds = getArticleIds();
        sortedIds.sort(String.CASE_INSENSITIVE_ORDER);
        sortedIds.forEach(System.out::println);
    }


    /**
     * Returns the article by its identification.
     *
     * @param id the article identification
     * @return the corresponding article, or null if not found
     */
    public Article getArticleById(String id) {
        return articles.stream()
                .filter(article -> article.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the article identifications that contain all the given keywords.
     *
     * @param keywords the set of keywords to check
     * @return a list of article identifications that contain all the given keywords
     */
    public List<String> findArticleByKeywords(Set<String> keywords) {
        return articles.stream()
                .filter(article -> article.getKeywords().containsAll(keywords))
                .map(Article::getId)
                .collect(Collectors.toList());
    }

    /**
     * Returns the articles that has all the given keywords.
     *
     * @param keyword keywords to check
     * @return the article identifications that has all the given keywords.
     */
    public List<String> containsKey(Set<String> keyword) {
        List<String> articlesList = new ArrayList<String>();
        for (Article article : articles) {
            if (article.getKeywords().containsAll(keyword)) {
                articlesList.add(article.getId());
            }
        }
        return articlesList;
    }

    /**
     * Returns a list of invalid publications.
     *
     * @return a list of invalid publication identifiers
     */
    public List<String> listInvalidPublications() {
        return articles.stream()
                .map(Article::getInvalidPublication)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private List<String> getArticleIds() {
        List<String> articleIds = new ArrayList<>();
        for (Article element : this.articles) {
            articleIds.add(element.getId());
        }
        return articleIds;
    }
}
