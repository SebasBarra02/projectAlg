import java.util.HashSet;
import java.util.Set;

class WordProcessor {
    String[] splitTextIntoWords(String text) {
        return text.split("\\s+");
    }

    String findCorrectSpelling(String misspelled, String[] words1) {
        String mostSimilarWord = misspelled;

        int minDistance = Integer.MAX_VALUE;

        for (String word1 : words1) {
            int distance = calculateLevenshteinDistance(word1, misspelled);
            if (distance < minDistance) {
                minDistance = distance;
                mostSimilarWord = word1;
            }
        }

        if (minDistance <= 0.55 * misspelled.length()) {
            return mostSimilarWord;
        }

        return misspelled; // Si no encontramos una palabra similar, devolvemos la palabra mal escrita original
    }

    private int calculateLevenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    Set<String> getCommonWords() {
        Set<String> commonWords = new HashSet<>();
        commonWords.add("the");
        commonWords.add("is");
        commonWords.add("and");
        return commonWords;
    }
}
