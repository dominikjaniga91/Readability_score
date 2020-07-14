package readabilityscore;

import readabilityscore.service.implementation.TextImpl;

public class TextData {

    private final long characters;
    private final long sentences;
    private final long words;
    private final long syllables;
    private final long polysyllables;

    public TextData(TextImpl textImpl, String text) {
        this.characters         = textImpl.getNumberOfCharacters(text);
        this.sentences          = textImpl.counter(text, "[!//.//?]");
        this.words              = textImpl.counter(text, " ");
        this.syllables          = textImpl.getNumberOfSyllables(text);
        this.polysyllables      = textImpl.getNumberOfPolysyllables(text);

        System.out.println("Characters: " + characters);
        System.out.println("Sentences: " + sentences);
        System.out.println("Words: " + words);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
    }

    public long getCharacters() {
        return characters;
    }

    public long getSentences() {
        return sentences;
    }

    public long getWords() {
        return words;
    }

    public long getSyllables() {
        return syllables;
    }

    public long getPolysyllables() {
        return polysyllables;
    }
}
