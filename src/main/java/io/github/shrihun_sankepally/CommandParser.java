package io.github.shrihun_sankepally;

import io.github.shrihun_sankepally.api.Command;
import io.github.shrihun_sankepally.api.LetterInPositionCommand;
import io.github.shrihun_sankepally.api.LetterNotInPositionCommand;
import io.github.shrihun_sankepally.api.LetterNotPresentCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Has multiple possible commands that must be parsed:
 * DOES_NOT_CONTAIN('X') -> Indicates that the letter is not present
 * HAS_LETTER_IN_POSITION('X', 0) -> Indicates that the letter is present at specific position
 * DOES_NOT_HAVE_LETTER_IN_POSITION('X', 0) -> Indicates letter is in word but in a different position
 */
public class CommandParser {

    public static final Pattern LETTER_IN_POSITION = Pattern.compile("HAS_LETTER_IN_POSITION\\s*\\(\\s*'(.)',\\s*(\\d+)\\)");
    public static final Pattern LETTER_NOT_IN_POSITION = Pattern.compile("DOES_NOT_HAVE_LETTER_IN_POSITION\\s*\\(\\s*'(.)',\\s*(\\d+)\\)");
    public static final Pattern LETTER_NOT_PRESENT = Pattern.compile("DOES_NOT_CONTAIN\\s*\\(\\s*'(.)'\\)");

    public static List<Command> parse(List<String> lines) {
        List<Command> commands = new ArrayList<>();
        for (String line : lines) {

            Matcher letterInPosition = LETTER_IN_POSITION.matcher(line);
            Matcher letterNotInPosition = LETTER_NOT_IN_POSITION.matcher(line);
            Matcher letterNotPresent = LETTER_NOT_PRESENT.matcher(line);


            if (letterInPosition.matches()) {
                commands.add(LetterInPositionCommand.parse(letterInPosition));
            } else if (letterNotInPosition.matches()) {
                commands.add(LetterNotInPositionCommand.parse(letterNotInPosition));
            } else if (letterNotPresent.matches()) {
                commands.add(LetterNotPresentCommand.parse(letterNotPresent));
            } else {
                System.out.println("Invalid Line!: " + line);
            }
        }

        return commands;
    }

}
