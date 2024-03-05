import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class TextComparator {

    private double calculateTextSimilarity(String text1, String text2) {
        WordProcessor wordProcessor = new WordProcessor();
        HashMap<String, String> misspelledWords = new HashMap<>();
        int misspelledCount = 0;
        int totalWords = 0;

        String[] words1 = wordProcessor.splitTextIntoWords(text1);
        String[] words2 = wordProcessor.splitTextIntoWords(text2);

        Set<String> commonWords = wordProcessor.getCommonWords();

        for (String word2 : words2) {
            if (word2.length() == 1 || commonWords.contains(word2.toLowerCase()))
                continue;

            String correctedWord = wordProcessor.findCorrectSpelling(word2, words1);
            if (!word2.equals(correctedWord)) {
                misspelledWords.put(word2, correctedWord);
                misspelledCount++;
            }
            totalWords++;
        }

        for (String word1 : words1) {
            if (!Arrays.asList(words2).contains(word1)) {
                totalWords++;
            }
        }

        double similarity = (double) (totalWords - misspelledCount) / totalWords * 100;
        System.out.println("• corrected words:");
        misspelledWords.forEach((misspelled, corrected) ->
                System.out.println(misspelled + " - " + corrected));
        return similarity;
    }

    public static void main(String[] args) {
        String text1Path = "/home/fundacion/projectAlg/resources/txt1.txt";
        String text2Path = "/home/fundacion/projectAlg/resources/txt2.txt";

        try {
            String text1 = TextFileReader.readTextFromFile(text1Path);
            String text2 = TextFileReader.readTextFromFile(text2Path);

            TextComparator calculator = new TextComparator();
            double similarity = calculator.calculateTextSimilarity(text1, text2);
            System.out.printf("• %.2f%%\n", similarity);

        } catch (IOException e) {
            System.out.println("Error al leer los archivos de texto.");
            e.printStackTrace();
        }
    }
}
