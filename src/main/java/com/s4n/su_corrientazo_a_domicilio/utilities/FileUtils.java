package com.s4n.su_corrientazo_a_domicilio.utilities;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class FileUtils {

    private FileUtils() {
    }

    public static Optional<InputStream> getResourceAsInputStream(@NotNull String filePath) {
        return Optional.ofNullable(getSystemClassLoader())
                .map(loader -> loader.getResourceAsStream(filePath));
    }

    public static List<File> getFolderFiles(@NotNull String folderPath) {
        return Optional.ofNullable(new File(folderPath).listFiles())
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .filter(File::isFile)
                .collect(toList());
    }

    public static List<String> getFileContent(@NotNull File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader
                    .lines()
                    .filter(Objects::nonNull)
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .collect(toList());
        } catch (IOException e) {
            return emptyList();
        }
    }

    public static boolean writeFile(@NotNull String fileName, @NotNull List<String> content) {
        try {
            File file = new File(fileName);
            boolean fileExist = file.exists();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, fileExist))) {
                for (String line : content) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
