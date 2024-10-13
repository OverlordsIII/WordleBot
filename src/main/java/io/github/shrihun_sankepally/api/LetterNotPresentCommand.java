package io.github.shrihun_sankepally.api;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterNotPresentCommand implements Command {

    private char letter;

    public LetterNotPresentCommand(char letter) {
        this.letter = Character.toLowerCase(letter);
    }

    public static Command parse(Matcher letterNotPresent) {
        return new LetterNotPresentCommand(letterNotPresent.group(1).charAt(0));
    }

    public char getLetter() {
        return letter;
    }

    @Override
    public String getName() {
        return "DOES_NOT_CONTAIN";
    }

    @Override
    public int getParameters() {
        return 1;
    }

    @Override
    public boolean predicate(String word) {
        return word.indexOf(letter) == -1;
    }
}
