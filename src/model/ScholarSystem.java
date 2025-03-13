package src.model;

import src.model.publication.ArticleType;
import src.model.publication.Article;
import src.management.ArticleManager;
import src.management.AuthorManager;
import src.management.ConferenceManager;
import src.management.JournalManager;
import src.management.SeriesManager;
import src.model.venue.Journal;
import src.model.venue.Series;
import src.resources.Errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Main system class for the scholar system.
 * Manages authors, journals, series, conferences, and articles.
 * Provides methods to add and query scholarly publications and related data.
 *
 * @version 1.1
 */
public class ScholarSystem {
    /**
     * Maximum valid year.
     */
    public static final int MAX_YEAR = 9999;

    private final AuthorManager authorManager;
    private final JournalManager journalManager;
    private final SeriesManager seriesManager;
    private final ConferenceManager conferenceManager;
    private final ArticleManager articleManager;

    /**
     * Constructs a ScholarSystem.
     */
    public ScholarSystem() {
        this.authorManager = new AuthorManager();
        this.journalManager = new JournalManager();
        this.seriesManager = new SeriesManager();
        this.conferenceManager = new ConferenceManager();
        this.articleManager = new ArticleManager();
    }

    /**
     * Adds a new author to the system.
     *
     * @param firstName first name of the author.
     * @param lastName  last name of the author.
     */
    public void addAuthor(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        authorManager.addAuthor(author);
    }

    /**
     * Adds a new journal to the system.
     *
     * @param journalName name of the journal.
     * @param publisher   name of the publisher.
     */
    public void addJournal(String journalName, String publisher) {
        Journal journal = new Journal(journalName, publisher);
        journalManager.addJournal(journal);
    }

    /**
     * Adds a new series to the system.
     *
     * @param seriesName name of the series.
     */
    public void addSeries(String seriesName) {
        Series series = new Series(seriesName);
        seriesManager.addSeries(series);
    }

    /**
     * Adds a new conference to the system under the specified series.
     *
     * @param seriesName  name of the series the conference belongs to.
     * @param releaseYear release year of the conference.
     * @param location    location of the conference.
     */
    public void addConference(String seriesName, int releaseYear, String location) {
        if (releaseYear < 0 || releaseYear > MAX_YEAR) {
            System.out.println(Errors.INVALID_YEAR);
            return;
        }
        Series series = seriesManager.getSeriesByName(seriesName);
        if (series != null) {
            Conference conference = new Conference(releaseYear, location);
            series.addConference(conference);
            conferenceManager.addConference(conference);
        } else {
            System.err.println("Error, conference series not found.");
        }
    }

    /**
     * Checks if an article can be assigned to the specified authors.
     * If an author is already assigned to the article, assignment fails.
     *
     * @param authorNames list of author names.
     * @param articleId   article identifier.
     * @return true if assignment is successful; false otherwise.
     */
    public boolean checkAssign(List<String> authorNames, String articleId) {
        Article article = articleManager.getArticleById(articleId);
        if (article == null) return false;
        List<Author> authorList = authorManager.getAuthorsList(authorNames);
        for (Author author : authorList) {
            if (article.checkIfAuthorAlreadyAssigned(author)) {
                return false;
            }
        }
        for (Author author : authorList) {
            article.assignAuthorsToArticle(author);
            author.assignArticle(article, authorList);
        }
        return true;
    }

    /**
     * Returns the identifiers of all publications in the specified conference series for the given year.
     *
     * @param seriesName name of the conference series.
     * @param year       release year.
     * @return list of article identifiers; an empty list if not found.
     */
    public List<String> inProceedings(String seriesName, int year) {
        if (!seriesManager.checkIfSeriesExists(seriesName)) {
            System.err.printf((Errors.SERIES_NOT_FOUND) + "%n", seriesName);
            return Collections.emptyList();
        }
        Conference conference = seriesManager.getConferenceByYear(seriesName, year);
        if (conference == null) {
            System.err.println(Errors.CONFERENCE_NOT_FOUND);
            return Collections.emptyList();
        }
        return seriesManager.getArticlesByYear(seriesName, year);
    }

    private Series getSeries(String seriesName) {
        return seriesManager.getSeriesByName(seriesName);
    }

    /**
     * Calculates the Jaccard index for two sets of keywords.
     *
     * @param firstSet  first set of keywords.
     * @param secondSet second set of keywords.
     * @return the Jaccard index as a formatted string.
     */
    private String jaccard(Set<String> firstSet, Set<String> secondSet) {
        int intersectionSize = (int) firstSet.stream()
                .filter(secondSet::contains)
                .count();
        int unionSize = firstSet.size() + secondSet.size() - intersectionSize;
        double index = unionSize == 0 ? 0.0 : (double) intersectionSize / unionSize;
        return String.format("%.4f", index);
    }

    /**
     * Returns the Jaccard coefficient for two arrays of keywords.
     *
     * @param firstArray  first array of keywords.
     * @param secondArray second array of keywords.
     * @return the Jaccard coefficient as a formatted string.
     */
    public String jaccardIndex(String[] firstArray, String[] secondArray) {
        Set<String> firstSet = new HashSet<>(Arrays.asList(firstArray));
        Set<String> secondSet = new HashSet<>(Arrays.asList(secondArray));
        return jaccard(firstSet, secondSet);
    }

