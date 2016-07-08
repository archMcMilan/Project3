package parsers;

import model.Drug;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface Parser contains method parser() and field drugs that all parsers must have
 */
public interface Parser {
    final List<Drug> drugs = new ArrayList<>();
    List<Drug> parse();

    static List<Drug> getDrugs(){
        return drugs;
    }

}
