package com.bobocode;


import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
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
        int randomApproach = new Random().nextInt(3) + 1;
        switch (randomApproach) {
            case 1: {
                System.out.println(randomApproach + ", using getCharacterStreamFromFile()");
                Stream<Character> characters = getCharacterStreamFromFile(fileName);
                characterMap = populateCharacterFrequencyMap(characters);
                mostPopularCharacter = findMostPopularCharacter(characterMap);
                break;
            }
            case 2: {
                System.out.println(randomApproach + ", getCharacterStreamFromFileBufferedReader()");
                Stream<Character> characters = getCharacterStreamFromFileBufferedReader(fileName);
                characterMap = populateCharacterFrequencyMap(characters);
                mostPopularCharacter = findMostPopularCharacter(characterMap);
                break;
            }
            case 3: {
                System.out.println(randomApproach + ", getCharacterStreamFromFileCommonIO()");
                Stream<Character> characters = getCharacterStreamFromFileCommonIO(fileName);
                characterMap = populateCharacterFrequencyMap(characters);
                mostPopularCharacter = findMostPopularCharacter(characterMap);
                break;
            }
            default:
                characterMap = new HashMap<>();
                mostPopularCharacter = null;
                break;
        }
    }

    private Stream<Character> getCharacterStreamFromFile(String fileName) {
        URL fileUrl = getFileUrl(fileName);
        Path filePath = getFilePath(fileUrl);
        Stream<String> lines = getStringLineStream(filePath);
        return lines
                .flatMapToInt(String::chars)
                .mapToObj(i -> (char) i);
    }

    private URL getFileUrl(String fileName) {
        Objects.requireNonNull(fileName);
        URL fileUrl = getClass().getClassLoader().getResource(fileName);
        if (fileUrl == null) {
            throw new FileStatsException("File " + fileName + " not fount");
        }
        return fileUrl;
    }

    private Path getFilePath(URL fileUrl) {
        try {
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new FileStatsException(e.getMessage(), e);
        }
    }

    private Stream<String> getStringLineStream(Path filePath) {
        try {
            return Files.lines(filePath);
        } catch (IOException e) {
            throw new FileStatsException(e.getMessage(), e);
        }
    }

    private Stream<Character> getCharacterStreamFromFileBufferedReader(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(new File(getFileUrl(fileName).toURI())))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString().chars().mapToObj(i -> (char) i);
        } catch (URISyntaxException | IOException e) {
            throw new FileStatsException(e.getMessage(), e);
        }
    }

    private Stream<Character> getCharacterStreamFromFileCommonIO(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(new File(getFileUrl(fileName).toURI()))) {
            return IOUtils.toString(inputStream, "utf-8").chars().mapToObj(i -> (char) i);
        } catch (IOException | URISyntaxException e) {
            throw new FileStatsException(e.getMessage(), e);
        }
    }

    private Map<Character, Long> populateCharacterFrequencyMap(Stream<Character> characters) {
        return characters
                .filter(c -> c != 32)
                .collect(groupingBy(identity(), counting()));
    }

    private Character findMostPopularCharacter(Map<Character, Long> characterMap) {
        return characterMap.entrySet().stream()
                .max(comparing(Map.Entry::getValue))
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
