package readabilityscore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import readabilityscore.service.implementation.TextImpl;

public class TextImplTest {

    private TextImpl textImpl;

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
}
