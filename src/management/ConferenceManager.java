package src.management;

import src.model.Conference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the stored conferences in the system.
 *
 * @author bsoenmez
 * @version 1.0
 */
public class ConferenceManager {

    private final List<Conference> conferences;

    /**
     * Constructs an empty {@link src.management.ConferenceManager}.
     */
    public ConferenceManager() {
        this.conferences = new ArrayList<>();
    }

    /**
     * Adds a conference to the system.
     *
     * @param conference conference to add
     */
    public void addConference(Conference conference) {
        this.conferences.add(conference);
    }

    /**
     * Returns an unmodifiable list of conferences.
     *
     * @return an unmodifiable list of conferences
     */
    public List<Conference> getConferences() {
        return Collections.unmodifiableList(conferences);
    }
}