    /**
     * Returns the similarity index between two articles based on their keywords.
     *
     * @param firstArticleId  id of the first article.
     * @param secondArticleId id of the second article.
     * @return the similarity index as a formatted string, or null if articles do not exist.
     */
    public String similarity(String firstArticleId, String secondArticleId) {
        if (articleManager.checkIfArticlesExist(new String[]{firstArticleId, secondArticleId})) {
            Set<String> firstKeywords = articleManager.getArticleById(firstArticleId).getKeywords();
            Set<String> secondKeywords = articleManager.getArticleById(secondArticleId).getKeywords();
            return jaccard(firstKeywords, secondKeywords);
        }
        return null;
    }

    /**
     * Returns the identifiers of all publications in which at least one of the specified authors participates.
     *
     * @param authorList list of author names.
     * @return list of article identifiers.
     */
    public List<String> publicationsBy(List<String> authorList) {
        return authorManager.publicationsBy(authorList);
    }

    /**
     * Adds keywords to a conference series.
     *
     * @param keywords   array of keywords to add.
     * @param seriesName name of the conference series.
     */
    public void addKeywordsToSeries(String[] keywords, String seriesName) {
        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords));
        Series series = seriesManager.getSeriesByName(seriesName);
        if (series != null) {
            series.addKeywords(keywordsSet);
        }
    }

    /**
     * Adds keywords to an existing article.
     *
     * @param keywords array of keywords to add.
     * @param id       article identifier.
     */
    public void addKeywordsToArticle(String[] keywords, String id) {
        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords));
        Article article = articleManager.getArticleById(id);
        if (article != null) {
            article.addKeywords(keywordsSet);
        }
    }

    /**
     * Adds keywords to a journal.
     *
     * @param keywords    array of keywords to add.
     * @param journalName name of the journal.
     */
    public void addKeywordsToJournal(String[] keywords, String journalName) {
        Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords));
        Journal journal = journalManager.getJournalByName(journalName);
        if (journal != null) {
            journal.addKeywords(keywordsSet);
        }
    }

    /**
     * Returns the g-index of an author based on their publications.
     *
     * @param authorName name of the author.
     * @return the g-index, or 0 if the author is not found.
     */
    public int gIndex(String authorName) {
        return authorManager.gIndex(authorName);
    }

    /**
     * Specifies that one publication cites another.
     *
     * @param citingPublicationId id of the publication that cites.
     * @param citedPublicationId  id of the publication being cited.
     */
    public void cite(String citingPublicationId, String citedPublicationId) {
        Article citing = articleManager.getArticleById(citingPublicationId);
        Article cited = articleManager.getArticleById(citedPublicationId);
        if (citing != null && cited != null) {
            citing.citePublication(cited);
        }
    }

    private boolean checkIfArticlesExist(String[] articleIds) {
        return articleManager.checkIfArticlesExist(articleIds);
    }

    private Journal checkIfJournalExists(String journalName) {
        return journalManager.getJournals().stream()
                .filter(journal -> journal.toString().equals(journalName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the bibliography formatted in ACM style for the given article identifiers.
     *
     * @param listOfIds array of article identifiers.
     * @return the ACM style bibliography as a string.
     */
    public String printBibliographyWithAcmStyle(String[] listOfIds) {
        if (!checkIfArticlesExist(listOfIds)) {
            return "";
        }
        StringBuilder acmStyle = new StringBuilder();
        for (int i = 0; i < listOfIds.length; i++) {
            String articleID = listOfIds[i];
            Article article = articleManager.getArticleById(articleID);
            if (article != null) {
                acmStyle.append("[").append(i + 1).append("] ")
                        .append(article.getBibliography().getAcmFormatted(article.getYear()))
                        .append("\n");
            }
        }
        if (acmStyle.length() > 0) {
            acmStyle.setLength(acmStyle.length() - 1); // Remove trailing newline
        }
        return acmStyle.toString();
    }

    /**
     * Returns a list of bibliographic references in APA style for the given article identifiers.
     *
     * @param listOfIds array of article identifiers.
     * @return list of APA style formatted references.
     */
    public List<String> printBibliographWithApaStyle(String[] listOfIds) {
        if (!checkIfArticlesExist(listOfIds)) {
            return Collections.emptyList();
        }
        List<String> references = new ArrayList<>();
        for (String articleID : listOfIds) {
            Article article = articleManager.getArticleById(articleID);
            if (article != null) {
                references.add(article.getBibliography().getApaFormatted(article.getYear()));
            }
        }
        return references;
    }

    /**
     * Adds a scholarly article to a publication venue.
     *
     * @param venueType      type of the venue ("series" or "journal").
     * @param venueName      name of the venue.
     * @param id             article identifier.
     * @param year           release year.
     * @param titleOfArticle title of the article.
     */
    public void addArticle(String venueType, String venueName, String id, int year, String titleOfArticle) {
        if (year < 0 || year > MAX_YEAR) {
            System.out.println(Errors.INVALID_YEAR);
            return;
        }
        if ("series".equalsIgnoreCase(venueType)) {
            Series series = getSeries(venueName);
            if (series != null) {
                Article article = new Article(id, year, titleOfArticle, ArticleType.CONFERENCE_ARTICLE, series);
                articleManager.addArticle(article);
                article.addSeriesThatArticleUsed(series);
                series.addArticleToVenue(article);
            } else {
                System.err.println("Error, the given series does not exist.");
            }
        } else if ("journal".equalsIgnoreCase(venueType)) {
            Journal journal = checkIfJournalExists(venueName);
            if (journal != null) {
                Article article = new Article(id, year, titleOfArticle, ArticleType.JOURNAL_ARTICLE, journal);
                articleManager.addArticle(article);
                journal.addArticleToVenue(article);
            } else {
                System.err.println("Error, the given journal does not exist.");
            }
        }
    }

}
