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
}
