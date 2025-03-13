package src.management;

import src.model.Author;
import src.model.publication.Article;
import src.resources.Errors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Manages the stored authors in the system.
 * Provides operations to retrieve, add, and validate authors.
 *
 * @author bsoenmez
 * @version 1.1
 */
public class AuthorManager {

    private final List<Author> authors;

    /**
     * Constructs an empty AuthorManager.
     */
    public AuthorManager() {
        this.authors = new ArrayList<>();
    }

    /**
     * Returns the author by its name.
     *
     * @param authorName the name of the author
     * @return the matching author, or null if not found
     */
    public Author getAuthorByName(String authorName) {
        String trimmedName = authorName.trim();
        return authors.stream()
                .filter(author -> author.toString().equals(trimmedName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns a list of authors matching the provided names.
     *
     * @param authorNames list of author names
     * @return list of authors corresponding to the given names
     */
    public List<Author> getAuthorsList(List<String> authorNames) {
        return authorNames.stream()
                .map(this::getAuthorByName)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new author to the system if not already present.
     *
     * @param author the author to add
     */
    public void addAuthor(Author author) {
        if (author != null && !checkIfAuthorExists(author.toString())) {
            this.authors.add(author);
        } else {
            System.err.println("Error, author with same name already added.");
        }
    }

    /**
     * Checks if an author with the given name already exists.
     *
     * @param authorName the name of the author
     * @return true if an author with the given name exists, false otherwise
     */
    public boolean checkIfAuthorExists(String authorName) {
        String trimmedName = authorName.trim();
        return authors.stream().anyMatch(author -> author.equalsString(trimmedName));
    }

    /**
     * Checks if all the authors in the given list exist in the system.
     * Prints an error message for the first missing author.
     *
     * @param authorNames list of author names
     * @return true if all authors exist, false otherwise
     */
    public boolean checkIfAuthorsExist(List<String> authorNames) {
        for (String name : authorNames) {
            if (!checkIfAuthorExists(name)) {
                System.err.printf(Errors.AUTHOR_NOT_FOUND + "%n", name);
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the identifiers of all publications in which at least one of the given authors participates.
     *
     * @param authorNames list of author names
     * @return a list of article identifiers, or an empty list if none found
     */
    public List<String> publicationsBy(List<String> authorNames) {
        if (!checkIfAuthorsExist(authorNames)) {
            return Collections.emptyList();
        }
        Set<String> articleIds = new HashSet<>();
        for (String name : authorNames) {
            Author author = getAuthorByName(name);
            if (author != null) {
                articleIds.addAll(author.getArticles().stream()
                        .map(Article::getId)
                        .collect(Collectors.toSet()));
            }
        }
        return new ArrayList<>(articleIds);
    }

    /**
     * Returns an author's g-index based on the publications stored in the system.
     * The g-index is defined as the largest number g such that the top g articles
     * have, together, at least g² citations.
     *
     * @param authorName the name of the author
     * @return the g-index of the author, or 0 if the author is not found
     */
    public int gIndex(String authorName) {
        Author author = getAuthorByName(authorName);
        if (author == null) {
            return 0;
        }
        List<Integer> citations = author.getArticles().stream()
                .map(Article::getSizeOfReceivedZitates)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        int sum = 0;
        int g = 0;
        for (int i = 0; i < citations.size(); i++) {
            sum += citations.get(i);
            if (sum >= Math.pow(i + 1, 2)) {
                g = i + 1;
            }
        }
        return g;
    }
}
