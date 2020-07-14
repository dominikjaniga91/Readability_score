package readabilityscore.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TextImplTest {

    private TextImpl textImpl;
    private final String text = "This is the front page of the Simple English Wikipedia. " +
            "Wikipedias are places where people work together to write encyclopedias in different languages. " +
            "We use Simple English words and grammar here. The Simple English Wikipedia is for everyone! " +
            "That includes children and adults who are learning English. There are 142,262 articles on the Simple English Wikipedia. " +
            "All of the pages are free to use. They have all been published under both the Creative Commons License and the GNU Free Documentation License. " +
            "You can help here! You may change these pages and make new pages. Read the help pages and other good pages to learn how to write pages here. " +
            "If you need help, you may ask questions at Simple talk. Use Basic English vocabulary and shorter sentences. " +
            "This allows people to understand normally complex terms or phrases.";

    @BeforeEach
    void setUp() {
        textImpl = new TextImpl();
    }

    @ParameterizedTest(name = "{0} should give result: {1}")
    @CsvFileSource(resources = "/data.csv", delimiter = ';')
    @DisplayName("Should return appropriate number of words in text:")
    void shouldReturnNumberOfWords(String text, int expected){
        int  actual = textImpl.counter(text, " ");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return zero when there is no text")
    void shouldReturnZero_whenThereIsNoText(){
        String text = "       ";
        int expected = 0;
        int actual = textImpl.counter(text, " ");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return appropriate number of sentences")
    void shouldReturnAppropriateNumberOfSentences(){
        int expected = 14;
        int actual = textImpl.counter(text, "[!//.//?]");
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "{0} should give result: {1}")
    @CsvFileSource(resources = "/words.csv")
    @DisplayName("Should return appropriate number of vowels in word")
    void shouldReturnAppropriateNumberOfVowels(String word, long expected){
        long  actual = textImpl.getNumberOfSyllablesInWord(word);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Should return appropriate number of syllables in text")
    void shouldReturnAppropriateNumberOfSyllablesInText(){
        long expected = 210;
        long  actual = textImpl.getNumberOfSyllables(text);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return appropriate number of sentences in text")
    void shouldReturnAppropriateNumberOfSentencesInText(){
        long expected = 14;
        long  actual = textImpl.counter(text, "[!//.//?]");
        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Should return appropriate number of characters in text")
    void shouldReturnAppropriateNumberOfCharactersInText(){
        long expected = 687;
        long  actual = textImpl.getNumberOfCharacters(text);
        Assertions.assertEquals(expected, actual);
    }
}
