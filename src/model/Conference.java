package src.model;

/**
 * Represents a conference with a release year and location.
 *
 * @version 1.1
 */
public class Conference {
    private final int releaseYear;
    private final String location;

    /**
     * Constructs a conference.
     *
     * @param releaseYear the release year of the conference
     * @param location    the location where the conference was held
     */
    public Conference(int releaseYear, String location) {
        this.releaseYear = releaseYear;
        this.location = location;
    }

    /**
     * Returns the release year of the conference.
     *
     * @return the release year
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Returns the location of the conference.
     *
     * @return the conference location
     */
    public String getLocation() {
        return location;
    }
}
