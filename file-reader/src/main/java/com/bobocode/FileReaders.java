package com.bobocode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * {@link FileReaders} privides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    private static final String PATH = "/home/serhii/IdeaProjects/bobocode/java-core-exercises/file-reader/src/test/resources/";

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        StringBuilder readedString = new StringBuilder();
        File file = new File(PATH + fileName);
        try (FileInputStream fin = new FileInputStream(file)) {
            int character;
            while (fin.available() != 0) {
                character = fin.read();
                readedString.append((char) character);
            }
        }
        catch (IOException  e) {
            e.printStackTrace();
        }
        return readedString.toString();
    }
}
