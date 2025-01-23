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
        int length = temperatures.length;
        int[] spanLengths = new int[length];
        ArrayList<Integer>[] adjLists = new ArrayList[length];

        for (int i = length - 1; i >= 0; i--){
            // Initialize each arraylist in adjLists — referenced Geeksforgeeks for this line
            adjLists[i] = new ArrayList<Integer>();
            // Add all possible edges to list, aka all nodes that lead to temperatures[i]
            for (int j = i - 1; j >= 0; j--){
                if (temperatures[j] < temperatures[i]){
                    adjLists[i].add(j);
                }
            }
        }

        // Recurse to find the longest span for every temp
        for (int i = 0; i < length; i++){
            spanLengths[i] = findLongestPath(i, spanLengths, adjLists);
        }

        // Find the longest span of all
        int max = 0;
        for (int span : spanLengths){
            // REMOVE PRINT STATEMENT LATER
            //System.out.println(span);
            if (span > max){
                max = span;
            }
        }
        return max;
    }

    // Recursive method to find the longest span of temperatures leading to tempIndex
    public static int findLongestPath(int tempIndex, int[] spanLengths, ArrayList<Integer>[] adjList){
        // Base case: If node already visited, break out early to remove redundant recursion
        if (spanLengths[tempIndex] != 0){
            return spanLengths[tempIndex];
        }

        int spanLen = 0;
        for (int temp : adjList[tempIndex]){
            spanLen = Math.max(spanLen, findLongestPath(temp, spanLengths, adjList));
        }
        return spanLen + 1;
    }
}