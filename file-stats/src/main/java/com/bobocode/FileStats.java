package com.bobocode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {

    private final Map<Character, Long> characterMap;
    private final Character mostPopularCharacter;

    public FileStats(String fileName) {
        URL fileUrl = getClass().getClassLoader().getResource(fileName);
        if (fileUrl == null) {
            throw new FileStatsException("File " + fileName + " not fount");
        }
        Path filePath;
        try {
            filePath = Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new FileStatsException(e.getMessage(), e);
        }
        Stream<String> lines;
        try {
            lines = Files.lines(filePath);
        } catch (IOException e) {
            throw new FileStatsException(e.getMessage(), e);
        }

        characterMap = lines
                .flatMapToInt(str -> str.chars())
                .filter(i -> i != 32)
                .mapToObj(i -> (char) i)
                .collect(groupingBy(identity(), counting()));

        mostPopularCharacter = characterMap.entrySet().stream()
                .max(comparing(es -> es.getValue()))
                .get()
                .getKey();
    }

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        return new FileStats(fileName);
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return characterMap.get(character).intValue();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        return mostPopularCharacter;
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return characterMap.containsKey(character);
    }
}
