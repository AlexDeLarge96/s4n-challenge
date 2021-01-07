package com.s4n.su_corrientazo_a_domicilio.utilities;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static com.s4n.su_corrientazo_a_domicilio.utilities.FileUtils.*;
import static java.nio.file.Files.deleteIfExists;
import static java.nio.file.Paths.get;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUtilsTest {

    public static final String TEST_FILE = "./src/main/resources/testFile.txt";

    @Test
    void shouldReturnResourceAsInputStream() {
        Optional<InputStream> resource = getResourceAsInputStream("application.yaml");

        assertTrue(resource.isPresent());
    }

    @Test
    void shouldReturnFolderFiles() {
        List<File> files = getFolderFiles("./src/main/resources");

        assertTrue(files.size() > 0);
    }

    @Test
    void shouldReadFileContent() {
        boolean testPassed = false;
        try {
            deleteIfExists(get(TEST_FILE));
            writeFile(TEST_FILE, singletonList("test passed"));
            List<String> content = getFileContent(new File(TEST_FILE));
            testPassed = content.size() == 1 && content.get(0).equals("test passed");
            deleteIfExists(get(TEST_FILE));
        } catch (IOException ignored) {
        }

        assertTrue(testPassed);
    }

    @Test
    void shouldReturnEmptyFileContentAfterException() {
        File file = new File("\u0000fail.txt");
        List<String> content = getFileContent(file);

        assertTrue(content.isEmpty());
    }

    @Test
    void shouldWriteTestFile() {
        boolean fileWasWritten = false;
        try {
            deleteIfExists(get(TEST_FILE));
            fileWasWritten = writeFile(TEST_FILE, singletonList("test"));
            deleteIfExists(get(TEST_FILE));
        } catch (IOException ignored) {
        }

        assertTrue(fileWasWritten);

    }

    @Test
    void shouldNotWriteTestFile() {
        boolean fileWasWritten = writeFile("\u0000fail.txt", singletonList("nothing"));
        assertFalse(fileWasWritten);
    }
}