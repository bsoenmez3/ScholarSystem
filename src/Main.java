package src;

import src.model.ScholarSystem;
import src.resources.Errors;
import src.ui.ScholarSystemUI;

/**
 * This is the main class.
 *
 * @author bsoenmez
 * @version 1.0
 */
public final class Main {


    private Main() {
        //This is a utility class so has a private constructor.
        throw new IllegalStateException(Errors.UTILITY_CLASS_INSTANTIATION);
    }

    /**
     * This method is the main entry point to the application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        ScholarSystem scholarSystem = new ScholarSystem();
        ScholarSystemUI scholarSystemUI = new ScholarSystemUI(scholarSystem);
        scholarSystemUI.interactive();
    }
}
