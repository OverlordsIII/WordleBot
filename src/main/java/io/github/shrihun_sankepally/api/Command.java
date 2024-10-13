package io.github.shrihun_sankepally.api;


import java.util.List;
import java.util.regex.Pattern;

/**
 * Has multiple possible commands that must be parsed:
 * DOES_NOT_CONTAIN('X') -> Indicates that the letter is not present
 * HAS_LETTER_IN_POSITION('X', 0) -> Indicates that the letter is present at specific position
 * DOES_NOT_HAVE_LETTER_IN_POSITION('X', 0) -> Indicates letter is in word but in a different position
 */

public interface Command {

    public String getName();

    public int getParameters();

    public boolean predicate(String word);
}
