package utils;

import java.util.Random;

public class StringUtils {

    private static final String REGEX = "[^a-zA-Z]";

    public static String getRandomStringFromFile(String file, int stringLength) {
        String initialText = deleteNonAlphabeticChars(new FileHelper().readFile(file));
        StringBuilder builderForString = new StringBuilder();
        Random random = new Random();
        while (builderForString.length() < stringLength) {
            int index = (int) (random.nextFloat() * initialText.length());
            builderForString.append(initialText.charAt(index));
        }
        return builderForString.toString();
    }

    private static String deleteNonAlphabeticChars(String string) {
        return string.replaceAll(REGEX, "");
    }
}
