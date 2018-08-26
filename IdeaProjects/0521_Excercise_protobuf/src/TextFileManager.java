import java.io.*;

public class TextFileManager {

    public static String readFile(String fileName) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();

        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static void writeFile(String fileName, String inputMessage) throws IOException {
       FileWriter fileWriter = new FileWriter(fileName);
       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

       bufferedWriter.write(inputMessage);

       bufferedWriter.close();
    }

    public static void appendFile(String fileName, String inputMessage) throws IOException {
        File file = new File(fileName);

        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(inputMessage);
        fileWriter.close();
    }
}
