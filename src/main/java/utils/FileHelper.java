package utils;

import static java.lang.String.format;
import static java.lang.System.getProperty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper {

    private static final String PATH_TO_FILE = getProperty("resources.path", "src/main/resources/%s.txt");

    public String readFile(String fileName) {
        String file = format(PATH_TO_FILE, fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file" + e.getMessage());
        }
    }
}
