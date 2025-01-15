/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Kate Little
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int longestWarmingTrend(int[] temperatures) {
        int length = temperatures.length;
        int[] spanUpTo = new int[length];
        // Represents the lowest temperature that has served as the start of a span of days
        int startMin = 1000;
        // Each position/temp in the array should be a potential start
        for (int i = 0; i < length; i++){
            // Only start there if it's lower than the smallest previous start — should implement this logic for all succeeding moves! During recursive part
            if (temperatures[i] < startMin){
                recurseSpanLength(i, 1, spanUpTo, temperatures);
            }
            // Update startMax
            if (temperatures[i] < startMin){
                startMin = temperatures[i];
            }
        }
        int maxRun = 0;
        for (int span : spanUpTo){
            if (span > maxRun){
                maxRun = span;
            }
        }
        return maxRun;
    }

    // Calculates the distance it takes to get to a certain temp
    // Follows an algorithm like moksha patam!
    public static void recurseSpanLength(int index, int spanLength, int[] spanUpTo, int[] temperatures){
        // Only update span length if it's greater than what's currently listed
        if (spanLength > spanUpTo[index]){
            spanUpTo[index] = spanLength;
        }
        // Base case: if final temperature has been reached, update span length & return
        int length = temperatures.length;
        if (index == length - 1){
            return;
        }
        // Recurse for each subsequent spot on the board, but only if it's greater than the current value
        // Store temperatures[index] as the next move
        for (int j = index + 1; j < length; j++){
            int smallestMove = 200;
            if (temperatures[j] > temperatures[index] && temperatures[j] < smallestMove){
                smallestMove = temperatures[j];
                recurseSpanLength(j, spanLength + 1, spanUpTo, temperatures);
            }
        }
    }
}
