package io.github.shrihun_sankepally.api;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterInPositionCommand implements Command {

    private char letter;

    private int position;

    public LetterInPositionCommand(char letter, int position) {
        this.letter = Character.toLowerCase(letter);
        this.position = position;
    }

    public char getLetter() {
        return letter;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return "HAS_LETTER_IN_POSITION";
    }

    @Override
    public int getParameters() {
        return 2;
    }

    @Override
    public boolean predicate(String word) {
        return word.charAt(position) == letter;
    }

    public static LetterInPositionCommand parse(Matcher matcher) {
        return new LetterInPositionCommand(matcher.group(1).charAt(0), Integer.parseInt(matcher.group(2)));
    }
}
