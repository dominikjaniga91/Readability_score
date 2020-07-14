package readabilityscore.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public String readTextFromFile(String path){

        String sentence = null;
        try{
            sentence = new String(Files.readAllBytes(Paths.get(path)));
        }catch (IOException exception){
            System.out.println(exception.getLocalizedMessage());
        }

        return sentence;
    }
}
