import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class TextFileReader {
    static String readTextFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
        }
        return content.toString();
    }
}
