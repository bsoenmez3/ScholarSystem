package src.management;

import src.model.Conference;
import src.model.venue.Series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the main class.
 *
 * @author bsoenmez
 * @version 1.1
 */
public class SeriesManager {

    private final List<Series> series;

    /**
     * Constructs an empty {@link src.management.SeriesManager series manager}.
     */
    public SeriesManager() {
        this.series = new ArrayList<>();
    }

    /**
     * Adds a new series to the manager.
     *
     * @param series the series to add
     */
    public void addSeries(Series series) {
        if (series != null) {
            this.series.add(series);
        }
    }

    /**
     * Checks if a series with the given name exists.
     *
     * @param seriesName the name of the series
     * @return true if the series exists, false otherwise
     */
    public boolean checkIfSeriesExists(String seriesName) {
        String trimmedName = seriesName.trim();
        return series.stream().anyMatch(s -> s.getName().equals(trimmedName));
    }


    /**
     * Returns the conference of a series by the given release year.
     *
     * @param seriesName the name of the series
     * @param year       the release year
     * @return the corresponding conference, or null if not found
     */
    public Conference getConferenceByYear(String seriesName, int year) {
        Series s = getSeriesByName(seriesName);
        return s != null ? s.getConferenceByYear(year) : null;
    }

    /**
     * Returns the article identifiers for a series in a given year.
     *
     * @param seriesName the name of the series
     * @param year       the release year
     * @return a list of article identifiers, or an empty list if none found
     */
    public List<String> getArticlesByYear(String seriesName, int year) {
        Series s = getSeriesByName(seriesName);
        return s != null ? s.getArticlesByYear(year) : Collections.emptyList();
    }

    /**
     * Retrieves a series by its name.
     *
     * @param name the name of the series
     * @return the matching series, or null if not found
     */
    public Series getSeriesByName(String name) {
        String trimmedName = name.trim();
        return series.stream()
                .filter(s -> s.getName().equals(trimmedName))
                .findFirst()
                .orElse(null);
    }

}

