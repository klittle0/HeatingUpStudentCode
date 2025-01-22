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
        // Make Adjacency matrix tracking all possible edges
        int length = temperatures.length;
        int[] spanLengths = new int[length];
        boolean[][] adjMatrix = new boolean[length][length];
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++){
                // If
                if (j > i){
                    adjMatrix[i][j] = true;
                }
                else{
                    adjMatrix[i][j] = false;
                }
            }
        }
        // Recurse to find the longest span for every temp
        for (int i = 0; i < length; i++){
            findLongestPath(i, spanLengths, adjMatrix);
        }
        int max = 0;
        // Find the longest span of all
        for (int span : spanLengths){
            if (span > max){
                max = span;
            }
        }
        return max;
    }


    // Recursive method to find the longest span of temperatures leading to tempIndex
    public static void findLongestPath(int tempIndex, int[] spanLengths, boolean[][] adjMatrix){
        int spanLen = 0;
        for (int i = 0; i < adjMatrix.length; i++){
            // If a given index leads to the specified temp
            if (adjMatrix[i][tempIndex]){
                // Check if visited to reduce recursive calls
                if (spanLengths[i] != 0){
                    // Is this true??
                    spanLen = spanLengths[i] + 1;
                }
                else{
                    // DON'T FORGET TO INCLUDE THE + 1 SOMEWHERE HERE?
                    findLongestPath(i, spanLengths, adjMatrix);
                    spanLen = 1 + Math.max(spanLen, findLongestPath(i, spanLengths, adjMatrix));
                }
            }
        }
        spanLengths[tempIndex] = spanLen;
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
