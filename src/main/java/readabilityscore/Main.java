package readabilityscore;

import readabilityscore.service.FileReader;
import readabilityscore.service.implementation.ScoreCalculationImpl;
import readabilityscore.service.implementation.TextImpl;

public class Main {
    public static void main(String[] args) {

        var text        = new FileReader().readTextFromFile("src/main/resources/input.txt");
        var data        = new TextData(new TextImpl(), text);
        var result      = new ScoreCalculationImpl().calculateReadabilityScore(data);

        System.out.println(result);
    }
}
