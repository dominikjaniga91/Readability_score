package readabilityscore.service;

import readabilityscore.TextData;

public interface ScoreCalculation {

    String calculateReadabilityScore(TextData data);
}
