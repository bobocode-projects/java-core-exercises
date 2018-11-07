package com.bobocode;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    private String fileData;

    public static FileStats from(String fileName) {
        String allLines = allLinesToString(fileName);
        return new FileStats(allLines);
    }

    private FileStats(String fileData) {
        this.fileData = fileData;
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return (int) characterStream()
                .filter(ch -> ch == character)
                .count();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        Map<Character, Long> charCountMap = characterStream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return charCountMap.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElse(null)
                .getKey();
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        Optional<Character> any = characterStream()
                .filter(ch -> ch == character)
                .findAny();
        return any.isPresent();
    }

    private Stream<Character> characterStream() {
        return fileData.chars()
                .mapToObj(ch -> (char) ch)
                .filter(ch -> ch != ' ');
    }

    private static String allLinesToString(String fileName) {
        Path path = getPath(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new FileStatsException(e.getMessage());
        }
    }

    private static Path getPath(String fileName) {
        try {
            URL url = FileStats.class.getClassLoader().getResource(fileName);
            return Paths.get(url.toURI());
        } catch (Exception e) {
            throw new FileStatsException(e.getMessage());
        }
    }
}
