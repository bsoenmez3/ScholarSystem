package src.model.venue;

import src.model.Conference;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class.
 *
 * @author bsoenmez
 * @version 1.0
 */
public class Series extends Venue {

    private final List<Conference> conferences;


    /**
     * Constructs a conference series.
     *
     * @param name name of the conference series
     */
    public Series(String name) {
        super(name);
        this.conferences = new ArrayList<>();
    }


    /**
     * Returns conference that matches with the given year.
     *
     * @param year release year
     * @return conference.
     */
    public Conference getConferenceByYear(int year) {
        for (Conference conference : this.conferences) {
            if (conference.getReleaseYear() == year) {
                return conference;
            }
        }
        return null;
    }

    /**
     * Adds a conference to the conference series.
     *
     * @param conference conference
     */
    public void addConference(Conference conference) {
        this.conferences.add(conference);
    }


}
