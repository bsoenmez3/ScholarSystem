package src.model.publication;

import src.model.Author;
import src.model.venue.Series;

/**
 * Represents a bibliography for a specific article.
 * Provides methods to format the bibliographic reference in ACM and APA styles.
 *
 * @version 1.1
 */
public class Bibliography {
    private final Article article;

    /**
     * Constructs a bibliography for the given article.
     *
     * @param article the article for which the bibliography is written
     */
    public Bibliography(Article article) {
        this.article = article;
    }

    /**
     * Returns the article formatted in ACM style.
     *
     * @param year the release year used for formatting
     * @return the ACM style formatted reference
     */
    public String getAcmFormatted(int year) {
        StringBuilder sb = new StringBuilder();
        if (article.getArticleType() == ArticleType.CONFERENCE_ARTICLE) {
            Series series = (Series) article.getVenue();
            sb.append(formatAuthorsAcm()).append(". ")
                    .append(article.getTitle())
                    .append(". In Proceedings of ")
                    .append(series.getName()).append(", ")
                    .append(series.getConferenceByYear(year).getReleaseYear()).append(", ")
                    .append(series.getConferenceByYear(year).getLocation()).append(".");
        } else if (article.getArticleType() == ArticleType.JOURNAL_ARTICLE) {
            sb.append(formatAuthorsAcm()).append(". ")
                    .append(article.getYear()).append(". ")
                    .append(article.getTitle()).append(". ")
                    .append(article.getVenue().toString()).append(".");
        }
        return sb.toString();
    }

    /**
     * Returns the article formatted in APA style.
     *
     * @param year the release year used for formatting
     * @return the APA style formatted reference
     */
    public String getApaFormatted(int year) {
        StringBuilder sb = new StringBuilder();
        if (article.getArticleType() == ArticleType.CONFERENCE_ARTICLE) {
            Series series = (Series) article.getVenue();
            sb.append(formatAuthorsApa()).append(". (")
                    .append(series.getConferenceByYear(year).getReleaseYear()).append("). ")
                    .append(article.getTitle()).append(". ")
                    .append(series.getName()).append(", ")
                    .append(series.getConferenceByYear(year).getLocation()).append(".");
        } else if (article.getArticleType() == ArticleType.JOURNAL_ARTICLE) {
            sb.append(formatAuthorsApa()).append(". (")
                    .append(article.getYear()).append("). ")
                    .append(article.getTitle()).append(". ")
                    .append(article.getVenue().toString()).append(".");
        }
        return sb.toString();
    }

    private String formatAuthorsApa() {
        StringBuilder sb = new StringBuilder();
        for (Author author : article.getAuthors()) {
            sb.append(author.toStringBackwards()).append(", & ");
        }
        int len = sb.length();
        return (len >= 5) ? sb.substring(0, len - 5) : sb.toString();
    }

    private String formatAuthorsAcm() {
        StringBuilder sb = new StringBuilder();
        for (Author author : article.getAuthors()) {
            sb.append(author.toString()).append(" and ");
        }
        int len = sb.length();
        return (len >= 5) ? sb.substring(0, len - 5) : sb.toString();
    }
}
