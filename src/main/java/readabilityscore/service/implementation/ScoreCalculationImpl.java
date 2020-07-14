package readabilityscore.service.implementation;

import readabilityscore.service.ScoreCalculation;
import readabilityscore.service.TextData;

import java.util.Scanner;

public class ScoreCalculationImpl implements ScoreCalculation {

    public String calculateReadabilityScore(TextData data){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String calculationMethod = scanner.nextLine();
        StringBuilder builder = new StringBuilder();
        boolean all = false;
        double score;
        int age;
        int ageSum = 0;

        switch (calculationMethod){
            case "all":{
                all = true;
            }
            case "ARI":{
                score = calculateARI(data);
                ageSum += age = getAgeWhenIsPossibleToReadText(score);
                builder.append(message("Automated Readability Index", score, age));
                if(!all){
                    break;
                }
            }
            case "FK":{
                score = calculateFK(data);
                ageSum += age = getAgeWhenIsPossibleToReadText(score);
                builder.append(message("Flesch–Kincaid readability tests", score, age));
                if(!all){
                    break;
                }
            }
            case "SMOG":{
                score = calculateSMOG(data);
                ageSum += age = getAgeWhenIsPossibleToReadText(score);
                builder.append(message("Simple Measure of Gobbledygook", score, age));
                if(!all){
                    break;
                }
            }
            case "CL":{
                score = calculateCL(data);
                ageSum += age = getAgeWhenIsPossibleToReadText(score);
                builder.append(message("Coleman–Liau index", score, age));
                if(!all){
                    break;
                }
            }
            default:{
                builder.append(String.format("This text should be understood in average by %.2f year olds.%n", ageSum / 4d));
            }
        }
        return builder.toString();
    }

    private double calculateARI(TextData data){
        return 4.71 * data.getCharacters() / data.getWords() + 0.5 * data.getWords() / data.getSentences() - 21.43;
    }

    private double calculateFK(TextData data){
        return 0.39 * data.getWords() / data.getSentences() + 11.8 * data.getSyllables() / data.getWords() - 15.59;
    }

    private double calculateSMOG(TextData data){
        return 1.043 * Math.sqrt(data.getPolysyllables() * 30 / (double) data.getSentences()) + 3.1291;
    }

    private double calculateCL(TextData data){
        return 0.0588 * data.getCharacters() / data.getWords() * 100 - 0.296 * data.getSentences() / data.getWords() * 100 - 15.8;
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

    private String message(String calculationMethod, double score, int age){
        return String.format("%s: %.2f (about %d year olds).%n" , calculationMethod, score, age);
    }
}
