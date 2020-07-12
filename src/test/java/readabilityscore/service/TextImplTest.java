package readabilityscore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import readabilityscore.service.implementation.TextImpl;

public class TextImplTest {

    private TextImpl textImpl;

    @BeforeEach
    void setUp() {
        textImpl = new TextImpl();
    }

    @Test
    @DisplayName("Should return appropriate number of words in text")
    void shouldReturnNumberOfWords(){
        String text = "This is the front page of the Simple English Wikipedia. Wikipedias are places where people work together to write encyclopedias in different languages.";
        int expected = 23;
        int  actual = textImpl.counter(text, " ");
        Assertions.assertEquals(expected, actual);
    }
}
