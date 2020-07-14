package readabilityscore.service.implementation;

import readabilityscore.service.Text;

import java.util.stream.Stream;

public class TextImpl implements Text {

    /**
     * Count words or sentences in provided text base on regexp
     *
     * @param text - text to analyze
     * @param regexp - pattern use to split text
     * @return number of words or sentences
     */

    @Override
    public int counter(String text, String regexp) {
        String[] elements = text.split(regexp);

        return arrayIsEmpty(elements) ? 0
                                      : elements.length;
    }

    private boolean arrayIsEmpty(String[] array){
        return array.length == 1 && array[0].trim().equals("");
    }

    @Override
    public int getNumberOfCharacters(String text) {
        return text.replace(" ","").length();
    }

    @Override
    public long getNumberOfPolysyllables(String sentence) {
        String[] words = sentence.split(" ");
        return Stream.of(words)
                .filter(word -> getNumberOfSyllablesInWord(word) > 2)
                .count();
    }

    @Override
    public long getNumberOfSyllables(String sentence){
        return Stream.of(sentence.split(" "))
                    .map(this::getNumberOfSyllablesInWord)
                    .reduce(0L, Long::sum);

    }

    protected long getNumberOfSyllablesInWord(String word){
        long number = 0;

        if(containsSyllables(word)) {
            number += getNumberOfVowels(word);
            number -= containsDoubledSyllables(word);
            number -= endsWithE(word);

        } else {
            number++;
        }
        return number;
    }

    private boolean containsSyllables(String word){
        return word.matches(".*[aoeuiyAOEUIY].*");
    }

    private long getNumberOfVowels(String word){
        return word.chars()
                .mapToObj(Character::toString)
                .filter(c -> c.matches("[aeiouyAEIOUY]"))
                .count();
    }

    private long containsDoubledSyllables(String word){
        return word.matches(".*[aeiouy][aeiouy].*") ? 1 : 0;
    }

    private long endsWithE(String word){
        return  word.endsWith("e") ? 1 : 0;
    }


}
