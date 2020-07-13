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
        long syllables = 0;
        for (String word : words) {
            syllables += getNumberOfVowels(word);
        }

        syllables += getNumberWordsWithoutVowel(words);
        syllables -= getNumberWordsWithoutDoubledVowel(words);
        syllables -= getNumberWordsEndsWithE(words);

        return syllables;
    }

    protected long getNumberOfVowels(String word){
        return word.chars()
                    .mapToObj(Character::toString)
                    .filter(s -> s.matches("[aeiouyAEIOUY]"))
                    .count();
    }

    protected long getNumberWordsWithoutVowel(String[] words){
        return Stream.of(words)
                    .filter(word -> !word.matches(".*[aoeuiyAOEUIY].*"))
                    .count();
    }

    protected long getNumberWordsWithoutDoubledVowel(String[] words){
        return Stream.of(words)
                .filter(word -> word.matches(".*[aeiouy][aeiouy].*"))
                .count();
    }

    protected long getNumberWordsEndsWithE(String[] words){
        return Stream.of(words)
                .filter(word -> word.matches(".*e"))
                .count();
    }
}
