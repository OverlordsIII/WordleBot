package io.github.shrihun_sankepally;

import io.github.shrihun_sankepally.api.Command;

import java.util.List;

// Take parsed commands from CommandParser and execute them using their predicate
public class CommandExecutor {

    public static List<String> execute(List<String> data, List<Command> commands) {
        for (Command command : commands) {
               data = data
                       .stream()
                       .filter(command::predicate)
                       .toList();
        }

        return data;
    }
}
