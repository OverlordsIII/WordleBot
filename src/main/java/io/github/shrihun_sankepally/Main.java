package io.github.shrihun_sankepally;

import com.google.gson.*;
import io.github.shrihun_sankepally.api.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Path RESOURCE_PATH = Paths.get("src", "main", "resources");

    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();

    public static void main(String[] args) throws IOException {
        List<String> unparsedCommands = getFileDataFromUser();

        List<Command> commands = CommandParser.parse(unparsedCommands);
        List<String> data = CommandExecutor.execute(getWordBank(), commands);

        exportWords(data);
    }

    private static List<String> getWordBank() throws IOException {
        JsonObject obj = GSON.fromJson(Files.readString(RESOURCE_PATH.resolve("words.json")), JsonObject.class);

        JsonArray array = obj.getAsJsonArray("words");

        return array.asList()
                .stream()
                .map(JsonElement::getAsString)
                .toList();
    }

    private static void exportWords(List<String> lines) {
        JsonArray array = new JsonArray();
        lines.forEach(line -> array.add(new JsonPrimitive(line)));

        JsonObject object = new JsonObject();
        object.add("words", array);
        String file = GSON.toJson(object);

        try {
            Files.writeString(RESOURCE_PATH.resolve("output.json"), file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getFileDataFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file name to the file you wish to read: ");
        String path = scanner.nextLine();

        String file;

        try {
            file = Files.readString(RESOURCE_PATH.resolve(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> lines = new ArrayList<>();

        for (String string : file.split("\n")) {
            string = string.trim();
            if (string.startsWith("#") || string.isEmpty()) {
                continue;
            }

            lines.add(string);
        }

        return lines;
    }


/*
    public static void main(String[] args) throws IOException {
        String str = Files.readString(Path.of("C:\\Users\\shrih\\OneDrive\\Desktop\\WordleProject\\src\\main\\resources\\words.txt"));
        JsonArray array = new JsonArray();
        for (String s : str.split("\n")) {
            array.add(s.trim());
        }

        JsonObject object = new JsonObject();
        object.add("words", array);

        String json = GSON.toJson(object);
        Files.writeString(Path.of("C:\\Users\\shrih\\OneDrive\\Desktop\\WordleProject\\src\\main\\resources\\words.json"), json);
    }
 */
}