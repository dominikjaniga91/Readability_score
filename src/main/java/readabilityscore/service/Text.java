package readabilityscore.service;

public interface Text {

    int counter(String text, String regexp);

    int getNumberOfCharacters(String text);

    long getNumberOfSyllables(String sentence);
}
