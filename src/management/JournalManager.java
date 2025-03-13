package src.management;

import src.model.venue.Journal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages journals in the system.
 * Provides operations to add, retrieve, and list journals.
 *
 * @author bsoenmez
 * @version 1.1
 */
public class JournalManager {

    private final List<Journal> journals;

    /**
     * Constructs an empty JournalManager.
     */
    public JournalManager() {
        this.journals = new ArrayList<>();
    }

    /**
     * Returns an unmodifiable list of journals.
     *
     * @return the list of journals
     */
    public List<Journal> getJournals() {
        return Collections.unmodifiableList(journals);
    }

    /**
     * Adds a journal to the system.
     *
     * @param journal the journal to add
     */
    public void addJournal(Journal journal) {
        if (journal != null) {
            this.journals.add(journal);
        }
    }

    /**
     * Retrieves a journal by its name.
     *
     * @param name the name of the journal
     * @return the journal with the specified name, or null if not found
     */
    public Journal getJournalByName(String name) {
        return journals.stream()
                .filter(journal -> journal.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
