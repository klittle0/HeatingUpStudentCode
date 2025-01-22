import java.util.ArrayList;

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
        ArrayList<Integer>[] adjLists = new ArrayList[length];


        // Recurse to find the longest span for every temp
        for (int i = 0; i < length; i++){
            spanLengths[i] = findLongestPath(i, spanLengths, adjMatrix);
        }
        int max = 0;
        // Find the longest span of all
        for (int span : spanLengths){
            // REMOVE PRINT STATEMENT LATER
            System.out.println(span);
            if (span > max){
                max = span;
            }
        }
        return max;
    }

    // Recursive method to find the longest span of temperatures leading to tempIndex
    public static int findLongestPath(int tempIndex, int[] spanLengths, boolean[][] adjMatrix){
        // Base case: If node already visited
        if (spanLengths[tempIndex] != 0){
            return spanLengths[tempIndex];
        }
        int spanLen = 0;
        for (int i = 0; i < adjMatrix.length; i++){
            // If a given index leads to the specified temp
            if (adjMatrix[i][tempIndex]){
                //System.out.println("start temp for " + temperatures[tempIndex] + ": " + temperatures[i]);
                // Check if visited to reduce recursive calls
                // ISSUE HERE WITH THIS IF/ELSE SEQUENCE!
                if (spanLengths[i] != 0){
                    spanLen = spanLengths[i];
                }
                else{
                    // Otherwise, you have to recurse & find longest possible path
                    spanLen = Math.max(spanLen, findLongestPath(i, spanLengths, adjMatrix));
                }
            }
        }
        return spanLen + 1;
    }
}