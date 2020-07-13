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
    public long getNumberOfSyllables(String sentence){
        String[] words = sentence.split(" ");

        long numberOfVowels = getNumberOfVowels(words);
        long wordsWithoutVowel = count(words, "[^aoeuiyAOEUIY]*");
        long wordsWithDoubledVowel = count(words, ".*[aeiouy][aeiouy].*");
        long wordsEndsWithE = count(words, ".*e");

        return  numberOfVowels
                + wordsWithoutVowel
                - wordsWithDoubledVowel
                - wordsEndsWithE;
    }

    protected long getNumberOfVowels(String[] words){
        return Stream.of(words).map(word -> word.chars()
                                                .mapToObj(Character::toString)
                                                .filter(s -> s.matches("[aeiouyAEIOUY]"))
                                                .count()).reduce(0L, Long::sum);
    }

    protected long count(String[] words, String regexp){
        return Stream.of(words)
                    .filter(word -> word.matches(regexp))
                    .count();
    }
}
