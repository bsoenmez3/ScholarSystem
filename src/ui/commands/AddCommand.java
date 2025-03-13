package src.ui.commands;

import resources.Commands;
import src.Splitter;
import src.model.ScholarSystem;
import src.resources.Errors;

import java.util.List;

/**
 * Handles the "add" command.
 */
public class AddCommand implements Command {
    public static final int FOUR = 4;
    private final ScholarSystem system;

    public AddCommand(ScholarSystem system) {
        this.system = system;
    }

    @Override
    public void execute(String[] tokens) {
        if (tokens.length < 3) {
            System.err.println(Errors.INVALID_INPUT);
            return;
        }
        String type = tokens[1];
        switch (type) {
            case "author":
                if (tokens.length < 4) {
                    System.err.println(Errors.INVALID_AUTHOR_NAME);
                } else {
                    system.addAuthor(tokens[2], tokens[3]);
                }
                break;
            case "journal":
                String[] journalParams = tokens[2].split(Commands.PARAMETER_SEPARATOR);
                if (journalParams.length < 2) {
                    System.err.println(Errors.INVALID_INPUT);
                } else {
                    system.addJournal(journalParams[0], journalParams[1]);
                }
                break;
            case "series":
                system.addSeries(tokens[2]);
                break;
            case "conference":
                String[] confParams = tokens[2].split(Commands.PARAMETER_SEPARATOR);
                if (confParams.length < 3) {
                    System.err.println(Errors.INVALID_INPUT);
                } else {
                    system.addConference(confParams[0], Integer.parseInt(confParams[1]), confParams[2]);
                }
                break;
            case "article":
                if (!tokens[2].equals("to")) {
                    System.err.println(Errors.INVALID_INPUT);
                } else {
                    handleArticleAddition(tokens);
                }
                break;
            case "keywords":
                handleKeywords(tokens);
                break;
            default:
                System.out.println(Errors.INVALID_INPUT);
                break;
        }
    }

    private void handleArticleAddition(String[] tokens) {
        String venueType = tokens[3];
        List<List<String>> params = Splitter.split(FOUR, tokens, ":", ",");
        if (params.size() < 2 || params.get(0).isEmpty() || params.get(1).size() < 3) {
            System.err.println(Errors.INVALID_INPUT);
            return;
        }
        String venueName = params.get(0).get(0);
        String id = params.get(1).get(0);
        int year = Integer.parseInt(params.get(1).get(1));
        String title = params.get(1).get(2);
        system.addArticle(venueType, venueName, id, year, title);
    }

    private void handleKeywords(String[] tokens) {
        if (tokens.length < FOUR) {
            System.err.println(Errors.INVALID_INPUT);
            return;
        }
        if (tokens[3].equals("journal")) {
            String[] params = tokens[FOUR].split(":");
            if (params.length < 2) {
                System.err.println(Errors.INVALID_INPUT);
            } else {
                system.addKeywordsToJournal(params[1].split(";"), params[0]);
            }
        } else if (tokens[3].equals("series")) {
            String[] params = tokens[FOUR].split(":");
            if (params.length < 2) {
                System.err.println(Errors.INVALID_INPUT);
            } else {
                system.addKeywordsToSeries(params[1].split(";"), params[0]);
            }
        } else if (tokens[3].contains(":")) {
            String[] params = tokens[3].split(":");
            if (params.length < 2) {
                System.err.println(Errors.INVALID_INPUT);
            } else {
                system.addKeywordsToArticle(params[1].split(";"), params[0]);
            }
        }
    }
}
