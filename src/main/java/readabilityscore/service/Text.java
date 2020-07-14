package readabilityscore.service;

public interface Text {

    int counter(String text, String regexp);

    int getNumberOfCharacters(String text);

    long getNumberOfPolysyllables(String sentence);

    long getNumberOfSyllables(String sentence);
}
