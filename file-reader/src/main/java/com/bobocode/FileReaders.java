package com.bobocode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
    public static String readWholeFile(String fileName) { // todo: refactor to follow clean code rules and use java best practices
        Path path = null;
        try {
            path = Paths.get(FileReaders.class.getClassLoader().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        List<String> l = new ArrayList<>();
        Stream<String> stream = null;
        try {
            stream = Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stream.forEach(l::add);
        stream.close();
        String str = "";
        for (int i = 0; i < l.size(); i++) {
            str += l.get(i);
            if (i != l.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }
}
