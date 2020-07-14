package readabilityscore.service.implementation;

import readabilityscore.service.FileReader;
import readabilityscore.service.ScoreCalculation;

public class ScoreCalculationImpl implements ScoreCalculation {

    private final TextImpl textImpl     = new TextImpl();
    private final FileReader reader     = new FileReader();
    private final String text           = reader.readTextFromFile("src/main/resources/input.txt");

    // constants required to calculations
    private final long characters       = textImpl.getNumberOfCharacters(text);
    private final long sentences        = textImpl.counter(text, "[!//.//?]");
    private final long words            = textImpl.counter(text, " ");
    private final long syllables        = textImpl.getNumberOfSyllables(text);
    private final long polysyllables    = textImpl.getNumberOfPolysyllables(text);

    private double calculateARI(){
        return 4.71 * characters / words + 0.5 * words / sentences - 21.43;
    }

    private double calculateFK(){
        return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }

    private double calculateSMOG(){
        return 1.043 * Math.sqrt((double) polysyllables * 30 / sentences) + 3.1291;
    }

    private double calculateCL(){
        return 0.0588 * characters / words * 100 - 0.296 * sentences / words * 100 - 15.8;
    }

    private int getAgeWhenIsPossibleToReadText(double score){
        int intScore = (int) Math.round(score);

        if(intScore == 2){
            return 7;
        } else if(intScore == 13){
            return 24;
        } else if(intScore > 13){
            return 25;
        } else {
            return intScore + 6;
        }
    }
}
