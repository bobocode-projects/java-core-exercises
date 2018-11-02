package com.bobocode;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * {@link FileReaders} privides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        try {
            Path path = getPathToResourseFileByFileName(fileName);
            Stream<String> lines = Files.lines(path);
            StringBuilder stringBuilder = new StringBuilder();

            lines.forEach(str -> stringBuilder.append(str).append("\n"));

            int lastIndexOfSeparator = stringBuilder.lastIndexOf("\n");

            return lastIndexOfSeparator > 0 ?
                    stringBuilder.substring(0, lastIndexOfSeparator) :
                    "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getPathToResourseFileByFileName(String fileName) throws URISyntaxException {
        URL resource = FileReaders.class.getClassLoader().getResource(fileName);
        return Paths.get(resource.toURI());
    }
}
