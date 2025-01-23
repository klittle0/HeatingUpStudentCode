import org.junit.jupiter.api.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherPatternsTest {

    private int[] temps;

    @Test
    public void testCorrectSmall() {
        setTestData(0);
    }

    @Test
    public void testCorrectMed() {
        setTestData(1);
    }

    @Test
    public void testTricky() {
        setTestData(2);
    }

    @Test
    @Timeout(value = 1500, unit = TimeUnit.MILLISECONDS)
    public void testEfficientLarge() {
        setTestData(3);
    }

    @Test
    @Timeout(value = 150, unit = TimeUnit.MILLISECONDS)
    public void testEdgeCase() {
        setTestData(4);
    }


    private void setTestData(int testNumber) {
        // Open files
        try {
            BufferedReader testReader = new BufferedReader(new FileReader("test_files/" + testNumber + ".txt"));
            BufferedReader answerReader = new BufferedReader(new FileReader("test_files/" + testNumber + "_answers.txt"));

            // Read in the data, then run.
            int answer = Integer.parseInt(answerReader.readLine());
            loadTest(testReader);
            assertEquals(answer, WeatherPatterns.longestWarmingTrend(temps),
                    "Test " + testNumber + " failed: should return " + answer);

        } catch (IOException e) {
            System.out.println("Error opening test file #" + testNumber);
            e.printStackTrace();
        }
    }

    private void loadTest(BufferedReader br) {
        String line;
        try {
            line = br.readLine();

            // Update instance variables with test data
            int n = Integer.parseInt(line);
            temps = new int[n];

            for (int i = 0; i < n; i++) {
                temps[i] = Integer.parseInt(br.readLine());
            }
        } catch (IOException e) {
            System.out.println("Error opening test file");
            e.printStackTrace();
        }
    }
}
