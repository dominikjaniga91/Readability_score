package readabilityscore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileReaderTest {

    @Test
    @DisplayName("Should return text after read file")
    void shouldReturnText_afterReadTextFromFile(){
        String expected = "This is the front page of the Simple English Wikipedia.";
        FileReader reader = new FileReader();

        String actual = reader.readTextFromFile("src/main/resources/test.txt");

        Assertions.assertEquals(expected, actual);
    }
}
