package readabilityscore.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public String readTextFromFile(String path){

        String sentence = null;
        try{
            sentence = new String(Files.readAllBytes(Paths.get(path)));
            System.out.println("The text is:");
            System.out.println(sentence);
        }catch (IOException exception){
            System.out.println(exception.getLocalizedMessage());
        }

        return sentence;
    }
}
