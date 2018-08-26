import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CsvFileManager {
    private static String[][] csv;
    private static String[] token;
    private static int row = 0;

    public static void readFile(String fileName) {
        String line;
        ArrayList<String[]> list = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                token = line.split(","); // -> column
                list.add(token);
                row++;
            }

            csv = new String[row][token.length];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < token.length; j++) {
                    csv[i][j % token.length] = list.get(i)[j];
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String[][] getCsvArray(String fileName) {
        readFile(fileName);
        return csv;
    }
}
