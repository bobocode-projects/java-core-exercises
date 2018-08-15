package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FileReadersTest {

    @Test
    public void testReadWholeFileOnEmptyFile() {
        String fileContent = FileReaders.readWholeFile("empty.txt");

        assertEquals("", fileContent);

    }

    @Test
    public void testReadWholeFileOnFileWithEmptyLines() {
        String fileContent = FileReaders.readWholeFile("lines.txt");

        assertEquals("Hey!\n" +
                "\n" +
                "What's up?\n" +
                "\n" +
                "Hi!", fileContent);
    }

    @Test
    public void testReadWholeFile() {
        String fileContent = FileReaders.readWholeFile("simple.txt");

        assertEquals("Hello!\n" + "It's a test file.", fileContent);
    }
}
