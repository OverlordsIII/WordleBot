package io.github.shrihun_sankepally.api;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterNotInPositionCommand implements Command {

    private char letter;

    private int position;

    public LetterNotInPositionCommand(char letter, int position) {
        this.letter = Character.toLowerCase(letter);
        this.position = position;
    }

    public static Command parse(Matcher letterNotInPosition) {
        String group = letterNotInPosition.group(1);
        return new LetterNotInPositionCommand(letterNotInPosition.group(1).charAt(0), Integer.parseInt(letterNotInPosition.group(2)));
    }

    public int getPosition() {
        return position;
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public String getName() {
        return "DOES_NOT_HAVE_LETTER_IN_POSITION";
    }

    @Override
    public int getParameters() {
        return 2;
    }

    @Override
    public boolean predicate(String word) {
        return (word.charAt(position) != letter) && (word.indexOf(letter) != -1);
    }
}
