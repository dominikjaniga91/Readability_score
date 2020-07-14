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
    private final double L              = (double) characters / words * 100;
    private final double S              = (double) sentences / words * 100;


    private double calculateARI(){
        return 4.71 * characters / words + 0.5 * words / sentences - 21.43;
    }

    private double calculateFK(){
        return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
    }

    private double calculateSMOG(){
        return 1.043 * Math.sqrt((double) polysyllables * 30 / sentences) + 3.1291;
    }
}
